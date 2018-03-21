package services;

import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;

/**
 * The Class KettleService.
 */
public class KettleService extends Service {

    private final Timer timer;
    private int percentHot;

    public KettleService(String name) {
        super(name, "_kettle._udp.local.");
        timer = new Timer();
        percentHot = 0;
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Boil")) {
            timer.schedule(new RemindTask(), 0, 2000);
            sendBack("OK");
            ui.updateArea("Boiling Kettle");
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTask extends TimerTask {

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

    @Override
    public String getStatus() {
        return "Kettle is " + percentHot + "% boiled.";
    }

    public static void main(String[] args) {
        new KettleService("Conor's");
    }
}
