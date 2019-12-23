package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
//@author Eline

public class KlantMainPane extends BorderPane {
    public KlantMainPane(Pane klantTab) {
        TabPane tabPane = new TabPane();
        Tab klantenTab = new Tab("Klant", klantTab);
        tabPane.getTabs().add(klantenTab);
        this.setCenter(tabPane);
    }
}