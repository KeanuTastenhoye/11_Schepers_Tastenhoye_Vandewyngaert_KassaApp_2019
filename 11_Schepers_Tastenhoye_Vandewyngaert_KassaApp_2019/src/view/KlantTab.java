package view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import domain.Artikel;

import model.observer.Observer;

public class KlantTab extends GridPane implements Observer {
    private TableView table;

    public KlantTab() {
        this.setPadding(new Insets(5,5,5,5));
        this.setVgap(5);
        this.setHgap(5);
    }

    @Override
    public void update(ObservableList<Artikel> klantlist) {
        System.out.println("Klant tab klantlist " + klantlist);
        Label totaalLabel = new Label("Totaal: ");

        TableColumn Nr = new TableColumn<>("Nr");
        TableColumn Naam = new TableColumn<>("Naam");
        TableColumn Groep = new TableColumn<>("Groep");
        TableColumn Prijs = new TableColumn<>("Prijs");
        TableColumn Voorraad = new TableColumn<>("Voorraad");

        Nr.setCellValueFactory(new PropertyValueFactory("Nr"));
        Naam.setCellValueFactory(new PropertyValueFactory("Naam"));
        Groep.setCellValueFactory(new PropertyValueFactory("Groep"));
        Prijs.setCellValueFactory(new PropertyValueFactory("Prijs"));
        Voorraad.setCellValueFactory(new PropertyValueFactory("Voorraad"));

        table = new TableView<String>();
        table.setPrefWidth(REMAINING);
        table.setItems(klantlist);
        table.getColumns().addAll(Nr, Naam, Groep, Prijs, Voorraad);
        table.getSortOrder().add(Naam);

        HBox hbox = new HBox(totaalLabel);

        add(hbox, 0, 2, 1, 1);
        add(table, 0, 4, 2, 2);
    }
}