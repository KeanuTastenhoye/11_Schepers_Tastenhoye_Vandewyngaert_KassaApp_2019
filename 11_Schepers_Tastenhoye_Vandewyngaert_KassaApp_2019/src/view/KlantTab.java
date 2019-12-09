package view;

import controller.ArtikelController;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import jxl.read.biff.BiffException;

import domain.Artikel;
import model.observer.Observer;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KlantTab extends GridPane implements Observer {
    private TableView table;
    private HBox hbox;
    private ArtikelController artikelController;
    private String artikelNr, artikelNaam, artikelGroep, artikelPrijs, artikelVoorraad;

    public KlantTab() throws BiffException, IOException {
        this.artikelController = new ArtikelController();
        this.setPadding(new Insets(5,5,5,5));
        this.setVgap(5);
        this.setHgap(5);

        Label totaalLabel = new Label("Totaal: ");

        TableColumn Naam = new TableColumn<>("Naam");
        TableColumn Aantal = new TableColumn<>("Aantal");
        TableColumn Prijs = new TableColumn<>("Prijs");

        Naam.setCellValueFactory(new PropertyValueFactory("Naam"));
        Aantal.setCellValueFactory(new PropertyValueFactory("Aantal"));
        Prijs.setCellValueFactory(new PropertyValueFactory("Prijs"));

        table = new TableView<String>();
        table.setPrefWidth(REMAINING);
        table.getColumns().addAll(Naam, Aantal, Prijs);
        table.getSortOrder().add(Naam);

        hbox = new HBox(totaalLabel);

        this.add(table, 0, 4, 2, 2);
        this.add(hbox, 0, 2, 1, 1);
    }

    @Override
    public void update(ObservableList<Artikel> klantlist) throws IOException, BiffException {
        Label totaalLabel = new Label("Totaal: ");

        TableColumn Naam = new TableColumn<>("Naam");
        TableColumn Aantal = new TableColumn<>("Aantal");
        TableColumn Prijs = new TableColumn<>("Prijs");

        Naam.setCellValueFactory(new PropertyValueFactory("Naam"));
        Aantal.setCellValueFactory(new PropertyValueFactory("Aantal"));
        Prijs.setCellValueFactory(new PropertyValueFactory("Prijs"));

        table = new TableView<String>();
        table.setPrefWidth(REMAINING);
        table.setItems(artikelController.getKlantObservable());
        table.getColumns().addAll(Naam, Aantal, Prijs);
        table.getSortOrder().add(Naam);

        hbox = new HBox(totaalLabel);

        this.add(table, 0, 4, 2, 2);
        this.add(hbox, 0, 2, 1, 1);
    }
}