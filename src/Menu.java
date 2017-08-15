import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Reaper on 6/1/17.
 */
public class Menu {
    public Rectangle playButton = new Rectangle(Game.WIDTH/ 2 + 60, 250, 100, 50);
    public Rectangle helpButton = new Rectangle(Game.WIDTH/ 2 + 60, 350, 100, 50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH/ 2 + 60, 450, 100, 50);

    private File myfile = new File("/Users/Reaper/Dropbox/CODE/JAVA/Breakout/seal.png");
    private BufferedImage image = new BufferedImage(50, 50, 1);
    public Menu(){
        try {
            image = ImageIO.read(myfile);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void render(Graphics g)
    {
        g.drawImage(image, 50, 200, null);
        Graphics2D g2d = (Graphics2D) g;
        Font font0 = new Font("arial", Font.BOLD, 50);
        g.setFont(font0);
        g.setColor(Color.WHITE);
        g.drawString("PUTIN'S", 50, 100);
        g.drawString("PLAYGROUND", 50, 160);

        Font font1 = new Font("arial", Font.BOLD, 30);
        g.setFont(font1);
        g.drawString("Play", playButton.x+20, playButton.y+40);
        g2d.draw(playButton);
        g.drawString("Help", playButton.x+20, helpButton.y+ 40);
        g2d.draw(helpButton);
        g.drawString("Quit", playButton.x+20, quitButton.y+40);
        g2d.draw(quitButton);





    }
}

