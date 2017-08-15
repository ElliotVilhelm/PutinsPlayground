import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * Created by Reaper on 6/1/17.
 */

public class Enemy_Spicey extends GameObject {
    private Random rnd = new Random();
    private File myfile = new File("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/spicer.png");
    private BufferedImage image = new BufferedImage(50, 50, 1);
    private int x_size = 60;
    private int y_size = 80;



    public Enemy_Spicey(int x, int y, ID id) {
        super(x,y,id);
        velY = 1;
        try {
            image = ImageIO.read(myfile);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void tick() {
        int randomnumber = rnd.nextInt(100 + 1 + 100) - 100;
//
        if (randomnumber % 29 == 0)
        { x += randomnumber;}
        y += velY;
        x = Game.clamp(x,2,Game.WIDTH-50);

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(image, x-10, y-10, null);
        g.setColor(Color.green);
        //g.fillRect(x,y, x_size, y_size);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,x_size,y_size);
    }

    public void reset()
    {
        setY(rnd.nextInt(100)-1000);
        setX(rnd.nextInt(600)-300);

    }

    public Image getImage(){
        return image;
    }

    private void insertDelay(final int DELAY_TIME) {
        try {
            Thread.sleep(DELAY_TIME);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}

