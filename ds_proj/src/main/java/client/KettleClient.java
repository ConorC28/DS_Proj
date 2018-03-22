/*
 * 
 */
package client;

import services.KettleService;
import clientui.KettleUI;
import serviceui.ServiceUI;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Bed Client.
 *
 * @author dominic
 */
public class KettleClient extends Client {

    private final String MAKEB = "Makeb";    
    private final String BOIL = "Boil";
    private boolean isMakingB = false;
    private boolean isBoiling = false;
    

    /**
     * Bed Client Constructor.
     */
    public KettleClient() {
        super();
        serviceType = "_kettle._udp.local.";
        ui = new KettleUI(this);
        name = "Kitchen";
    }

    /**
     * sends a message to boil the kettle.
     */
    public void boil() {
        if (!isBoiling) {
            String a = sendMessage(BOIL);
            if (a.equals(OK)) {
                isBoiling = true;
                ui.updateArea("Kettle is Boiling");
            }
        } else {
            ui.updateArea("Kettle already Boiled");
        }
    }

//    @Override
//    public void updatePoll(String msg) {
//        if (msg.equals("Kettle is 100% boiled.")) {
//            isBoiling = false;
//        }
//    }
    
    public void makebedtea() {
        
        
            if (!isMakingB) {
                String a = sendMessage(MAKEB);
                
                if (a.equals(OK)) {
                    isMakingB = true;
                    ui.updateArea("Making bed tea.");
                }
            } else {
                ui.updateArea("Already made your bed tea");
            }
        
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Already made your bed tea.")) {
            isMakingB = false;
        }
        else if (msg.equals("The kettle is not boiled enough.")){
            isMakingB = false;
        }
        else if (msg.equals("Kettle is 100% boiled.")) {    // Include kettle update
            isBoiling = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new KettleUI(this);
        isBoiling = false;
    }
}
