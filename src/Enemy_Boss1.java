/**
 * Created by Reaper on 6/2/17.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * Created by Reaper on 5/31/17.
 */
public class Enemy_Boss1 extends GameObject {
    private Random rnd = new Random();
    private File myfile = new File("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/kim.png");
    private BufferedImage image = new BufferedImage(50, 50, 1);
    public Handler handler;
    public int health;
    private int x_size = 100;
    private int y_size = 100;
    MusicPlayer hitEffect;

    Timer trump_timer = new Timer( );
    Timer spicer_timer = new Timer( );

    HUD hud;




    public Enemy_Boss1(int x, int y, ID id, Handler hndl, HUD hud) {
        super(x,y,id);
        this.health = 50;
//        ImageIcon ii = new ImageIcon("trump.jpg");
        //velY = 2;
        try {
            image = ImageIO.read(myfile);
        }catch(Exception e){
            System.out.println(e);
        }
        this.handler = hndl;
        this.hitEffect = new MusicPlayer("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/hit.wav");
        setVelX(3);
        setVelY(2);

        this.hud = hud;


        trump_timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
//                handler.addObject(new Enemy(rnd.nextInt(Game.WIDTH), rnd.nextInt(200), ID.Trump));
//                handler.addObject(new Enemy(rnd.nextInt(Game.WIDTH), rnd.nextInt(200), ID.Trump));
//                handler.addObject(new Enemy(rnd.nextInt(Game.WIDTH), rnd.nextInt(200), ID.Trump));
                int rnd_x = rnd.nextInt(Game.WIDTH);
                handler.addObject(new Enemy(getX() + 30, y, ID.Trump, 0));
                handler.addObject(new Enemy(getX() -30, y, ID.Trump, 0));
                handler.addObject(new Enemy(getX() , y+30, ID.Trump, 0));
                handler.addObject(new Enemy(getX() , y-30, ID.Trump, 0));


            }
        }, 1000,1500);
        spicer_timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

//                handler.addObject(new Enemy_Spicey(x, y, ID.Spicer));
            }
        }, 1000,10000);
    }


    @Override
    public void tick() {
        //int randomnumber = rnd.nextInt(100 + 1 + 100) - 100;
//
//        if (randomnumber % 29 == 0|| randomnumber % 17 == 0)
//        { x += randomnumber;}
        x += velX;
        y += velY;
        int new_x = Game.clamp(x,2,Game.WIDTH-100);
        int new_y = Game.clamp(y,2,Game.HEIGHT-400);

        if (new_x != x)
            velX *= -1;
        if (new_y != y)
            velY *= -1;
        x = new_x;

        checkCollision();

        if (health == 20) {
            if (velX < 0)
                velX = -10;
            else
                velX = 10;
            if(velY < 0)
                velY = -10;
            else
                velY = 10;
        }


    }

    @Override
    public void render(Graphics g) {

        g.drawImage(image, x-10, y-5, null);
        g.setColor(Color.green);
//        g.fillRect(x,y, x_size, y_size);
        HealthBar(g);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,x_size,y_size);
    }

    public void reset()
    {
//        setX(Game.WIDTH/2);
//        setY(20);
//        setVelX(5);
    }
    public void HealthBar(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(x, y+150, 100, 30);
        g.setColor(Color.green);
        g.fillRect(x, y+150, health*2, 30);
    }
    public void ReduceHealth()
    {
        health--;
    }

    public void checkCollision(){
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tmp = handler.object.get(i);
            if(tmp.getId() == ID.Bullet) {
                if (getBounds().intersects((tmp.getBounds()))) {
                    hitEffect.Play();

//                    // collision with enemy
                    handler.removeObject(tmp); // remove bullet
                    if(health > 0) {
                        ReduceHealth();
                    }
                    else {
                        handler.removeObject(this);
                        hud.increaseScore(100);
                        trump_timer.cancel();
                    }
//                    tmp.reset();
//                    handler.removeObject(this);
                }
            }


        }
    }

    public Image getImage(){
        return image;
    }
}

