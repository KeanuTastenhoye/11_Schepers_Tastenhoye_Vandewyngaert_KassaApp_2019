package model.observer;

import domain.Artikel;
import javafx.collections.ObservableList;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;

public interface Observer {
    //void update(String artikelNr, String artikelNaam, String artikelGroep, String artikelPrijs, String artikelVoorraad) throws IOException, BiffException;
    void update(ObservableList<Artikel> klantlist) throws IOException, BiffException;
}

