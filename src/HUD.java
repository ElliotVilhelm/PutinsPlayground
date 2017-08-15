import java.awt.*;

/**
 * Created by Reaper on 5/31/17.
 */
public class HUD {

    int numberOfLives;
    int x;
    int y;
    private int score = 0;
    Hilary hilz = new Hilary(10, 300);

    public HUD(int x, int y, int numLives)
    {
        numberOfLives = numLives;
        this.x = x;
        this.y = y;
    }
    public void render(Graphics g) {
        for (int i = 0; i < numberOfLives; i++)
        {
            g.setColor(Color.red);
            g.fillOval(x + i*20, y, 10, 10);
            g.drawString("Score: " + score, x, y+22);
        }
        hilz.render(g, score);
    }
    public void increaseScore(int increment){
        score += increment;
    }
    public void reset(){
        numberOfLives = 3;
        score = 0;
    }
    public int getScore(){
        return score;
    }
    public void decreaseLife()
    {
        numberOfLives--;
    }
    public void increaseLife()
    {
        numberOfLives++;
    }

    public int getNumberOfLives()
    {
        return numberOfLives;
    }
}
