import com.sun.tools.javadoc.Start;

/**
 * Created by Reaper on 5/31/17.
 */
public class Spawn {
    private Handler handler;
    private HUD hud_display;
    private int Max_Height = Game.HEIGHT;
    public long StartTime;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud_display = hud;
        StartTime = System.currentTimeMillis();
    }

    public void tick() {
//
        if ((System.currentTimeMillis() - StartTime)/1000 == 2 )
        {
            System.out.println("Wave 1 fired");
            handler.setTrumps(5);
           // handler.setKimJong(1, handler);
        }
        else if ((System.currentTimeMillis() - StartTime)/1000 == 5) {
            System.out.println("Wave 2 fired");
            handler.setTrumps(4);
        }
        else if ((System.currentTimeMillis() - StartTime)/1000 == 10) {
            System.out.println("Wave 3 fired");
            handler.setTrumps(4);
            handler.setSpicers(2);
        }
        else if ((System.currentTimeMillis() - StartTime)/1000 == 15) {
            System.out.println("Wave 4 fired");
//            handler.setTrumps(4);
            handler.setSpicers(6);
        }
        else if ((System.currentTimeMillis() - StartTime)/1000 == 20) {
//            System.out.println("Wave 2 fired");
            handler.setTrumps(5);
            handler.setSpicers(5);
        }
        else if ((System.currentTimeMillis() - StartTime)/1000 == 25) {
//            System.out.println("Wave 2 fired");
            handler.setKimJong(1, handler, hud_display);
        }
        else if ((System.currentTimeMillis() - StartTime)/1000 == 40) {
//            System.out.println("Wave 2 fired");
            handler.setSpicers(5);
        }
        else if ((System.currentTimeMillis() - StartTime)/1000 == 55) {
//            System.out.println("Wave 2 fired");
            handler.setTrumps(8);

        }
        else if ((System.currentTimeMillis() - StartTime)/1000 == 60) {
//            System.out.println("Wave 2 fired");
            handler.setTrumps(5);
        }
        else if ((System.currentTimeMillis() - StartTime)/1000 == 65) {
//            System.out.println("Wave 2 fired");
            handler.setPowerUpLife(1);
        }
        else if ((System.currentTimeMillis() - StartTime)/1000 == 68) {
//            System.out.println("Wave 2 fired");
            handler.setKimJong(2, handler, hud_display);
        }




////        else if ((System.currentTimeMillis() - StartTime)/1000 == 15) {
//////            handler.setTrumps(2);
//////            handler.setSpicers(2);
////            handler.setSpicers(10);
////        }
//        else if ((System.currentTimeMillis() - StartTime)/1000 == 10) {
//            System.out.println("Wave 4 fired");
//
////            handler.setTrumps(2);
////            handler.setSpicers(2);
//            handler.setKimJong(1, handler);
//        }


//        // make objects come back around  * notice trumps come back spicers dont * they are 10 points
//        for (int i= 0; i < handler.object.size(); i++)
//        {
//            GameObject tmp = handler.object.get(i);
//            if (tmp.getId() == ID.Trump)// || tmp.getId() == ID.Spicer)
//            {
//                if(tmp.getY() > Max_Height)//-80)
//                {
//                    tmp.setY(0);
//                }
//            }
//        }

        // make objects come back around  * notice trumps come back spicers dont * they are 10 points
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tmp = handler.object.get(i);
            if (tmp.getY() > Max_Height-20)//-80)
            {
                handler.removeObject(tmp);
            }

        }

    }
    public void setStartTime(){
        StartTime = System.currentTimeMillis();
        System.out.println("yesyes");
    }
}



