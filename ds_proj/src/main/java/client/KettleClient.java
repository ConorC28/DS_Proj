/*
 * 
 */
package client;

import clientui.KettleUI;

/**
 * Bed Client.
 *
 * @author dominic
 */
public class KettleClient extends Client {

    private final String BOIL = "Boil";
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
     * sends a message to warm the bed.
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

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Kettle is 100% boiled.")) {
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
