package App;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class OrderMinPanel extends JPanel {

    int success = 0;
    int fail = 0;
    int lineCounter = 0;

    // Initialisierung der Buttons, Label und Textfelder
    JButton zurueck = new JButton();
    JButton erstellen = new JButton();
    JButton ordnerSuchen = new JButton();
    JLabel ordnerText = new JLabel();
    JTextField speicherplatzSuche = new JTextField();
    JLabel userKuerzel = new JLabel("Userkürzel:");
    JLabel orgCode = new JLabel("OrgCode:");
    JLabel mailPartner = new JLabel("Mailpartner:");
    JLabel sellerNo = new JLabel("Sellerno.:");
    JLabel plantCode = new JLabel("Werkscode");
    JLabel unloadingPoint = new JLabel("Abladestelle");
    JLabel unitOfMeasure = new JLabel("Maßeinheit(EA / PCE / KGM / MTR)");
    JLabel dateiCount = new JLabel("Anzahl Dateien");
    JLabel positionenCount = new JLabel("Anzahl Positionen:");
    JLabel bestellmenge = new JLabel("Bestellmenge/Position:");
    JLabel waehrung = new JLabel("Währung:");
    JLabel lieferung = new JLabel("Lieferung(DN / DY / BK / SC)");
    JLabel rechnung = new JLabel("Rechnung(Y / N):");
    JLabel antwort = new JLabel("Bestellantwort (Y / N):");
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
    JTextField rechnungText = new JTextField("Y");
    JTextField antwortText = new JTextField("Y");
    JTextField idStartText = new JTextField("1");


    //Plausicheck der Textfelder
    public void enableUOMButton(){
        if (unitOfMeasureText.getText().equals("EA") || unitOfMeasureText.getText().equals("KGM") || unitOfMeasureText.getText().equals("MTR") || unitOfMeasureText.getText().equals("PCE"))
        {
            erstellen.setEnabled(true);
        }
        else
        {
            erstellen.setEnabled(false);
        }}

    public void enableLieButton(){
        if (lieferungText.getText().equals("DY") || lieferungText.getText().equals("DN") || lieferungText.getText().equals("BK") || lieferungText.getText().equals("SC"))
        {
            erstellen.setEnabled(true);
        }
        else
        {
            erstellen.setEnabled(false);
        }}

    public void enableReButton(){
        if (rechnungText.getText().equals("Y") || rechnungText.getText().equals("N"))
        {
            erstellen.setEnabled(true);
        }
        else
        {
            erstellen.setEnabled(false);
        }}

    public void enableBaButton(){
        if (antwortText.getText().equals("Y") || antwortText.getText().equals("N"))
        {
            erstellen.setEnabled(true);
        }
        else
        {
            erstellen.setEnabled(false);
        }}


    public OrderMinPanel() {

        //Wichtig für die eindeutige Dateibenennung und -befüllung.
        // Erstellt Datum heute, gestern und morgen. Erstellt aktuelle Uhrzeit minutengenau.
        Instant today =  Instant.now();
        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
        Instant tomorrow = Instant.now().plus(1, ChronoUnit.DAYS);

        DateTimeFormatter day = DateTimeFormatter.ofPattern("yyMMdd")
                .withZone(ZoneId.systemDefault());
        DateTimeFormatter dayYear = DateTimeFormatter.ofPattern("yyyyMMdd")
                .withZone(ZoneId.systemDefault());
        DateTimeFormatter hour = DateTimeFormatter.ofPattern("hhmm")
                .withZone(ZoneId.systemDefault());

        String strTodayFull = dayYear.format(today);
        String strToday = day.format(today);
        String strYesterday = dayYear.format(yesterday);
        String strTomorrow = dayYear.format(tomorrow);

        String strHour = hour.format(today);

        // Actionlistener zur Ordnersuche; Ablage der Dateien
        ordnerSuchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle(choosertitle);
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false); // Schaltet "Alle Ordner" Option ab

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

        //Ordnertext soll fett gedruckt werden
        Font newLabelFont=new Font(ordnerText.getFont().getName(),Font.BOLD,ordnerText.getFont().getSize());
        ordnerText.setText("Zielordner:");
        ordnerText.setFont(newLabelFont);
        ordnerSuchen.setText("Suchen");
        ordnerSuchen.setToolTipText("Ordner zur Speicherung suchen");

        //Layout für das Containerpanel
        mainContainer = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        mainContainer.setLayout(gbl);
        mainContainer.setBackground(Color.lightGray);

        //Mainpanel erhält Grouplayout und ihm werden alle Labels, Buttons und Textfelder hinzugefügt
        //Mainpanel wird dem Maincontainer hinzugefügt.
        main = new JPanel();
        GroupLayout group = new GroupLayout(main);
        group.setAutoCreateGaps(true);
        group.setAutoCreateContainerGaps(true);
        mainContainer.add(main);
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


        //Liveupdate des Suchen-Buttons durch den Plausicheck
        unitOfMeasureText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                enableUOMButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enableUOMButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enableUOMButton();
            }
        });

        lieferungText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                enableLieButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enableLieButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enableLieButton();
            }
        });

        rechnungText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                enableReButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enableReButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enableReButton();
            }
        });

        antwortText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                enableBaButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enableBaButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enableBaButton();
            }
        });


        //Actionlistener für die Erstellung der EDIFACT Nachrichten, bzw. der EDI-Dateien, bzw. der EDI-Dateien bei Klick auf Erstellen-Button
        erstellen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int id = Integer.parseInt(idStartText.getText());
                    int datCount = Integer.parseInt(dateiCountText.getText());
                    int posCount = Integer.parseInt(positionenCountText.getText());
                    int ordNumberEnd = id + datCount- 1;
                    int neueId = id + datCount;
                    for (int counter = id; counter <= ordNumberEnd; counter++ ){
                        String counterStr = String.valueOf(counter);
                        File myObj = new File(sp+"\\SO_ORDERS_"+userKuerzelText.getText()+strToday+counterStr+"_"+strTodayFull+".edi");
                        System.out.println("Speicherort: "+myObj.getAbsolutePath());
                        try {
                            if (myObj.createNewFile()) {
                                System.out.println("File created: " + myObj.getName());
                                success++;
                                try {
                                    FileWriter myWriter = new FileWriter(sp+"\\SO_ORDERS_"+userKuerzelText.getText()+strToday+counterStr+"_"+strTodayFull+".edi");
                                    myWriter.write("UNA:+.? '\n");
                                    myWriter.write("UNB+UNOC:3+"+mailPartnerText.getText()+"+OSUPPLYON+"+strTodayFull+":"+strHour+"+0000200003'\n");
                                    lineCounter++;
                                    myWriter.write("UNH+1+ORDERS:D:99B:UN'\n");
                                    lineCounter++;
                                    myWriter.write("BGM+220:1::Purchase Orders SupplyOn+"+userKuerzelText.getText()+strToday+idStartText.getText()+"+9'\n");
                                    lineCounter++;
                                    myWriter.write("DTM+137:"+strYesterday+":102'\n");
                                    lineCounter++;
                                    myWriter.write("NAD+BY+"+plantCodeText.getText()+"::92+SCMPD:Demo Buyer+Demo Buyer SA:Buyer Name2:Buyer Name3:Buyer Name4+Ludwigstrasse 49+Hallbergmoos+1A1+85399+DE'\n");
                                    lineCounter++;
                                    myWriter.write("NAD+SU+"+sellerNoText.getText()+"::92++Demo Seller GmbH:Supplier Name2:Supplier Name3:Supplier Name4+Kastanienweg 12+Bremen+1A1+45678+DE'\n");
                                    lineCounter++;
                                    myWriter.write("NAD+CN+"+plantCodeText.getText()+"::92+OBLE:Demo Buyer+Plant 2:Consignee Name2:Consignee Name3:Consignee Name4+Burgweg 341+Hamburg++20095+DE'\n");
                                    lineCounter++;
                                    myWriter.write("LOC+11+"+unloadingPointText.getText()+"'\n");
                                    lineCounter++;
                                    for (int count= 1; count <= posCount; count++){
                                        myWriter.write("LIN+"+count+"++A "+strToday+counterStr+":IN'\n");
                                        lineCounter++;
                                        myWriter.write("IMD+F+1+:::A "+strToday+counterStr+" Descr. Cust'\n");
                                        lineCounter++;
                                        myWriter.write("QTY+113:"+bestellmengeText.getText()+":"+unitOfMeasureText.getText()+"'");
                                        lineCounter++;
                                        myWriter.write("ALI+FR++S+"+antwortText.getText()+"+"+rechnungText.getText()+"+Y+N'\n");
                                        lineCounter++;
                                        myWriter.write("ALI++S+"+lieferungText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("DTM+2:"+strTomorrow+":102'\n");
                                        lineCounter++;
                                        myWriter.write("MOA+203:1:"+waehrungText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("MOA+128:2:"+waehrungText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("PRI+AAA:120:::100:"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("CUX+2:"+waehrungText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("SCC+1'\n");
                                        lineCounter++;
                                        myWriter.write("QTY+113:"+bestellmengeText.getText()+":"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("DTM+2:"+strTomorrow+":102'\n");
                                        lineCounter++;
                                    }
                                    myWriter.write("UNS+D'\n");
                                    lineCounter++;
                                    myWriter.write("MOA+79:1:"+waehrungText.getText()+"'\n");
                                    lineCounter++;
                                    myWriter.write("MOA+116:2:"+waehrungText.getText()+"'\n");
                                    lineCounter++;
                                    myWriter.write("UNT+"+lineCounter+"+1'\n");
                                    myWriter.write("UNZ+1+14'\n");
                                    myWriter.close();
                                    lineCounter = 0;
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

                        // Popupnachricht wie viele Dateien erstellt wurden und anschließend zurücksetzen der Counter.

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
        //Einordnung der Labels, Buttons und Textfelder in das Raster des Grouplayouts.
        //emptyLabel nötig, da ohne das Grouplayout verzogen wird
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

        // Neues Panel für den Zurück-Button. Wird im Bild unten angesiedelt

        back = new JPanel();
        back.setBackground(Color.black);
        zurueck.setText("Zurück");
        back.add(zurueck);
        back.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Das große Hauptpanel erhält Layout und die relevanten Panels

        this.setLayout(new BorderLayout());
        this.add(back, BorderLayout.SOUTH);
        this.add(mainContainer, BorderLayout.CENTER);
    }
}
