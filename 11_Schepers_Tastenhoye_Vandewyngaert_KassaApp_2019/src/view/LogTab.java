package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class LogTab extends GridPane {

    public LogTab(String s) {
        this.setPadding(new Insets(5,5,5,5));
        this.setVgap(2);
        this.setHgap(5);

        this.add(new Label(s), 0, 0, 1, 1);
    }
}