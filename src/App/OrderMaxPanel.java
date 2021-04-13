package App;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class OrderMaxPanel extends JPanel {

    int success = 0;
    int fail = 0;
    int lineCounter = 0;

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
    JLabel unitOfMeasure = new JLabel("Maßeinheit(EA / PCE / KGM / MTR)");
    JLabel dateiCount = new JLabel("Anzahl Dateien");
    JLabel positionenCount = new JLabel("Anzahl Positionen:");
    JLabel bestellmenge = new JLabel("Bestellmenge/Position:");
    JLabel waehrung = new JLabel("Währung:");
    JLabel lieferung = new JLabel("Lieferung(DN / DY / BK / SC)");
    JLabel rechnung = new JLabel("Rechnung(Y / N):");
    JLabel antwort = new JLabel("Bestellantwort (y / N):");
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


    public OrderMaxPanel() {

        //Wichtig für die eindeutige Dateibenennung
        // Erstellt Datum heute, gestern und morgen. Erstellt aktuelle Uhrzeit minutengenau.
        Instant today =  Instant.now();
        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
        Instant tomorrow = Instant.now().plus(1, ChronoUnit.DAYS);
        Instant futureOne = Instant.now().plus(2, ChronoUnit.DAYS);
        Instant futureTwo = Instant.now().plus(3, ChronoUnit.DAYS);
        Instant futureFour = Instant.now().plus(4, ChronoUnit.DAYS);
        Instant futureThree = Instant.now().plus(12, ChronoUnit.DAYS);
        Instant pastOne = Instant.now().minus(1, ChronoUnit.DAYS);

        DateTimeFormatter day = DateTimeFormatter.ofPattern("yyMMdd")
                .withZone(ZoneId.systemDefault());
        DateTimeFormatter dayYear = DateTimeFormatter.ofPattern("yyyyMMdd")
                .withZone(ZoneId.systemDefault());
        DateTimeFormatter dayFull = DateTimeFormatter.ofPattern("yyyyMMddhhmm")
                .withZone(ZoneId.systemDefault());
        DateTimeFormatter hour = DateTimeFormatter.ofPattern("hhmm")
                .withZone(ZoneId.systemDefault());

        String strToday = day.format(today);

        String strTodayFull = dayYear.format(today);
        String strYesterday = dayYear.format(yesterday);
        String strPastOne = dayYear.format(pastOne);
        String strTomorrow = dayYear.format(tomorrow);
        String strFutOne = dayYear.format(futureOne);
        String strFutTwo = dayYear.format(futureTwo);
        String strFutFour = dayYear.format(futureFour);


        String strFutThree = dayFull.format(futureThree);
        String strFutFull = dayFull.format(futureTwo);

        String strHour = hour.format(today);

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

        erstellen.setText("Maximalorder(s) erstellen");


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

        //Dateien werden erstellt und befüllt
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
                        // Datei erstellen
                        File myObj = new File(sp+"\\SO_ORDERS_"+userKuerzelText.getText()+strToday+counterStr+"_"+strTodayFull+".edi");
                        String path = myObj.getPath();
                        System.out.println("Speicherort: "+myObj.getAbsolutePath());
                        try {
                            if (myObj.createNewFile()) {
                                System.out.println("File created: " + myObj.getName());
                                success++;
                                try {
                                    //Jede erstellte Datei mit Infos befüllen
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
                                    myWriter.write("DTM+157:"+strFutOne+":102'\n");
                                    lineCounter++;
                                    myWriter.write("DTM+36:"+strFutTwo+":102'\n");
                                    lineCounter++;
                                    myWriter.write("DTM+XXX:"+strFutFull+":102'\n");
                                    lineCounter++;
                                    myWriter.write("ALI+++1+2+3+4+5'\n");
                                    lineCounter++;
                                    myWriter.write("ALI++S+"+lieferungText.getText()+"'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+ADU+++Any text defined by the sender:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5 '\n");
                                    lineCounter++;
                                    myWriter.write("FTX+ACB+++Free text orders:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+AAI+++Header information:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+AAK+++Price Conditions 1:Price Conditions 2:Price Conditions 3:Price Conditions 4:Price Conditions 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+ZZZ+++Dates:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+AAB+++Payment Terms:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+AAR+++Delivery Terms:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+DIN+++Shipping Instructions:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+CCI+++Customs Clearance:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+IIN+++Insurance:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+SIN+++Additional Agreement:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+PRD+++Configs'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+FRA+++IncotermsText'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+FL1+++http???://www.supplyon.com/documents/link1.pdf'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+FL2+++http?://www.supplyon.com/documents/link2.pdf'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+FL3+++http?://www.supplyon.com/documents/link3.pdf'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+ACF+++additional attribute 1:additional attribute 2:additional attribute 3:additional attribute 4:additional attribute 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+AFT+++Business Line/Project 1:Business Line/Project 2:Business Line/Project 3:Business Line/Project 4:Business Line/Project 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+AGE+++Contract Information 1:Contract Information 2:Contract Information 3:Contract Information 4:Contract Information 5 '\n");
                                    lineCounter++;
                                    myWriter.write("FTX+ORI+++Order instruction 1:Order instruction 2:Order instruction 3:Order instruction 4:Order instruction 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+AIX+++Warranty Term 1:Warranty Term 2:Warranty Term 3:Warranty Term 4:Warranty Term 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+AIL+++Guarantee 1:Guarantee 2:Guarantee 3:Guarantee 4:Guarantee 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+DOC+++Document instruction 1:Document instruction 2:Document instruction 3:Document instruction 4:Document instruction 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+CUR+++Customer Remarks:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("FTX+COI+++Order Information:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+BN:100245'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+CR:778802393'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+ON:101119770720'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+AEP:PROJ333'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+XXX:12345'\n");
                                    lineCounter++;
                                    myWriter.write("NAD+BY+"+plantCodeText.getText()+"::92+SCMPD:Demo Buyer+Demo Buyer SA:Buyer Name2:Buyer Name3:Buyer Name4+Ludwigstrasse 49+Hallbergmoos+1A1+85399+DE'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+VA:001002003'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+ARB:006005004'\n");
                                    lineCounter++;
                                    myWriter.write("CTA+DL+Purchasing Departement:Konrad Meier'\n");
                                    lineCounter++;
                                    myWriter.write("CTA+DL+SUCT 001:Eberhard Mueller'\n");
                                    lineCounter++;
                                    myWriter.write("COM+040195588784:TE'\n");
                                    lineCounter++;
                                    myWriter.write("COM+040195588794:FX'\n");
                                    lineCounter++;
                                    myWriter.write("COM+K.Meier@buyer.de:EM'\n");
                                    lineCounter++;
                                    myWriter.write("NAD+SU+"+sellerNoText.getText()+"::92++Demo Seller GmbH:Supplier Name2:Supplier Name3:Supplier Name4+Kastanienweg 12+Bremen+1A1+45678+DE'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+VA:DE555555555'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+ARB:006005004'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+FC:008008008'\n");
                                    lineCounter++;
                                    myWriter.write("CTA+DL+SUCT 001:Eberhard Mueller'\n");
                                    lineCounter++;
                                    myWriter.write("COM+040133588784:TE'\n");
                                    lineCounter++;
                                    myWriter.write("COM+040133588794:FX'\n");
                                    lineCounter++;
                                    myWriter.write("COM+E.Mueller@supplier.de:EM'\n");
                                    lineCounter++;
                                    myWriter.write("NAD+CA+5588666699::92++TransSped KG:Sped Name2:Sped Name3:Sped Name4+Lagerpfad 33+Dünsen+1A1+55345+DE'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+VA:004993011'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+ARB:088774404'\n");
                                    lineCounter++;
                                    myWriter.write("CTA+DL+CACT 001:Thorsten Klipfel'\n");
                                    lineCounter++;
                                    myWriter.write("COM+777833588784:TE'\n");
                                    lineCounter++;
                                    myWriter.write("COM+077833588777:FX'\n");
                                    lineCounter++;
                                    myWriter.write("COM+T.Klipfel@icarrier.de:EM'\n");
                                    lineCounter++;
                                    myWriter.write("NAD+VT+5588666699::92++TransSped KG:Sped Name2:Sped Name3:Sped Name4+Lagerpfad 33+Dünsen+1A1+55345+DE'\n");
                                    lineCounter++;
                                    myWriter.write("CTA+IC+CACT 001:Thorsten Klipfel' \n");
                                    lineCounter++;
                                    myWriter.write("COM+777833588788:TE'\n");
                                    lineCounter++;
                                    myWriter.write("COM+077833588777:FX'\n");
                                    lineCounter++;
                                    myWriter.write("COM+T.Klipfel@sitebranch.de:EM' \n");
                                    lineCounter++;
                                    myWriter.write("COM+012133588776:AJ' \n");
                                    lineCounter++;
                                    myWriter.write("NAD+OB+OB01::92+OB01:X+Ordered By Name 1:Ordered By Name 2:Ordered By Name 3:Ordered By Name 4+OBStreet+Dünsen+1A1+55345+DE'\n");
                                    lineCounter++;
                                    myWriter.write("CTA+IC+OBCT 001:Thorsten Klipfel'\n");
                                    lineCounter++;
                                    myWriter.write("COM+777833588782:TE'\n");
                                    lineCounter++;
                                    myWriter.write("COM+077833588777:FX'\n");
                                    lineCounter++;
                                    myWriter.write("COM+T.Klipfel@orderingparty.de:EM'\n");
                                    lineCounter++;
                                    myWriter.write("NAD+XXX+5588666699::92+Flexible Name and Adress 1:Flexible Name and Adress 2+FlexibleCompanyName 1:FlexibleCompanyName 2:FlexibleCompanyName 3:FlexibleCompanyName 4+Flexible Street 11+MUnich+1A1+55345+DE'\n");
                                    lineCounter++;
                                    myWriter.write("RFF+XXX:Flexible Reference'\n");
                                    lineCounter++;
                                    myWriter.write("CTA+XXX+CACT 001:Thorsten Klipfel'\n");
                                    lineCounter++;
                                    myWriter.write("COM+777833588783:TE'\n");
                                    lineCounter++;
                                    myWriter.write("COM+077833588777:FX'\n");
                                    lineCounter++;
                                    myWriter.write("COM+T.Klipfel@flexibleparty.de:EM'\n");
                                    lineCounter++;
                                    myWriter.write("TOD+5++N::ZZZ:16.10.2015'\n");
                                    lineCounter++;
                                    for (int count= 1; count <= posCount; count++){
                                        myWriter.write("LIN+"+count+"++A "+strToday+counterStr+":IN'\n");
                                        lineCounter++;
                                        myWriter.write("IMD+F+1+:::A "+strToday+counterStr+" Descr. Cust:A "+strToday+counterStr+" Descr. S'\n");
                                        lineCounter++;
                                        myWriter.write("IMD+A++::92:Detailed Article Description::DE'\n");
                                        lineCounter++;
                                        myWriter.write("IMD+C++::6:1203::DE'\n");
                                        lineCounter++;
                                        myWriter.write("QTY+113:"+bestellmengeText.getText()+":"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("QTY+129:1500:"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("QTY+70:500:"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("ALI+FR++S+"+antwortText.getText()+"+"+rechnungText.getText()+"+Y+N'\n");
                                        lineCounter++;
                                        myWriter.write("ALI++S+"+lieferungText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("DTM+2:"+strTomorrow+":102'\n");
                                        lineCounter++;
                                        myWriter.write("DTM+334:"+strPastOne +":102'\n");
                                        lineCounter++;
                                        myWriter.write("DTM+282:"+strFutThree+":203'\n");
                                        lineCounter++;
                                        myWriter.write("MOA+203:1:"+waehrungText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("MOA+128:2:"+waehrungText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+ACX+++01-02+DE'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+AAI+++General Information 1:General Information 2:General Information 3:General Information 4:General Information 5 '\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F01+++FlexibleField01'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F02+++FlexibleField02'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F03+++FlexibleField03'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F04+++FlexibleField04'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F05+++FlexibleField05'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F06+++FlexibleField06'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F07+++FlexibleField07'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F08+++FlexibleField08'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F09+++X'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F10+++FlexibleField10'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F11+++FlexibleField11'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F12+++FlexibleField12'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+FL1+++http?://www.supplyon.com/documents/link1.pdf'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+FL2+++http?://www.supplyon.com/documents/link2.pdf'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+FL3+++http?://www.supplyon.com/documents/link3.pdf'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+CHG+++1'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+PTY+++1'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+PRD+++Config1:Config2:Config3:Config4:Config5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+COI+++16.10.2007:Information 2:Information 3:Information 4:Information 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+AAA+++Model?:VALUE:Goods Description 2:Goods Description 3:Goods Description 4:Goods Description 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+DOC+++Document instruction 1:Document instruction 2:Document instruction 3:Document instruction 4:Document instruction 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+PUR+++Purchasing Information 1:Purchasing Information 2:Purchasing Information 3:Purchasing Information 4:Purchasing Information 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+AIX+++Warranty Term 1:Warranty Term 2:Warranty Term 3:Warranty Term 4:Warranty Term 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+AIL+++Guarantee 1:Guarantee 2:Guarantee 3:Guarantee 4:Guarantee 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+AGE+++Contract Information 1:Contract Information 2:Contract Information 3:Contract Information 4:Contract Information 5 '\n");
                                        lineCounter++;
                                        myWriter.write("FTX+ACB+++Free text orders:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+AAK+++Price Conditions 1:Price Conditions 2:Price Conditions 3:Price Conditions 4:Price Conditions 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+AAR+++Delivery Terms:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+ZZZ+++Dates:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+AAB+++Payment Terms:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+DIN+++Shipping Instructions:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+CCI+++Customs Clearance:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+IIN+++Insurance:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+SIN+++Additional Agreement:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+CUR+++Customer Remarks:Continuation text 2:Continuation text 3:Continuation text 4:Continuation text 5'\n");
                                        lineCounter++;
                                        myWriter.write("FTX+F13+++Order Information 1:Order Information 2:Order Information 3:Order Information 4:Order Information 5'\n");
                                        lineCounter++;
                                        myWriter.write("PRI+AAA:120:::100:"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("CUX+2:"+waehrungText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+AAN:55001236'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+AAJ:88045127A:10'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+UC:15102007JK'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+CT:2798248'\n");
                                        lineCounter++;
                                        myWriter.write("PAC+9++:::KLT-4711'\n");
                                        lineCounter++;
                                        myWriter.write("QTY+52:100:"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("TAX+7+VAT+++:::19'\n");
                                        lineCounter++;
                                        myWriter.write("TAX+7+SUR+++:::15'\n");
                                        lineCounter++;
                                        myWriter.write("NAD+CN+"+plantCodeText.getText()+"::92+OBLE:Demo Buyer+Plant 2:Consignee Name2:Consignee Name3:Consignee Name4+Burgweg 341+Hamburg++20095+DE'\n");
                                        lineCounter++;
                                        myWriter.write("LOC+11+"+unloadingPointText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("LOC+18+Warehouse1'\n");
                                        lineCounter++;
                                        myWriter.write("LOC+159+point of cunsumpti 1'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+VA:DE987654321'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+ARB:9876543210'\n");
                                        lineCounter++;
                                        myWriter.write("CTA+DL+Warenannahme: Louis Buyer'\n");
                                        lineCounter++;
                                        myWriter.write("COM+?+2249 (30) 27046:TE'\n");
                                        lineCounter++;
                                        myWriter.write("COM+?+2249 (30) 123478:FX'\n");
                                        lineCounter++;
                                        myWriter.write("COM+louis.buyer@buyer.fr:EM'\n");
                                        lineCounter++;
                                        myWriter.write("NAD+IV+00999888::92++Invoicee GmbH & Co. KG:Invoicee Name2:Invoicee Name3:Invoicee Name4+Mainstreet 44+Berlin+1A1+12345+DE'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+VA:004003003'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+ARB:006005004'\n");
                                        lineCounter++;
                                        myWriter.write("CTA+DL+Inv Dptm:Invoicee Spiegel'\n");
                                        lineCounter++;
                                        myWriter.write("COM+047833588784:TE'\n");
                                        lineCounter++;
                                        myWriter.write("COM+047833588794:FX' \n");
                                        lineCounter++;
                                        myWriter.write("COM+K.Spiegel@invoicee.de:EM'\n");
                                        lineCounter++;
                                        myWriter.write("NAD+GM+3333::92++Harald Strunz'\n");
                                        lineCounter++;
                                        myWriter.write("CTA+DL+TC-512:Harald Strunz' \n");
                                        lineCounter++;
                                        myWriter.write("COM+042795588784:TE'\n");
                                        lineCounter++;
                                        myWriter.write("COM+042795588794:FX'\n");
                                        lineCounter++;
                                        myWriter.write("COM+H.Strunz@buyer.de:EM'\n");
                                        lineCounter++;
                                        myWriter.write("ALC+M++++FC:::X'\n");
                                        lineCounter++;
                                        myWriter.write("PCD+2:2.5'\n");
                                        lineCounter++;
                                        myWriter.write("MOA+8:12345.50'\n");
                                        lineCounter++;
                                        myWriter.write("TOD+1++FOB'\n");
                                        lineCounter++;
                                        myWriter.write("LOC+1+Munich'\n");
                                        lineCounter++;
                                        myWriter.write("SCC+1'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+LI:b1'\n");
                                        lineCounter++;
                                        myWriter.write("QTY+113:"+bestellmengeText.getText()+":"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("DTM+2:"+strTomorrow+":102'\n");
                                        lineCounter++;
                                        myWriter.write("SCC+4'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+LI:b2'\n");
                                        lineCounter++;
                                        myWriter.write("QTY+113:0:"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("DTM+137:"+strFutOne+":102'\n");
                                        lineCounter++;
                                        myWriter.write("SCC+9'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+LI:b3'\n");
                                        lineCounter++;
                                        myWriter.write("QTY+113:0:"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("DTM+137:"+strFutTwo+":102'\n");
                                        lineCounter++;
                                        myWriter.write("SCC+99'\n");
                                        lineCounter++;
                                        myWriter.write("RFF+XXX:b4'\n");
                                        lineCounter++;
                                        myWriter.write("QTY+113:0:"+unitOfMeasureText.getText()+"'\n");
                                        lineCounter++;
                                        myWriter.write("DTM+137:"+strFutFour+":102'\n");
                                        lineCounter++;
                                    }
                                    myWriter.write("UNS+D'\n");
                                    lineCounter++;
                                    myWriter.write("MOA+199:10000.25:"+waehrungText.getText()+"'\n");
                                    lineCounter++;
                                    myWriter.write("MOA+66:960.00:"+waehrungText.getText()+"'\n");
                                    lineCounter++;
                                    myWriter.write("MOA+8:121:"+waehrungText.getText()+"'\n");
                                    lineCounter++;
                                    myWriter.write("MOA+79:1:"+waehrungText.getText()+"'\n");
                                    lineCounter++;
                                    myWriter.write("MOA+150:205.04:"+waehrungText.getText()+"'\n");
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
