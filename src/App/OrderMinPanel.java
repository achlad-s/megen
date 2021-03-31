package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OrderMinPanel extends JPanel {

    JButton zurueck = new JButton();
    JButton erstellen = new JButton();
    JButton ordnerSuchen = new JButton();
    JLabel ordnerText = new JLabel();
    JLabel speicherplatzSuche = new JLabel();
    JPanel back;
    JPanel mainContainer;
    JPanel main;
    JFileChooser chooser = new JFileChooser();
    String choosertitle;
    String sp;
    JTextField name;


    public OrderMinPanel() {


        ordnerSuchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle(choosertitle);
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false); // Schalltet "Alle Ordner" Option ab

                if (chooser.showOpenDialog(ordnerSuchen) == JFileChooser.APPROVE_OPTION) {
                    sp = chooser.getSelectedFile().toString();
                    speicherplatzSuche.setText(sp);
                    System.out.println("getCurrentDirectory(): "
                            +  chooser.getCurrentDirectory());
                    System.out.println("getSelectedFile() : "
                            +  sp);
                }
                else {
                    System.out.println("No Selection ");
                }

            }
        });

        Font newLabelFont=new Font(ordnerText.getFont().getName(),Font.BOLD,ordnerText.getFont().getSize());
        ordnerText.setText("Zielordner:");
        ordnerText.setFont(newLabelFont);
        ordnerSuchen.setText("Suchen");

        mainContainer = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        mainContainer.setLayout(gbl);
        mainContainer.setBackground(Color.lightGray);

        main = new JPanel();
        name = new JTextField();
        GroupLayout group = new GroupLayout(main);
        group.setAutoCreateGaps(true);
        group.setAutoCreateContainerGaps(true);
        main.setLayout(group);
        main.add(erstellen);
        main.add(ordnerSuchen);
        main.add(ordnerText);
        main.add(speicherplatzSuche);
        main.add(name);

        erstellen.setText("Minimalorder(s) erstellen");
        erstellen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tName = name.getText();
                File myObj = new File(sp+"\\"+tName+".edi");
                String path = myObj.getPath();
                System.out.println("Speicherort: "+myObj.getAbsolutePath());
                try {
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                        try (FileOutputStream fos = new FileOutputStream(path)) {
                            String text = "Today is a beautiful day";
                            byte[] mybytes = text.getBytes();
                            fos.write(mybytes);
                            JOptionPane.showMessageDialog(null, "Nachricht(en) wurde(n) erstellt!", "Abgeschlossen", JOptionPane.INFORMATION_MESSAGE);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            System.out.println("An error occurred.");
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("Faaaaaail.");
                        JOptionPane.showMessageDialog(null, "Nachricht wurde nicht erstellt!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });
        group.setHorizontalGroup(group.createSequentialGroup()
                .addComponent(ordnerText)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(speicherplatzSuche)
                        .addComponent(name)
                        .addComponent(erstellen))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(ordnerSuchen))
                );


        group.setVerticalGroup(group.createSequentialGroup()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(ordnerText)
                .addComponent(speicherplatzSuche)
                .addComponent(ordnerSuchen))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(name))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(erstellen))
        );

        main.setForeground(Color.green);
        mainContainer.add(main);

        back = new JPanel();
        back.setBackground(Color.black);
        zurueck.setText("Zurück");
        back.add(zurueck);
        back.setLayout(new FlowLayout(FlowLayout.RIGHT));

        this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());
        this.add(back, BorderLayout.SOUTH);
        this.add(mainContainer, BorderLayout.CENTER);
    }
}
