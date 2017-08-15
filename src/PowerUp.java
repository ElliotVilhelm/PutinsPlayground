import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * Created by Reaper on 6/1/17.
 */
public class PowerUp extends GameObject {
    private Random rnd = new Random();

    private File myfile = new File("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/powerup_life.png");
    private BufferedImage image = new BufferedImage(50, 50, 1);
    public PowerUp(int x, int y, ID id){
        super(x,y,id);
        velY = 2;
        try {
            image = ImageIO.read(myfile);
        }catch(Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void tick() {
       // x += velX;
        y += velY;
//        int new_x = Game.clamp(x,2,Game.WIDTH-50);
//        if (new_x != x)
//        {
//            velX *= -1;
//        }
//        x = new_x;

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(image, x-5, y-5, null);
        g.setColor(Color.green);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y, 50, 50);
    }

    @Override
    public void reset() {
//        setY(rnd.nextInt(100)-200);
//        setX(rnd.nextInt(400));
//        if(rnd.nextInt(11) % 5 == 0) {
//            setVelY(rnd.nextInt(3)+2);
//            setVelX(rnd.nextInt(5) - 5);
//        }

//        else

        setVelX(0);

    }
}
