package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;

import database.PropertySchrijver;
import jxl.read.biff.BiffException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * //@author Keanu
 */

public class LogTab extends GridPane {

    public LogTab(String s) {
        this.setPadding(new Insets(5,5,5,5));
        this.setVgap(2);
        this.setHgap(5);

        this.add(new Label(s), 0, 0, 1, 1);
    }
}