package view;

import controller.ArtikelController;
import database.DecoratorWriter;
import database.KortingSchrijver;
import database.Methode;
import domain.Artikel;
import domain.KortingEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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

public class LogTab extends GridPane {
    private ArtikelController controller;

    public LogTab() {
        this.controller = new ArtikelController();
        this.setPadding(new Insets(5,5,5,5));
        this.setVgap(2);
        this.setHgap(5);

        this.add(new Label(controller.loggen()), 0, 0, 1, 1);
    }
}