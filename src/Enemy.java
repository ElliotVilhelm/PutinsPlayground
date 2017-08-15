import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

//import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * Created by Reaper on 5/31/17.
 */
public class Enemy extends GameObject {
    private Random rnd = new Random();
    private File myfile = new File("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/trump.png");
    private BufferedImage image = new BufferedImage(50, 50, 1);


    public Enemy(int x, int y, ID id, int x_vel) {
        super(x,y,id);
//        ImageIcon ii = new ImageIcon("trump.jpg");
        velY = 3;
        velX = x_vel;
        try {
            image = ImageIO.read(myfile);
        }catch(Exception e){
            System.out.println(e);
        }

        if(x_vel != 0 && rnd.nextInt(11) % 5 == 0) {
            setVelY(rnd.nextInt(3)+2);
            setVelX(rnd.nextInt(5) - 5);
        }

    }


    @Override
    public void tick() {
        //int randomnumber = rnd.nextInt(100 + 1 + 100) - 100;
//
//        if (randomnumber % 29 == 0|| randomnumber % 17 == 0)
//        { x += randomnumber;}
        x += velX;
        y += velY;
        int new_x = Game.clamp(x,2,Game.WIDTH-50);
        if (new_x != x)
        {
            velX *= -1;
        }
        x = new_x;

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(image, x-5, y-5, null);
        g.setColor(Color.green);
       // g.fillRect(x,y, 40, 30);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,40,30);
    }

    public void reset()
    {
        setY(rnd.nextInt(100)-200);
        setX(rnd.nextInt(400));
        if(rnd.nextInt(11) % 5 == 0) {
            setVelY(rnd.nextInt(3)+2);
            setVelX(rnd.nextInt(5) - 5);
        }

        else
            setVelX(0);
    }

    public Image getImage(){
        return image;
    }
}
