import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.logging.LogRecord;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import sun.audio.*;
import java.io.*;
import java.util.Timer;



/**
 * Created by Reaper on 5/22/17.
 */
public class Game extends Canvas implements Runnable{


    public static final int HEIGHT = 600, WIDTH = HEIGHT / 12 * 9; // nice aspect ratio

//    public static final int WIDTH = 1200, HEIGHT = 800; // nice aspect ratio

    private Thread thread; // entire game will run through this thread, single threaded is not the best
    private boolean running = false;
    private Handler handler;
    private Random rnd = new Random();
    Timer timer = new Timer();
    private long initTime;

    private HUD hud_display;
    private Menu menu;
    private int spiceNumber = 0;

    private Background bckgrd;

    private Spawn spawner;
    private Player playerOne;
//    private Hilary hilz;
    private boolean paused = false;
    // Constructor
    public Game(){
        // handler first or else nullpointer
        // or else window class is created first
        this.initTime = System.currentTimeMillis();

        this.menu = new Menu();
        this.hud_display = new HUD(WIDTH-100, 10, 3);
        this.handler = new Handler();
//        this.hilz = new Hilary(10, 300);
        this.bckgrd = new Background();
        playerOne = new Player(WIDTH/2, HEIGHT-118, ID.Player1, handler, hud_display);

        spawner = new Spawn(handler, hud_display);


        this.addKeyListener(new KeyInput(handler, hud_display, paused)); // listen up
        this.addMouseListener(new MouseInput(spawner));
        new Window(WIDTH, HEIGHT, "GAME TIME!", this);



        // add objects
        handler.addObject(playerOne);
        playerOne.loadImage();
        Music();
//        for(int i = 0; i < 3; i ++)
//        {
//            handler.addObject(new Enemy(rnd.nextInt(WIDTH), rnd.nextInt(400)-500, ID.Trump));
//        }

//        handler.addObject(new Enemy_Spicey(rnd.nextInt(WIDTH), rnd.nextInt(400)-1000, ID.Spicer));
//        handler.addObject(new Enemy_Spicey(rnd.nextInt(WIDTH), rnd.nextInt(100)-1000, ID.Spicer));
       // handler.addObject(new PowerUp(100, 100, ID.Life));





    }

//    First, it is not possible for two invocations of synchronized methods on
//    the same object to interleave. When one thread is executing a synchronized method
//    for an object, all other threads that invoke synchronized methods for the same object
//    block (suspend execution) until the first thread is done with the object.
//    Second, when a synchronized method exits, it automatically establishes a happens-before
//    relationship with any subsequent invocation of a synchronized method for the same object.
//    This guarantees that changes to the state of the object are visible to all threads.

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        //we need a loop that performs 2 things:
        // - it checks whether enough time has passed (1/60 sec) to refresh the game
        //  - checks whether enough time has passed (1 sec) to refresh the FPS counter

        // while 'running' the loop adds the time it took to go through one iteration of the
        // loop then adds it to delta (which is simplified to 1) so once it reaches
        // 1 delta it means enough time has passed to go forward one tick.

        long lastTime = System.nanoTime(); // start time to measure time passed
        double amountOfTicks = 60.0; // ticks per second
        double ns = 1000000000 / amountOfTicks; // nanoseconds between ticks
        double delta = 0; // time between ticks
        long timer = System.currentTimeMillis();
        int frames = 0; // number of frames per second
        int tickNumber = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; // update delta to time passed since last iter of loop
            lastTime = now;

            // while we are behind on game ticks
            while (delta >= 1) {
//                System.out.println("behind");
                tick(); // make it tick
                delta--;
                tickNumber++;
            }

            // redraw screen
            if (running) // maybe we should only render if an update has occured or a tick?
            {
                if(state == STATE.GAME) {
                    render();



                    if (hud_display.getNumberOfLives() == 0) {
                        ResetGame();
                        state = STATE.MENU;
                    }
                    else if (playerOne.hit == true)
                        ResetLife();
                }else if(state == STATE.MENU)
                {
                    renderMenu();

                }

            }
            // increase fps by one
//            The frame++ is a counter variable for the Frames per second that the video card is outputting.
//            The actual game's internal tick clock is running at 60 updates per second but in this instance,
//            the render method is not limited to a specific frame rate, so it's running as fast as the graphics card
//            can handle. The ticks and the actual frames per second are two different things.

            frames++;

            // if one second has passed
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames); // display FPS
                frames = 0; // reset FPS counter to one
                //System.out.println("ticknumber: " + tickNumber);
                tickNumber = 0;
            }
        }
        stop();
    }

    private void renderMenu()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        menu.render(g);
        g.dispose();
        bs.show();

    }
    private void tick(){

        //if(!paused){
        if(state == STATE.GAME) {
            handler.tick();
            spawner.tick();
        }
       // }

    }
    public enum STATE{
        MENU,
        GAME
    };
    public static STATE state = STATE.MENU;

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //g.setColor(Color.darkGray);
        //g.fillRect(0, 0, WIDTH, HEIGHT);
        bckgrd.render(g);

//        hilz.render(g, hud_display.getScore());
        hud_display.render(g);
        handler.render(g);


        g.dispose();
        bs.show();
    }
    public static int clamp(int var, int min, int max){
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }
    public void ResetLife() {
        for (int i = 0; i < handler.object.size(); i++) {
            handler.object.get(i).reset();
        }
       // paused = true;
        try {
            // thread to sleep for 1000 milliseconds
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void ResetGame(){

        spawner.setStartTime();
        hud_display.reset();
        for(int i = 0; i < handler.object.size(); i++)
        {
            handler.object.get(i).reset();
        }
        try {
            // thread to sleep for 1000 milliseconds
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
       // paused = true;
    }

    public static void Music()
    {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/edm_music.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);

    }






    public static void main(String[] args) {

        new Game();
    }

}
