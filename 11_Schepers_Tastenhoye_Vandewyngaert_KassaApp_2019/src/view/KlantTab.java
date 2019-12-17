package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import domain.Artikel;

import model.observer.Observer;

import java.util.ArrayList;

public class KlantTab extends GridPane implements Observer {
    private TableView table;

    public KlantTab() {
        this.setPadding(new Insets(5,5,5,5));
        this.setVgap(5);
        this.setHgap(5);

        Label totaalLabel = new Label("Totaal: ");

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

        HBox hbox = new HBox(totaalLabel);

        add(hbox, 0, 2, 1, 1);
        add(table, 0, 4, 2, 2);
    }

    @Override
    public void update(ObservableList<String> klantlist) {
        System.out.println("Klanten list: " + klantlist);

        table.setItems(klantlist);
        table.refresh();
    }
}