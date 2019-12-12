package view;

import controller.ArtikelController;

import database.Methode;
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
import model.observer.Observer;

import java.io.IOException;

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


        HBox knopjes = new HBox(artikelCodeLabel, artikelCodeText, save, delete, onHold, continu, afsluiten);

        this.add(knopjes, 0, 2, 1, 1);

        Label totaalLabel = new Label("Totaal: ");

        Label bedragLabel = new Label("");
        Label koritngTxt = new Label("Korting: ");
        Label kortingLabel = new Label("");
        Label versieringLabel = new Label("--------------------------------------");
        Label eindTotaal = new Label("Te betalen: ");



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

                        //update();
                        table.setItems(artikelController.getVerkoopObservable(artikelNrke)); //hier wordt het artikel met de meegegeven code opgezocht in de lijst

                        table.getColumns().addAll(artikelNr, artikelNaam, artikelGroep, artikelPrijs, artikelVoorraad);
                        table.getSortOrder().add(artikelNaam);

                        bedragLabel.setText(Double.toString(artikelController.getVerkoopPrijs(artikelNrke)));

                        HBox totaal = new HBox(totaalLabel, bedragLabel);

                        artikelController.doObserver();

                        add(totaal, 0, 3, 1, 1);
                        add(table, 0, 7, 2, 2);
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
                        table.setItems(artikelController.getDeleteVerkoopObservable(artikelNrke));
                        table.getColumns().addAll(artikelNr, artikelNaam, artikelGroep, artikelPrijs, artikelVoorraad);
                        table.getSortOrder().add(artikelNaam);

                        bedragLabel.setText(Double.toString(artikelController.getDeleteVerkoopPrijs(artikelNrke)));

                        HBox totaal = new HBox(totaalLabel, bedragLabel);

                        add(totaal, 0, 3, 1, 1);
                        add(table, 0, 6, 2, 2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        onHold.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(klantNaOnHoldCounter<3)
                {
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
                    klantNaOnHoldCounter=0;
                    table.getItems().clear();
                    bedragLabel.setText("");
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
                    table.setItems(artikelController.getOnHoldObservable());
                    artikelController.clearOnHold();
                    table.getColumns().addAll(artikelNr, artikelNaam, artikelGroep, artikelPrijs, artikelVoorraad);
                    table.getSortOrder().add(artikelNaam);

                    bedragLabel.setText(Double.toString(artikelController.getOnHoldPrijs()));

                    HBox totaal = new HBox(totaalLabel, bedragLabel);

                    add(totaal, 0, 3, 1, 1);
                    add(table, 0, 7, 2, 2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        afsluiten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                kortingLabel.setText("JA WADDE JE HEBT KORTING");



                HBox korting = new HBox(koritngTxt, kortingLabel);
                HBox versiering = new HBox(versieringLabel);
                HBox eindtotaal = new HBox(eindTotaal);
                add(korting, 0, 4, 1, 1);
                add(versiering, 0, 5, 1, 1);
                add(eindTotaal, 0,6,1,1);

            }
        });
    }

    @Override
    public void update(ObservableList<Artikel> klantlist) throws IOException, BiffException {
        artikelController.voorraadOmlaag(artNr);
    }





}