package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderMaxPanel extends JPanel {

    int success = 0;
    int fail = 0;

    JButton zurueck = new JButton();
    JButton erstellen = new JButton();
    JButton ordnerSuchen = new JButton();
    JLabel ordnerText = new JLabel();
    JLabel speicherplatzSuche = new JLabel();
    JLabel userKuerzel = new JLabel("Userkürzel:");
    JLabel orgCode = new JLabel("OrgCode:");
    JLabel mailPartner = new JLabel("Mailpartner:");
    JLabel sellerNo = new JLabel("Sellerno.:");
    JLabel plantCode = new JLabel("Werkscode");
    JLabel unloadingPoint = new JLabel("Abladestelle");
    JLabel unitOfMeasure = new JLabel("Maßeinheit");
    JLabel dateiCount = new JLabel("Anzahl Dateien");
    JLabel positionenCount = new JLabel("Anzahl Positionen:");
    JLabel bestellmenge = new JLabel("Bestellmenge/Position:");
    JLabel waehrung = new JLabel("Währung:");
    JLabel lieferung = new JLabel("Lieferung:");
    JLabel rechnung = new JLabel("Rechnung?");
    JLabel antwort = new JLabel("Bestellantwort?");
    JLabel idStart = new JLabel("Startnummer");
    JLabel emptyLabel =new JLabel("");
    JPanel back;
    JPanel mainContainer;
    JPanel main;
    JFileChooser chooser = new JFileChooser();
    String choosertitle;
    String sp;
    JTextField userKuerzelText = new JTextField();
    JTextField orgCodeText = new JTextField("SCMPD");
    JTextField mailPartnerText = new JTextField("TSTOBLE");
    JTextField sellerNoText = new JTextField("02_WEB");
    JTextField plantCodeText = new JTextField("ASN_04");
    JTextField unloadingPointText = new JTextField("Warehouse");
    JTextField unitOfMeasureText = new JTextField("EA");
    JTextField dateiCountText = new JTextField("1");
    JTextField positionenCountText = new JTextField("1");
    JTextField bestellmengeText = new JTextField("1");
    JTextField waehrungText = new JTextField("EUR");
    JTextField lieferungText = new JTextField("DY");
    JTextField rechnungText = new JTextField("J");
    JTextField antwortText = new JTextField("J");
    JTextField idStartText = new JTextField("1");





    public OrderMaxPanel() {

        //Wichtig für die eindeutige Dateibenennung
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String strDate = dateFormat.format(date);

        ordnerSuchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.setCurrentDirectory(new File("."));
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
        GroupLayout group = new GroupLayout(main);
        group.setAutoCreateGaps(true);
        group.setAutoCreateContainerGaps(true);
        main.setLayout(group);
        main.add(erstellen);
        main.add(ordnerSuchen);
        main.add(ordnerText);
        main.add(speicherplatzSuche);
        main.add(userKuerzel);
        main.add(orgCode);
        main.add(mailPartner);
        main.add(sellerNo);
        main.add(plantCode);
        main.add(unloadingPoint);
        main.add(unitOfMeasure);
        main.add(idStart);
        main.add(dateiCount);
        main.add(positionenCount);
        main.add(bestellmenge);
        main.add(waehrung);
        main.add(lieferung);
        main.add(rechnung);
        main.add(antwort);

        main.add(emptyLabel);

        main.add(userKuerzelText);
        main.add(orgCodeText);
        main.add(mailPartnerText);
        main.add(sellerNoText);
        main.add(plantCodeText);
        main.add(unloadingPointText);
        main.add(unitOfMeasureText);
        main.add(idStartText);
        main.add(dateiCountText);
        main.add(positionenCountText);
        main.add(bestellmengeText);
        main.add(waehrungText);
        main.add(lieferungText);
        main.add(rechnungText);
        main.add(antwortText);

        erstellen.setText("Minimalorder(s) erstellen");

        erstellen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int id = Integer.parseInt(idStartText.getText());
                    int datCount = Integer.parseInt(dateiCountText.getText());
                    int ordNumberEnd = id + datCount- 1;
                    int neueId = id + datCount;
                    for (int counter = id; counter <= ordNumberEnd; counter++ ){
                        String counterStr = String.valueOf(counter);
                        File myObj = new File(sp+"\\"+userKuerzelText.getText()+strDate+counterStr+".edi");
                        String path = myObj.getPath();
                        System.out.println("Speicherort: "+myObj.getAbsolutePath());
                        try {
                            if (myObj.createNewFile()) {
                                System.out.println("File created: " + myObj.getName());
                                success++;
                                try {
                                    FileWriter myWriter = new FileWriter(sp+"\\"+userKuerzelText.getText()+strDate+counterStr+".edi");
                                    myWriter.write("Files in Java might be tricky, but it is fun enough!");
                                    myWriter.close();
                                    System.out.println("Successfully wrote to the file.");
                                } catch (IOException ioe) {
                                    System.out.println("An error occurred.");
                                    ioe.printStackTrace();
                                }
                            } else {
                                fail++;
                                System.out.println("Faaaaaail.");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } }
                        JOptionPane.showMessageDialog(null, "Es wurde(n) "+success+" Nachricht(en) erstellt! " +fail+ " Nachricht(en) wurde(n) nicht erstellt!", "Abgeschlossen", JOptionPane.INFORMATION_MESSAGE);
                        idStartText.setText(String.valueOf(neueId));
                        success = 0;
                        fail = 0;
                        }
                        catch (NumberFormatException nfe)
                        {
                            JOptionPane.showMessageDialog(null, "Startnummer oder Dateianzahl enthält nicht ausschließlich Zahlen!", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
            }
        });
        group.setHorizontalGroup(group.createSequentialGroup()
                .addComponent(emptyLabel)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(ordnerText)
                        .addComponent(userKuerzel)
                        .addComponent(orgCode)
                        .addComponent(mailPartner)
                        .addComponent(sellerNo)
                        .addComponent(plantCode)
                        .addComponent(unloadingPoint)
                        .addComponent(unitOfMeasure)
                        .addComponent(idStart)
                        .addComponent(dateiCount)
                        .addComponent(positionenCount)
                        .addComponent(bestellmenge)
                        .addComponent(waehrung)
                        .addComponent(lieferung)
                        .addComponent(rechnung)
                        .addComponent(antwort))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(speicherplatzSuche)
                        .addComponent(userKuerzelText)
                        .addComponent(orgCodeText)
                        .addComponent(mailPartnerText)
                        .addComponent(sellerNoText)
                        .addComponent(plantCodeText)
                        .addComponent(unloadingPointText)
                        .addComponent(unitOfMeasureText)
                        .addComponent(idStartText)
                        .addComponent(dateiCountText)
                        .addComponent(positionenCountText)
                        .addComponent(bestellmengeText)
                        .addComponent(waehrungText)
                        .addComponent(lieferungText)
                        .addComponent(rechnungText)
                        .addComponent(antwortText)
                        .addComponent(erstellen))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(ordnerSuchen))
                );


        group.setVerticalGroup(group.createSequentialGroup()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emptyLabel)
                        .addComponent(ordnerText)
                        .addComponent(speicherplatzSuche)
                        .addComponent(ordnerSuchen))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(userKuerzel)
                        .addComponent(userKuerzelText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(orgCode)
                        .addComponent(orgCodeText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mailPartner)
                        .addComponent(mailPartnerText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sellerNo)
                        .addComponent(sellerNoText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(plantCode)
                        .addComponent(plantCodeText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(unloadingPoint)
                        .addComponent(unloadingPointText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(unitOfMeasure)
                        .addComponent(unitOfMeasureText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(idStart)
                        .addComponent(idStartText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(dateiCount)
                        .addComponent(dateiCountText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(positionenCount)
                        .addComponent(positionenCountText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bestellmenge)
                        .addComponent(bestellmengeText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(waehrung)
                        .addComponent(waehrungText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lieferung)
                        .addComponent(lieferungText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(rechnung)
                        .addComponent(rechnungText))
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(antwort)
                        .addComponent(antwortText))
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
