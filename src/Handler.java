import sun.plugin2.message.GetAppletMessage;

import java.util.*;
import java.awt.Graphics;


/**
 * Created by Reaper on 5/22/17.
 */
public class Handler {

    //    HashMap is an implementation of Map. Map is just an interface for any type of map.
//    public HashMap<ID, Integer> ObjectCounts = new HashMap<ID, Integer>();// = new <ID, Integer>();
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    private Random rnd = new Random();

    int TrumpCount = 0;
    int SpicerCount = 0;
    int KimJongCount = 0;
    int PowerUpLifeCount = 0;


    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObj = object.get(i);
            tempObj.tick();
        }
    }

    public void render(Graphics g) {

        for (int i = 0; i < object.size(); i++) {
            GameObject tempObj = object.get(i);
            tempObj.render(g);
        }
    }

    public void addObject(GameObject object) {
        if (object.getId() == ID.Trump)
            TrumpCount++;
        else if(object.getId() == ID.Spicer)
            SpicerCount++;
        else if(object.getId() == ID.KimJong)
            KimJongCount++;
        else if(object.getId() == ID.Life)
            PowerUpLifeCount++;

        this.object.add(object);

    }


    public void removeObject(GameObject object) {
        if (object.getId() == ID.Trump)
            TrumpCount--;
        else if(object.getId() == ID.Spicer)
            SpicerCount--;
        else if(object.getId() == ID.KimJong)
            KimJongCount--;
        else if(object.getId() == ID.Life)
            PowerUpLifeCount--;
        this.object.remove(object);
    }


    public void setSpicers(int num) {
        for (int i = SpicerCount; i < num; i++) {
            addObject(new Enemy_Spicey(rnd.nextInt(Game.WIDTH), rnd.nextInt(200) - 200, ID.Spicer));
        }

    }

    public void setTrumps(int num) {

        for (int i = TrumpCount; i < num; i++) {
            addObject(new Enemy(rnd.nextInt(Game.WIDTH), rnd.nextInt(200) - 200, ID.Trump, 2));
        }
    }


    public void setKimJong(int num, Handler handler, HUD hud) {
        for (int i = KimJongCount; i < num; i++) {
            addObject(new Enemy_Boss1(Game.WIDTH/2 -50 + i*100, 20, ID.KimJong, handler, hud));
        }
    }
    public void setPowerUpLife(int num) {
        for (int i = PowerUpLifeCount; i < num; i++) {
            addObject(new PowerUp(Game.WIDTH / 2, -10, ID.Life));
        }
    }
}
