package domain;

import model.observer.Observable;
import model.observer.Observer;

import java.util.ArrayList;

public class Artikel implements Observable {
    private String artikelNr;
    private String artikelNaam;
    private String artikelGroep;
    private String artikelPrijs;
    private String artikelVoorraad;
    private ArrayList<Observer> observers;

    //Constructors
    public Artikel(String artikelNr, String artikelNaam, String artikelGroep, String artikelPrijs, String artikelVoorraad) {
        setArtikelNr(artikelNr);
        setArtikelNaam(artikelNaam);
        setArtikelGroep(artikelGroep);
        setArtikelPrijs(artikelPrijs);
        setArtikelVoorraad(artikelVoorraad);
        observers = new ArrayList<Observer>();
    }

    public Artikel(String artikelNaam, String artikelGroep, String artikelPrijs, String artikelVoorraad) {
        setArtikelNaam(artikelNaam);
        setArtikelGroep(artikelGroep);
        setArtikelPrijs(artikelPrijs);
        setArtikelVoorraad(artikelVoorraad);
        observers = new ArrayList<Observer>();
    }

    public Artikel(String artikelNaam, String artikelPrijs) {
        setArtikelNaam(artikelNaam);
        setArtikelPrijs(artikelPrijs);
        observers = new ArrayList<Observer>();
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

    public ArrayList<Observer> getObservers() { return observers; }

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

    public void setArtikelVoorraad(String artikelVoorraad) {
        this.artikelVoorraad = artikelVoorraad;
    }

    //Observer methodes
    @Override
    public void addObserver(Observer o) { observers.add(o); }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(artikelNr, artikelNaam, artikelGroep, artikelPrijs, artikelVoorraad);
        }
    }

    //Andere methodes
    @Override
    public String toString() {
        return getArtikelNr() + " " + getArtikelNaam() + " " + getArtikelGroep() + " " + getArtikelPrijs() + " " + getArtikelVoorraad();
    }
}