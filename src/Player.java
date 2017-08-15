/**
 * Created by Reaper on 5/22/17.
 */
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import sun.security.jgss.GSSCaller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

//import java.awt.Image;

// I AM A CHILD OF GAME OBJECT !



public class Player extends GameObject {


    public boolean hit = false;

    private int player_width = 50;
    private int player_height = 100;


    private File myfile1 = new File("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/putin.png");
    private BufferedImage image_putin = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);

    MusicPlayer lifeLost = new MusicPlayer("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/lifelost.wav");
    MusicPlayer lifeGained = new MusicPlayer("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/extralife.wav");




    Handler handler;
    Color myColor;
    HUD hud;


    public Player(int x, int y, ID id, Handler handler, HUD hud){
        super(x, y, id); // call parent constructor
        this.handler = handler;
        this.myColor = Color.cyan;
        this.hud = hud;

    }
    public void loadImage()
    {
        try {
            image_putin = ImageIO.read(myfile1);
        }catch(Exception e){
            System.out.println(e);
        }

    }
    public void tick(){
        x += velX;
        y += velY;
        x = Game.clamp(x,2,Game.WIDTH-50);
        checkCollision();
    }
    private void checkCollision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tmp = handler.object.get(i);
            if (tmp.getId() == ID.Trump || tmp.getId() == ID.Spicer) {
                if (getBounds().intersects((tmp.getBounds())) && tmp.getY() < 530) {
                    // collision with enemy
                    //myColor = Color.red;
                    hud.decreaseLife();
                    hit = true;
                    lifeLost.Play();

                }
            }

            if (tmp.getId() == ID.Life) {
                if (getBounds().intersects((tmp.getBounds())) && tmp.getY() < 530) {
                    // collision with enemy
                    //myColor = Color.red;
                    hud.increaseLife();
                    handler.removeObject(tmp);
                    //hit = true;
                    lifeGained.Play();
                }
            }


        }
    }



    public void render(Graphics g){


//        if(id == ID.Player1)
//            g.setColor(myColor);
        g.setColor(Color.green);
        g.drawImage(image_putin, x-8, y-10, null);

       // g.fillRect(x, y, player_width, player_height);
    }


    public Rectangle getBounds()
    {
        return new Rectangle(x,y,player_width, player_height);
    }

    @Override
    public void reset() {
        hit = false;
    }
}

