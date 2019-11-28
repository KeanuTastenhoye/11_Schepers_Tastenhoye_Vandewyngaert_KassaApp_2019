package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class KassaMainPane extends BorderPane {
    public KassaMainPane(Pane kassaTab, Pane artikelTab, Pane instellingTab){
        TabPane tabPane = new TabPane();
        Tab kassasTab = new Tab("Kassa", kassaTab);
        Tab artikelsTab = new Tab("Artikelen", artikelTab);
        Tab instellingsTab = new Tab("Instellingen", instellingTab);
        Tab logTab = new Tab("Log");

        tabPane.getTabs().add(kassasTab);
        tabPane.getTabs().add(artikelsTab);
        tabPane.getTabs().add(instellingsTab);
        tabPane.getTabs().add(logTab);

        this.setCenter(tabPane);
    }
}