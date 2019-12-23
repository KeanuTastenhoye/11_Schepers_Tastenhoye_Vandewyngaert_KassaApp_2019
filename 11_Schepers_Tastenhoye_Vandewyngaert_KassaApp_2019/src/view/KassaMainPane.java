package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
//@author Phloy

public class KassaMainPane extends BorderPane {
    public KassaMainPane(Pane kassaTab, Pane artikelTab, Pane instellingTab, Pane logTab){
        TabPane tabPane = new TabPane();
        Tab kassasTab = new Tab("Kassa", kassaTab);
        Tab artikelsTab = new Tab("Artikelen", artikelTab);
        Tab instellingsTab = new Tab("Instellingen", instellingTab);
        Tab logsTab = new Tab("Log", logTab);

        tabPane.getTabs().add(kassasTab);
        tabPane.getTabs().add(artikelsTab);
        tabPane.getTabs().add(instellingsTab);
        tabPane.getTabs().add(logsTab);

        this.setCenter(tabPane);
    }
}