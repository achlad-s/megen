package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    JButton minimalOrder = new JButton();
    JButton maximalOrder = new JButton();
    JButton test = new JButton("Test");
    GridBagLayout gbl = new GridBagLayout();





    public MainPanel (){

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.weighty = 1;

        this.setLayout(gbl);
        minimalOrder.setText("Minimalorder erstellen");
        maximalOrder.setText("Maximalorder erstellen");

        this.add(minimalOrder, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.1;

        this.add(maximalOrder, gbc);



        this.setBackground(Color.lightGray);

    }


}
