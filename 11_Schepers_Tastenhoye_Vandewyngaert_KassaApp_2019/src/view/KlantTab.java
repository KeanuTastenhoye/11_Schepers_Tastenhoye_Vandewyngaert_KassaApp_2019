package view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import domain.Artikel;

import model.observer.Observer;
//@author Phloy,Keanu,Eline

public class KlantTab extends GridPane implements Observer {

    TableView table = new TableView<String>();

    public KlantTab() {}

    private <T> boolean checkTableColumn(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> col : tableView.getColumns()) {
            if (col.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(ObservableList<Artikel> klantlist) {
        //System.out.println("Verkregen klanten list: " + klantlist);


        if (checkTableColumn(table, "Nr") && checkTableColumn(table, "Naam") &&
            checkTableColumn(table, "Groep") && checkTableColumn(table, "Prijs") && checkTableColumn(table, "Voorraad")) {
            //System.out.println("De tabel bestaat al.");

            table.setItems(klantlist);
            table.refresh();
        } else {
            //System.out.println("De tabel bestaat nog niet.");

            setPadding(new Insets(5,5,5,5));
            setVgap(5);
            setHgap(5);

            Label totaalLabel = new Label("Totaal: ");

            TableColumn artikelNr = new TableColumn<>("Nr");
            TableColumn artikelNaam = new TableColumn<>("Naam");
            TableColumn artikelGroep = new TableColumn<>("Groep");
            TableColumn artikelPrijs = new TableColumn<>("Prijs");
            TableColumn artikelVoorraad = new TableColumn<>("Voorraad");
            //TableColumn artikelAantal = new TableColumn<>("Aantal");

            artikelNr.setCellValueFactory(new PropertyValueFactory("artikelNr"));
            artikelNaam.setCellValueFactory(new PropertyValueFactory("artikelNaam"));
            artikelGroep.setCellValueFactory(new PropertyValueFactory("artikelGroep"));
            artikelPrijs.setCellValueFactory(new PropertyValueFactory("artikelPrijs"));
            artikelVoorraad.setCellValueFactory(new PropertyValueFactory("artikelVoorraad"));
            //artikelAantal.setCellValueFactory(new PropertyValueFactory("artikelAantal"));

            table.setPrefWidth(REMAINING);
            table.setItems(klantlist);
            table.getColumns().addAll(artikelNr, artikelNaam, artikelGroep, artikelPrijs, artikelVoorraad);
            table.getSortOrder().add(artikelNaam);

            HBox totaal = new HBox(totaalLabel);

            add(totaal, 0, 2, 1, 1);
            add(table, 0, 4, 2, 2);
        }
    }
}