import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * Created by Reaper on 6/1/17.
 */

public class Hilary  {
    private Random rnd = new Random();
    private File myfile = new File("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/hilary.png");
    private BufferedImage image = new BufferedImage(50, 50, 1);
    private int x_size = 60;
    private int y_size = 80;
    private int framesOn = 0;
    private int x;
    private int y;

    private boolean draw = false;
    private int score;


    public Hilary(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(myfile);
        }catch(Exception e){
            System.out.println(e);
        }
    }



    public void render(Graphics g, int score) {
        if((score) % 50 == 0 && draw == false && score != 0) {
            Music();
            draw = true;
        }
        if (draw == true && framesOn < 500) {
            g.drawImage(image, x - 10, y - 10, null);
            //g.setColor(Color.green);
            framesOn++;
        }
        if(framesOn >= 500)
        {
            framesOn = 0;
            draw = false;
        }
        //g.fillRect(x,y, x_size, y_size);
    }


    public void reset()
    {

    }

    public Image getImage(){
        return image;
    }

    public static void Music()
    {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/groan.wav");
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

