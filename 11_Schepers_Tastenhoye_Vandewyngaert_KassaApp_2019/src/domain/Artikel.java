package domain;

import model.observer.Observable;
import model.observer.Observer;

import java.util.ArrayList;

public class Artikel {
    private String artikelNr;
    private String artikelNaam;
    private String artikelGroep;
    private String artikelPrijs;
    private String artikelVoorraad;

    //Constructors
    public Artikel(String artikelNr, String artikelNaam, String artikelGroep, String artikelPrijs, String artikelVoorraad) {
        setArtikelNr(artikelNr);
        setArtikelNaam(artikelNaam);
        setArtikelGroep(artikelGroep);
        setArtikelPrijs(artikelPrijs);
        setArtikelVoorraad(artikelVoorraad);
    }

    public Artikel(String artikelNaam, String artikelGroep, String artikelPrijs, String artikelVoorraad) {
        setArtikelNaam(artikelNaam);
        setArtikelGroep(artikelGroep);
        setArtikelPrijs(artikelPrijs);
        setArtikelVoorraad(artikelVoorraad);
    }

    public Artikel(String artikelNaam, String artikelPrijs) {
        setArtikelNaam(artikelNaam);
        setArtikelPrijs(artikelPrijs);
    }

    public Artikel() {

    }

    //Getters
    public String getArtikelNr() {
        return artikelNr;
    }

    public String getArtikelNaam() {
        return artikelNaam;
    }

    public String getArtikelGroep() {
        return artikelGroep;
    }

    public String getArtikelPrijs() {
        return artikelPrijs;
    }

    public String getArtikelVoorraad() {
        return artikelVoorraad;
    }

    //Setters
    public void setArtikelNr(String artikelNr) {
        this.artikelNr = artikelNr;
    }

    public void setArtikelNaam(String artikelNaam) {
        this.artikelNaam = artikelNaam;
    }

    public void setArtikelGroep(String artikelGroep) {
        this.artikelGroep = artikelGroep;
    }

    public void setArtikelPrijs(String artikelPrijs) {
        this.artikelPrijs = artikelPrijs;
    }

    public void setArtikelVoorraad(String artikelVoorraad) { this.artikelVoorraad = artikelVoorraad; }

    //Andere methodes
    @Override
    public String toString() {
        return getArtikelNr() + " " + getArtikelNaam() + " " + getArtikelGroep() + " " + getArtikelPrijs() + " " + getArtikelVoorraad();
    }
}