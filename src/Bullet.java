import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Reaper on 5/31/17.
 */
public class Bullet extends GameObject {
    Handler  handler;
    HUD hud;
    MusicPlayer hit_enemy;


    private File myfile = new File("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/stacks.png");
    private BufferedImage image = new BufferedImage(50, 50, 1);
    public Bullet(int x, int y, ID id, Handler handler, HUD hud){
        super(x+2, y, id);
        velY = -10;
        this.handler = handler;
        this.hud = hud;
        try {
            image = ImageIO.read(myfile);
        }catch(Exception e){
            System.out.println(e);
        }
        Music();
        hit_enemy = new MusicPlayer("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/hit_trump.wav");


    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        checkCollision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        //g.fillOval(x, y, 10, 10);
        g.drawImage(image, x-5, y-5, null);


    }
    @Override
    public Rectangle getBounds() {

        return new Rectangle(x, y, 20, 20);
    }

    @Override
    public void reset() {
        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tmp = handler.object.get(i);
            if (tmp.getId() == ID.Bullet)
                handler.removeObject(tmp);
        }

    }

    public void checkCollision(){
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tmp = handler.object.get(i);
            if (tmp.getY() <= 0 && tmp.getId() == ID.Bullet)
                handler.removeObject(tmp); // clean run away bullets
            if(tmp.getId() == ID.Trump) {
                if (getBounds().intersects((tmp.getBounds()))) {
//                    // collision with enemy
                    hud.increaseScore(1);
                    handler.removeObject(tmp);
//                    tmp.reset();
                    handler.removeObject(this);
                    hit_enemy.Play();
                }
            }
            if(tmp.getId() == ID.Spicer ){
                if (getBounds().intersects((tmp.getBounds()))){
//                   // collision with enemy
                    hud.increaseScore(10);
                    handler.removeObject(tmp);
                    //tmp.reset();
                    handler.removeObject(this);
                    hit_enemy.Play();
                }
            }

        }
    }
    private void insertDelay(final int DELAY_TIME) {
        try {
            Thread.sleep(DELAY_TIME);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    public static void Music()
    {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/shoot.wav");
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
    }

