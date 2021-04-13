package App;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main (String[] args) {

     /*   JFrame main = new JFrame();
        main.setTitle("Megen"); //Name der App
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Schließt App bei click auf X
        main.setResizable(true);
        main.setSize(1600,900);
        main.setVisible(true);

        ImageIcon logo = new ImageIcon("SupplyOn Logo.png"); //create Image icon
        frame.setIconImage(logo.getImage()); //change Icon of Main
        main.getContentPane().setBackground(Color.lightGray); //(new Color(r,g,b)) */

     //Erstellt das Fenster, in dem das Programm gezeigt wird
     Frame frame = new Frame();
        ImageIcon logo = new ImageIcon("SupplyOn Logo.png"); //create Image icon
        frame.setIconImage(logo.getImage()); //change Icon of Main
     //Windowlistener nötig für die Suche des Ablageordners im System
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

    }

}
