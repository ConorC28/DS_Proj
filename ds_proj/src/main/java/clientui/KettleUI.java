package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.KettleClient;

public class KettleUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton boil;
    private JButton Makeb;
    private final KettleClient parent;

    public KettleUI(KettleClient kettleClient) {
        super(kettleClient);
        parent = kettleClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        boil = new JButton("Boil");
        Makeb = new JButton("Make Bed Tea"); 
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{boil});
        add(new JButton[]{Makeb});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boil) {
            parent.boil();
        } else if (e.getSource() == Makeb){
            parent.makebedtea();
        }
    }
}
