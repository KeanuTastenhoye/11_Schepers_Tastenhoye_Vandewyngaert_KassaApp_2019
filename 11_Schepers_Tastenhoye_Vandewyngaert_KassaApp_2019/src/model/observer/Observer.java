package model.observer;

import jxl.read.biff.BiffException;

import java.io.IOException;

public interface Observer {
    void update(String artikelNr, String artikelNaam, String artikelGroep, String artikelPrijs, String artikelVoorraad) throws IOException, BiffException;
}
