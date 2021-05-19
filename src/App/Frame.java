package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame{

    //Einfügen aller nötigen Panels
    OrderMinPanel orderMin = new OrderMinPanel();
    OrderMaxPanel orderMax = new OrderMaxPanel();
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
        ImageIcon logo = new ImageIcon("SupplyOn Logo.png"); //Erstelle Image icon -> SupplyOn Logo
        this.setIconImage(logo.getImage()); //Ändere das Icon zum SupplyOnLogo
        this.getContentPane().setBackground(Color.lightGray); //(new Color(r,g,b))

        //Actionlistener für das durchswitchen des Cardlayouts.

        mainPanel.minimalOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "2");
            }
        });

        mainPanel.maximalOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "3");
            }
        });

        orderMin.zurueck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "1");
            }
        });

        orderMax.zurueck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "1");
            }
        });
        panelContainer.setLayout(cl);
        panelContainer.add(mainPanel,"1");
        panelContainer.add(orderMin,"2");
        panelContainer.add(orderMax, "3");
        cl.show(panelContainer, "1");




    }
}
