/**
 * Created by Reaper on 6/1/17.
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
    Spawn spawner;

    public MouseInput(Spawn s){
        this.spawner = s;
    }
    @Override
    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();

//        public Rectangle playButton = new Rectangle(Game.WIDTH/ 2, 250, 100, 50);
//        public Rectangle helpButton = new Rectangle(Game.WIDTH/ 2, 350, 100, 50);
//        public Rectangle quitButton = new Rectangle(Game.WIDTH/ 2, 450, 100, 50);

        // play
        if(mx >= Game.WIDTH/2 + 60 && mx <= Game.WIDTH/2+160)
        {
            if(my >= 250 && my <= 300)
            {
                Game.state = Game.STATE.GAME;
                spawner.setStartTime();
            }
        }
        if(mx >= Game.WIDTH/2+ 60 && mx <= Game.WIDTH/2+160)
        {
            if(my >= 350 && my <= 400)
            {
//                Game.state = Game.STATE.GAME;
            }
        }
        if(mx >= Game.WIDTH/2 + 60 && mx <= Game.WIDTH/2+160)
        {
            if(my >= 450 && my <= 500)
            {
                System.exit(1);
            }
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void resetSpawnerTimer()
    {
        spawner.setStartTime();
    }
}
