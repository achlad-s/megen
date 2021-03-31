package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    JButton minimalOrder = new JButton();
    GridBagLayout gbll = new GridBagLayout();

    public MainPanel (){

        this.setLayout(gbll);
        minimalOrder.setText("Minimalorder erstellen");
        this.add(minimalOrder);
        this.setBackground(Color.lightGray);

    }


}
