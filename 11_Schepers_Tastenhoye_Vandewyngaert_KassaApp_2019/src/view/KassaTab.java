package view;

import controller.ArtikelController;

import domain.Artikel;
import domain.KortingEnum;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import jxl.read.biff.BiffException;
import model.decorator.*;
import model.observer.Observer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import java.util.List;

public class KassaTab extends GridPane implements Observer {
    private ArtikelController artikelController;
    private TableView table;
    private int klantNaOnHoldCounter=0;
    private String artNr;

    public KassaTab() {
        this.artikelController = new ArtikelController();
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Voeg de artikelcode in om het in je winkelmandje te doen: "), 0, 0, 1, 1);

        Label artikelCodeLabel = new Label("Code: ");
        TextField artikelCodeText = new TextField();
        Button save = new Button("Save");
        Button delete = new Button("Delete");
        Button onHold = new Button("OnHold");
        Button continu = new Button("Continue");
        Button afsluiten = new Button("Afsluiten");
        Button betalen = new Button("Betalen");
        Button annuleren = new Button("Annuleren");

        HBox knopjes = new HBox(artikelCodeLabel, artikelCodeText, save, delete, onHold, continu, afsluiten, betalen,annuleren);

        this.add(knopjes, 0, 2, 1, 1);

        Label totaalLabel = new Label("Totaal: ");
        Label bedragLabel = new Label("");
        Label kortingTxt = new Label("Korting: ");
        Label kortingLabel = new Label("");
        Label versieringLabel = new Label("--------------------------------------");
        Label eindTotaal = new Label("Te betalen: ");
        Label eindLabel = new Label("");

        TableColumn artikelNr = new TableColumn<>("Nr");
        TableColumn artikelNaam = new TableColumn<>("Naam");
        TableColumn artikelGroep = new TableColumn<>("Groep");
        TableColumn artikelPrijs = new TableColumn<>("Prijs");
        TableColumn artikelVoorraad = new TableColumn<>("Voorraad");

        artikelNr.setCellValueFactory(new PropertyValueFactory("artikelNr"));
        artikelNaam.setCellValueFactory(new PropertyValueFactory("artikelNaam"));
        artikelGroep.setCellValueFactory(new PropertyValueFactory("artikelGroep"));
        artikelPrijs.setCellValueFactory(new PropertyValueFactory("artikelPrijs"));
        artikelVoorraad.setCellValueFactory(new PropertyValueFactory("artikelVoorraad"));

        table = new TableView<String>();
        table.setPrefWidth(REMAINING);
        table.getColumns().addAll(artikelNr, artikelNaam, artikelGroep, artikelPrijs, artikelVoorraad);
        table.getSortOrder().add(artikelNaam);

        HBox totaal = new HBox(totaalLabel, bedragLabel);

        add(totaal, 0, 3, 1, 1);
        add(table, 0, 7, 2, 2);

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String artikelNrke = artikelCodeText.getText();
                    artNr = artikelNrke;

                    if (!artikelController.correctNr(artikelNrke)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error in het artikel nummer");
                        alert.setContentText("Het artikel nummer dat U invoerde bestaat niet.");
                        alert.showAndWait();
                    } else {
                        table.setItems(artikelController.getVerkoopObservable(artikelNrke)); //hier wordt het artikel met de meegegeven code opgezocht in de lijst
                        bedragLabel.setText(Double.toString(artikelController.getVerkoopPrijs(artikelNrke)));
                        artikelController.doObserver();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String artikelNrke = artikelCodeText.getText();

                    if (!artikelController.correctNr(artikelNrke)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error in het artikel nummer");
                        alert.setContentText("Het artikel nummer dat U invoerde bestaat niet.");
                        alert.showAndWait();
                    } else if (!artikelController.nrStaatNietInLijst(artikelNrke)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error in het artikel nummer");
                        alert.setContentText("Het artikel nummer dat U invoerde staat niet in de lijst.");
                        alert.showAndWait();
                    } else {
                        table.setItems(artikelController.getDeleteVerkoopObservable(artikelNrke));
                        bedragLabel.setText(Double.toString(artikelController.getDeleteVerkoopPrijs(artikelNrke)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        onHold.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (klantNaOnHoldCounter<3) {
                    klantNaOnHoldCounter++;
                    artikelController.setOnHold();
                    artikelController.setOnHoldPrijs();
                    table.getItems().clear();
                    bedragLabel.setText("");
                }
            }
        });

        continu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    klantNaOnHoldCounter = 0;
                    table.getItems().clear();
                    bedragLabel.setText("");
                    table.setItems(artikelController.getOnHoldObservable());
                    bedragLabel.setText(Double.toString(artikelController.getOnHoldPrijs()));
                    artikelController.clearOnHold();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        afsluiten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\kortingStrategieProperties");
                //File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\kortingStrategieProperties");
                File file = new File("./bestanden/kortingStrategieProperties");

                try {
                    HashMap<KortingEnum, ArrayList<String>> kortingen= artikelController.getKortingen();
                    if(!kortingTxt.equals("") || kortingen.containsKey(KortingEnum.DREMPEL) && artikelController.getAllScannedArtikelsv2().size()<Integer.parseInt(kortingen.get(KortingEnum.DREMPEL).get(1))
                            ||kortingen.containsKey(KortingEnum.GROEP)&& artikelController.hasGroup(kortingen.get(KortingEnum.GROEP).get(1)))  {
                        //kortingLabel.setText("JA WADDE JE HEBT KORTING");
                        /*
                        TextField korting = new TextField("prijs zonder korting: "+Double.toString(artikelController.getTotalPriceScannedItems())
                                +" aantal korting: "+Double.toString(artikelController.getAmountOfKorting())
                                +" totale prijs met korting: "+Double.toString(artikelController.getTotalPriceScannedItems()-artikelController.getAmountOfKorting()));

                         */

                        kortingLabel.setText(Double.toString(artikelController.getAmountOfKorting()));
                        eindLabel.setText(Double.toString(artikelController.getTotalPriceScannedItems() - artikelController.getAmountOfKorting()));

                        HBox korting = new HBox(kortingTxt, kortingLabel);
                        HBox versiering = new HBox(versieringLabel);
                        HBox eind = new HBox(eindTotaal, eindLabel);

                        add(korting, 0, 4, 1, 1);
                        add(versiering, 0, 5, 1, 1);
                        add(eind, 0, 6, 1, 1);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (BiffException e) {
                    e.printStackTrace();
                }
            }
        });

        betalen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    KassabonAbstract kassabon = new Kassabon() {};
                    String lijn;
                    String inputVeld = null;
                    int haakjePlaats = 0;
                    String decKeuzes[];
                    List<String> decShit;

                    try {
                        //BufferedReader reader = new BufferedReader(new FileReader("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\DecoratorKassabonProperties"));
                        BufferedReader reader = new BufferedReader(new FileReader("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\DecoratorKassabonProperties"));

                        while ((lijn = reader.readLine()) != null) {
                            if (lijn.charAt(0) != '#') {
                                for (int i = 0; i < lijn.length(); i++) {
                                    if (lijn.charAt(i) == ']') {
                                        haakjePlaats = i;
                                    }
                                }
                                if (lijn.substring(0,6).equals("Input=")) {
                                    inputVeld = lijn.substring(7);
                                }
                                if (lijn.substring(0, 6).equals("Keuze=")) {
                                   List<String> lijst = new ArrayList<>();
                                   lijst.add(lijn.substring(7, haakjePlaats));

                                   for (String s: lijst) {
                                       decKeuzes = s.split(" , ");
                                       decShit = new ArrayList<>(Arrays.asList(decKeuzes));

                                       for (String str: decShit) {
                                            if (str.equals("alg")) {
                                                kassabon = new HeaderMetAlgInfo(kassabon, inputVeld);
                                            }
                                            if (str.equals("date")) {
                                                kassabon = new HeaderMetDatumEnTijd(kassabon);
                                            }
                                            if (str.equals("kort")) {
                                                kassabon = new FooterKorting(kassabon);
                                            }
                                            if (str.equals("btw")) {
                                                kassabon = new FooterMetBtw(kassabon);
                                            }
                                            if (str.equals("danku")) {
                                                kassabon = new FooterMetBericht(kassabon);
                                            }
                                       }
                                   }
                                }
                            } else {
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println(kassabon.print(artikelController.getAllScannedArtikelsv2()));

                } catch (BiffException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        annuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           //code voor annuleren
            }
        });
    }

    @Override
    public void update(ObservableList<Artikel> klantlist) throws IOException, BiffException {
        artikelController.voorraadOmlaag(artNr);
        table.refresh();
    }
}