package database;

import domain.Artikel;
import domain.DomainException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.observer.Observable;
import model.observer.Observer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ArtikelDbInMemory implements ArtikelDbStrategy, Observable {
    private LoadSave strategy;
    private HashMap<String,Artikel> artikels;
    private ArrayList<Observer> observers;

    public void leesIn() throws FileNotFoundException { }

    @Override
    public ArrayList<Artikel> load(String file) throws IOException, BiffException { return strategy.load(file); }

    @Override
    public void save(ArrayList<Artikel> artikels,String file) throws DomainException, WriteException, IOException, BiffException { strategy.save(artikels,file); }

    public Artikel findArtikel(String id) {
        return null;
        //todo
    }

    public ArrayList<Observer> getObservers() { return observers; }

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
}
