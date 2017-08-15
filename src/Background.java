import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * Created by Reaper on 6/1/17.
 */
public class Background {

    private Random rnd = new Random();
    private File myfile = new File("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/background3.jpg");
    private BufferedImage image = new BufferedImage(50, 50, 1);

    private boolean draw = false;
    private int score;


    public Background() {
        try {
            image = ImageIO.read(myfile);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void render(Graphics g) {
            g.drawImage(image, 0, 0, null);

    }
}

