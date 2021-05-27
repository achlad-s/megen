package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    JButton minimalOrder = new JButton();
    JButton maximalOrder = new JButton();
    JPanel main;
    JPanel container;
    BorderLayout bl = new BorderLayout();
    





    public MainPanel (){

        this.setLayout(bl);

        container = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        container.setLayout(gbl);
        container.setBackground(Color.lightGray);

        //Tooltip für Buttons und Grundgerüst des Hauptbildschirms
        main = new JPanel();
        main.setBackground(Color.lightGray);
        GroupLayout gl = new GroupLayout(main);
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);
        this.add(container, BorderLayout.CENTER);
        container.add(main);
        main.add(minimalOrder);
        main.add(maximalOrder);
        minimalOrder.setText("Minimalorder erstellen");
        minimalOrder.setToolTipText("Order mit ausschließlich Pflichtfeldern");
        maximalOrder.setText("Maximalorder erstellen");
        maximalOrder.setToolTipText("Order bei der auch alle optionalen Felder befüllt sind.");

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(minimalOrder)
                .addComponent(maximalOrder));

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(minimalOrder))
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(maximalOrder)));

        this.setBackground(Color.lightGray);

    }


}
