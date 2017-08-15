import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Reaper on 5/22/17.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private HUD hud;
    private boolean[] keyDown = new boolean[3];
    private boolean paused;

    public KeyInput(Handler handler, HUD hud, boolean paused){
        this.handler = handler;
        this.hud = hud;
        for(int i = 0; i < keyDown.length-1; i++)
        {
            keyDown[i] = false;
        }
        this.paused = paused;
    }

    public void keyPressed(KeyEvent e) {
        //super.keyPressed(e);
        int key = e.getKeyCode();
//        System.out.println(key);

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObj = handler.object.get(i);

            if(tempObj.getId() == ID.Player1){
                    if (key == KeyEvent.VK_ENTER)
                    {
                        paused = false;
                        break;
                    }
                    if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                        tempObj.setVelX(-10);
                        keyDown[0] = true;
                    }

                    if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                        tempObj.setVelX(10);
                        keyDown[1] = true;
                    }
                    if (key == KeyEvent.VK_SPACE) {
                        handler.addObject(new Bullet(tempObj.getX(), tempObj.getY() + 20, ID.Bullet, handler, hud));
                        keyDown[2] = true;
                    }
            }


        }
//        System.out.println(key);
    }

    public void keyReleased(KeyEvent e) {
        //super.keyReleased(e);
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObj = handler.object.get(i);
            if(tempObj.getId() == ID.Player1){
                if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
                    tempObj.setVelX(0);
                    keyDown[0] = false;
                }

                if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                    tempObj.setVelX(0);
                    keyDown[1] = false;
                }
                if(key == KeyEvent.VK_SPACE) {
                    keyDown[2] = false;
                }
            }
        }
    }

}
