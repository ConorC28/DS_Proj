package services;

import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;

/**
 * The Class KettleService.
 */
public class KettleService extends Service {

    private final Timer timer;
    public static int percentHot;       // had to change this int to allow the KettleClient to check it
    
    private int percentMade;

    public KettleService(String name) {
        super(name, "_kettle._udp.local.");
        timer = new Timer();
        percentMade = 0;
        percentHot = 0;
    
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Boil")) {
            timer.schedule(new RemindTask1(), 0, 2000);
            sendBack("OK");
            ui.updateArea("Boiling Kettle");
        } 
//        else if (a.equals("Boil")) {
//            timer.schedule(new RemindTask(), 0, 2000);
//            sendBack("OK");
//            ui.updateArea("Boiling Kettle");
//        } 
        else if (a.equals("Makeb")) {
            timer.schedule(new RemindTask2(), 0, 3000);
            sendBack("OK");
            ui.updateArea("Making Bed Tea");
        } 
//        else if (a.equals("Boil")) {
//            timer.schedule(new RemindTask(), 0, 2000);
//            sendBack("OK");
//            ui.updateArea("Boiling Kettle");
//        }
        else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    public class RemindTask1 extends TimerTask {

        @Override
        public void run() {
            if (percentHot < 100) {
                percentHot += 7;
                
            }
            else if(percentHot != 100){     //This else if stops the kettle boiling to 105% percent
                percentHot -= 5;
            }
        }
    }
    
    class RemindTask2 extends TimerTask {

        @Override
        public void run() {
            if  (percentHot < 80){
                ui.updateArea("Kettle is not boiled enough yet");
                
            }else if (percentHot > 80){ 
                
                if (percentMade < 100) {
                percentMade += 7;
            }
            else if(percentMade != 100){     //This else if stops the kettle boiling to 105% percent
                percentMade -= 5;
            }
            }
        }
    }

    @Override
    public String getStatus() {
        return ("Kettle is " + percentHot + "% boiled." 
                + "and" + " Bed Tea is " + percentMade + "% made.");
    }

    public static void main(String[] args) {
        new KettleService("Conor's");
    }
}
