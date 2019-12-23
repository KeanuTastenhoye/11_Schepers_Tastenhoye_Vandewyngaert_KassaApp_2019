package database;

import domain.Artikel;
import domain.DomainException;
import javafx.collections.ObservableList;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.observer.Observable;
import model.observer.Observer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//@author Phloy,Keanu,Eline

public class ArtikelDbInMemory implements ArtikelDbStrategy, Observable {
    private LoadSave strategy;
    private volatile static ArtikelDbInMemory uniqueInstance;
    private HashMap<String,Artikel> artikels;
    private ArrayList<Observer> observers;

    public ArtikelDbInMemory() {
        this.artikels = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    public void leesIn() throws FileNotFoundException { }

    @Override
    public ArrayList<Artikel> load(File file) throws IOException, BiffException { return strategy.load(file); }

    public HashMap<String, Artikel> getArtikels() {
        return artikels;
    }

    public void setArtikels(HashMap<String, Artikel> artikels) {
        this.artikels = artikels;
    }

    public Artikel getDuurste()
    {
        Artikel artikel= null;
        for(Artikel a: artikels.values())
        {
            if(Integer.parseInt(a.getArtikelPrijs())>Integer.parseInt(
                    artikel.getArtikelPrijs()))
            {
                artikel=a;
            }
        }
        return artikel;
    }
    public static ArtikelDbInMemory getInstance() {
        if (uniqueInstance == null) {
            synchronized (ArtikelDbInMemory.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new ArtikelDbInMemory();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public void save(ArrayList<Artikel> artikels, File file) throws DomainException, WriteException, IOException, BiffException {
       ArrayList<ArrayList<String>> saveArtikels = new ArrayList<>();
       for(Artikel a: artikels)
       {
           for(int i=0; i<saveArtikels.size();i++)
           {
               saveArtikels.add(new ArrayList<String>());
               saveArtikels.get(i).add((a.getArtikelNr()));
               saveArtikels.get(i).add((a.getArtikelNaam()));
               saveArtikels.get(i).add((a.getArtikelGroep()));
               saveArtikels.get(i).add((a.getArtikelPrijs()));
               saveArtikels.get(i).add((a.getArtikelVoorraad()));

           }
       }
        strategy.save(saveArtikels, file);
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
    public void notifyObservers(ObservableList<Artikel> klantlist) throws IOException, BiffException {
        for (Observer o: observers) {
            o.update(klantlist);
        }
    }
    /*
    public void notifyObservers() throws IOException, BiffException {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            for (Artikel a: artikels.values()) {
                observer.update(a.getArtikelNr(), a.getArtikelNaam(), a.getArtikelGroep(), a.getArtikelPrijs(), a.getArtikelVoorraad());
            }
        }
    }
    */
}
