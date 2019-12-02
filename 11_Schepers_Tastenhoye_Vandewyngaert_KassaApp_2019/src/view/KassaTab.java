package view;

import controller.ArtikelController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class KassaTab extends GridPane {
    private ArtikelController artikelController;
    private TableView table;

    public KassaTab() {
        this.artikelController = new ArtikelController();
        //artikelController.registerObserver(this);
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

        HBox hbox1 = new HBox(artikelCodeLabel, artikelCodeText, save, delete, onHold, continu);

        this.add(hbox1, 0, 2, 1, 1);

        Label totaalLabel = new Label("Totaal: ");
        Label bedragLabel = new Label("");

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String artikelNrke = artikelCodeText.getText();

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

                        HBox hbox2 = new HBox(totaalLabel, bedragLabel);

                        add(hbox2, 0, 3, 1, 1);
                        add(table, 0, 6, 2, 2);
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

                        HBox hbox2 = new HBox(totaalLabel, bedragLabel);

                        add(hbox2, 0, 3, 1, 1);
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
                artikelController.setOnHold();
                artikelController.setOnHoldPrijs();
                table.getItems().clear();
                bedragLabel.setText("");
            }
        });

        continu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
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

                    HBox hbox2 = new HBox(totaalLabel, bedragLabel);

                    add(hbox2, 0, 3, 1, 1);
                    add(table, 0, 6, 2, 2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}