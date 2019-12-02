package model.observer;

public interface Observer {
    void update(String artikelNr, String artikelNaam, String artikelGroep, String artikelPrijs, String artikelVoorraad);
}
