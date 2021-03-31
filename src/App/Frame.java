package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame{

    //Einfügen aller nötigen Panels
    OrderMinPanel orderMin = new OrderMinPanel();
    MainPanel mainPanel = new MainPanel();
    JPanel panelContainer = new JPanel();
    //Card Layout um zwischen Panels zu wechseln per Buttonklick
    CardLayout cl = new CardLayout();


    public Frame() {
        this.add(panelContainer);
        this.setSize(1600,900);
        this.setTitle("Megen"); //Name der App
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Schließt App bei click auf X
        this.setResizable(true); //Größe ist veränderbar
        this.setVisible(true); //Frame ist sichtbar
        ImageIcon logo = new ImageIcon("SupplyOn Logo.png"); //create Image icon -> SupplyOn Logo
        this.setIconImage(logo.getImage()); //change Icon of this
        this.getContentPane().setBackground(Color.lightGray); //(new Color(r,g,b))


        mainPanel.minimalOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               cl.show(panelContainer, "2");
            }
        });

        orderMin.zurueck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "1");
            }
        });
        panelContainer.setLayout(cl);
        panelContainer.add(mainPanel,"1");
        panelContainer.add(orderMin,"2");
        cl.show(panelContainer, "1");




    }
}
