package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import database.PropertySchrijver;

public class InstellingTab extends GridPane {
    private PropertySchrijver schrijver;

    public InstellingTab() {
        this.schrijver = new PropertySchrijver();
        this.setPadding(new Insets(5,5,5,5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Kies je voorkeur van opslagstrategie: "), 0, 0, 1, 1);

        ToggleGroup groep = new ToggleGroup();

        RadioButton tekstButton = new RadioButton("Tekst");
        RadioButton excelButton = new RadioButton("Excel");

        tekstButton.setToggleGroup(groep);
        excelButton.setToggleGroup(groep);

        Button knop = new Button("Save");

        VBox vbox = new VBox(tekstButton, excelButton, knop);

        this.add(vbox, 0, 1, 2, 6);

        knop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String keuze = null;

                if (tekstButton.isSelected()) {
                    keuze = "Tekst";
                } else {
                    keuze = "Excel";
                }

                schrijver.write(keuze);
            }
        });
    }
}