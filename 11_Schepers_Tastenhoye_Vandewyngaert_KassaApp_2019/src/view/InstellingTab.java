package view;

import database.Methode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import database.PropertySchrijver;

import java.lang.reflect.Method;

public class InstellingTab extends GridPane {
    private PropertySchrijver schrijver;

    public InstellingTab() {
        this.schrijver = new PropertySchrijver();
        this.setPadding(new Insets(5,5,5,5));
        this.setVgap(5);
        this.setHgap(5);

        //Opslag strategie
        this.add(new Label("Kies je voorkeur van opslagstrategie: "), 0, 0, 2, 1);

        ToggleGroup groep = new ToggleGroup();

        RadioButton tekstButton = new RadioButton("Tekst");
        RadioButton excelButton = new RadioButton("Excel");

        tekstButton.setToggleGroup(groep);
        excelButton.setToggleGroup(groep);

        Button knop = new Button("Save");

        this.add(tekstButton, 0, 2, 1, 1);
        this.add(excelButton, 0, 3, 1, 1);
        this.add(knop, 0, 5, 1, 1);

        //Korting
        this.add(new Label("Kies de korting: "), 0,7,2,1);

        ToggleGroup kortingen = new ToggleGroup();
        ToggleGroup groepen = new ToggleGroup();
        ToggleGroup drempels = new ToggleGroup();

        RadioButton groepkorting = new RadioButton("Groepskorting");

        RadioButton gr1 = new RadioButton("gr1");
        RadioButton gr2 = new RadioButton("gr2");

        TextField groepTXT = new TextField();

        Label lblGroep = new Label("%");

        Button groepBtn = new Button("OK");

        RadioButton drempelkorting = new RadioButton("Drempelkorting");

        RadioButton dr1 = new RadioButton("50");
        RadioButton dr2 = new RadioButton("100");

        TextField drempelTXT = new TextField();

        Label lblDrempel = new Label("%");

        Button drempelBtn = new Button("OK");

        RadioButton duurstekorting = new RadioButton("Duurstekorting");

        TextField duursteTXT = new TextField();

        Label lblDuurste = new Label("%");

        Button duursteBtn = new Button("OK");

        groepkorting.setToggleGroup(kortingen);
        drempelkorting.setToggleGroup(kortingen);
        duurstekorting.setToggleGroup(kortingen);

        gr1.setToggleGroup(groepen);
        gr2.setToggleGroup(groepen);

        dr1.setToggleGroup(drempels);
        dr2.setToggleGroup(drempels);

        this.add(groepkorting, 0, 9, 1, 1);
        this.add(gr1, 1, 11, 1, 1);
        this.add(gr2, 1, 12, 1, 1);
        this.add(groepTXT, 2, 12, 1, 1);
        this.add(lblGroep, 3, 12, 1, 1);
        this.add(groepBtn, 4, 12, 1, 1);

        this.add(drempelkorting, 0, 13, 1, 1);
        this.add(dr1, 1, 15, 1, 1);
        this.add(dr2, 1, 16, 1, 1);
        this.add(drempelTXT, 2, 16, 1, 1);
        this.add(lblDrempel, 3, 16, 1, 1);
        this.add(drempelBtn, 4, 16, 1, 1);

        this.add(duurstekorting, 0, 17, 1, 1);
        this.add(duursteTXT, 2, 19, 1, 1);
        this.add(lblDuurste, 3, 19, 1, 1);
        this.add(duursteBtn, 4, 19, 1, 1);

        knop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Methode keuze = null;

                if (tekstButton.isSelected()) {
                    keuze = Methode.TEXT;
                } else {
                    keuze = Methode.EXCEL;
                }

                schrijver.write(keuze);
            }
        });
    }
}