package view;

import controller.ArtikelController;

import domain.Artikel;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import jxl.read.biff.BiffException;
import model.observer.Observer;

import java.io.IOException;
//@author Phloy,Keanu,Eline

public class ArtikelTab extends GridPane implements Observer {
    private TableView table;
    private ArtikelController artikelController;

    public ArtikelTab() throws BiffException, IOException {
        this.artikelController = new ArtikelController();
        this.setPadding(new Insets(5,5,5,5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Artikels: "), 0, 0, 1, 1);

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
        table.setItems(artikelController.getArtikelObservable());
        table.getColumns().addAll(artikelNr, artikelNaam, artikelGroep, artikelPrijs, artikelVoorraad);
        table.getSortOrder().add(artikelNaam);

        add(table, 0, 1, 2, 6);
    }

    @Override
    public void update(ObservableList<Artikel> klantlist) throws IOException, BiffException {
        //Vul de table opnieuw na voorraad aanpassing van kassaTab
        table.refresh();
    }
}