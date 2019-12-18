package controller;

import database.ArtikelDbInMemory;
import domain.Artikel;
import database.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;
import view.KassaTab;
import view.KassaView;
import view.KlantTab;

public class ArtikelController {
    private final Service service;

    private List<Artikel> artikels;
    private List<Artikel> nieuweArtikels;
    private List<Artikel> oudeArtikels;
    private List<Artikel> onHold;
    private List<Artikel> verkoopArtikels;
    private List<Artikel> klantArtikels;

    private double prijs;
    private double onHoldPrijs;

    private Stage stage;

    public ArtikelController() {
        service = new Service();

        artikels = new ArrayList<>();
        nieuweArtikels = new ArrayList<>();
        oudeArtikels = new ArrayList<>();
        onHold = new ArrayList<>();
        verkoopArtikels = new ArrayList<>();
        klantArtikels = new ArrayList<>();

        this.stage = new Stage();
    }

    //Standaard Getters
    public Service getService() {
        return this.service;
    }

    public List<Artikel> getArtikels() throws BiffException, IOException { return service.getArtikels(); }

    public List<Artikel> getOnHold() { return onHold; }

    public double getOnHoldPrijs() {
        return onHoldPrijs;
    }

    //Methodes verbonden met tabs
    public List<Artikel> getVerkoopArtikels(String artikelNr) throws BiffException, IOException {
        for (Artikel a: getService().getArtikels()) {
            if (a.getArtikelNr().equals(artikelNr)) {
                artikels.add(a);
                verkoopArtikels.add(a);
                System.out.println("Verk Artikels: " + verkoopArtikels);
                return artikels;
            }
        }
        throw new IllegalArgumentException("Het artikel nummer bestaat niet");
    }

    public boolean contains(List<Artikel> artikels, Artikel art){
        for (Artikel a:artikels) {
            if(a.getArtikelNaam().equals(art.getArtikelNaam())) return false;
            else if(a.getArtikelGroep().equals(art.getArtikelGroep())) return false;
        }
        return true;
    }

    /*
    public List<Artikel> getVerkoopArtikelsNietDubbel() throws BiffException, IOException {
        for (Artikel a: verkoopArtikels) {
            if (!contains(klantArtikels, a)) {
                klantArtikels.add(a);
            }
        }
        return klantArtikels;
    }
    */

    public Map<String, List<String>> getVerkoopArtikelsNietDubbel(List<Artikel> kutlijst) throws BiffException, IOException {
        Map<String, List<String>> artikelMap = new HashMap<>();
        int aantal = 1;

        for (Artikel a: kutlijst) {
            //bestaand artikel aantal ++
            if (artikelMap.containsKey(a.getArtikelNr())) {
                int aantalInLijst = Integer.parseInt(artikelMap.get(a.getArtikelNr()).get(1)) + 1;

                List<String> artikelLijst = new ArrayList<>();

                artikelLijst.add(a.getArtikelNaam());
                artikelLijst.add(String.valueOf(aantalInLijst));
                artikelLijst.add(a.getArtikelPrijs());

                artikelMap.replace(a.getArtikelNr(), artikelLijst);

            } else {//anders  gwn toevoegen aan de lijst
                List<String> artikelLijst = new ArrayList<>();

                artikelLijst.add(a.getArtikelNaam());
                artikelLijst.add(String.valueOf(aantal));
                artikelLijst.add(a.getArtikelPrijs());

                artikelMap.put(a.getArtikelNr(), artikelLijst);
            }
        }
        return artikelMap;
    }

    public List<String> mapToListString(Map<String, List<String>> artikels) {
        List<String> arts = new ArrayList<>();


        for (String key:artikels.keySet()) {
            arts.addAll(artikels.get(key));
        }
        return arts;
    }

    public List<Artikel> getAllScannedArtikels() {
        return klantArtikels;
    }

    public List<Artikel> getAllScannedArtikelsv2() {
        return verkoopArtikels;
    }

    public List<Artikel> getDeleteVerkoopArtikels(String artikelNr) throws BiffException, IOException {
        oudeArtikels.clear();
        nieuweArtikels.clear();
        oudeArtikels.addAll(artikels);
        for (Artikel a: artikels) {
            if (!a.getArtikelNr().equals(artikelNr)) {
                nieuweArtikels.add(a);
            } else {
                oudeArtikels.remove(a);
            }
        }
        artikels.clear();
        artikels.addAll(oudeArtikels);
        return nieuweArtikels;
    }

    public double getVerkoopPrijs(String artikelNr) {
        for (Artikel a: verkoopArtikels) {
            if (a.getArtikelNr().equals(artikelNr)) {
                prijs += Double.parseDouble(a.getArtikelPrijs());
                return prijs - onHoldPrijs;
            }
        }
        throw new IllegalArgumentException("Het artikel nummer bestaat niet");
    }

    public double getDeleteVerkoopPrijs(String artikelNr) {
        try {
            for (Artikel a: getArtikels()) {
                if (a.getArtikelNr().equals(artikelNr)) {
                    prijs -= Double.parseDouble(a.getArtikelPrijs());
                    return prijs;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Het artikel nummer bestaat niet");
    }

    public void setOnHold() {
        onHold.addAll(artikels);
        verkoopArtikels.removeAll(verkoopArtikels);
    }

    public void setOnHoldPrijs() {
        for (Artikel a: onHold) {
            onHoldPrijs += Double.parseDouble(a.getArtikelPrijs());
        }
    }

    public void clearOnHold() {
        onHold.clear();
    }

    public boolean correctNr(String artikelNr) {
        try {
            for (Artikel a: service.getArtikels()) {
                if (a.getArtikelNr().equals(artikelNr)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean nrStaatNietInLijst(String artikelNr) {
        for (Artikel a: artikels) {
            if (a.getArtikelNr().equals(artikelNr)) {
                return true;
            }
        }
        return false;
    }

    public void voorraadOmhoog(String artikelNr) {
        for (Artikel a: artikels) {
            if (a.getArtikelNr().equals(artikelNr)) {
                int vooraad = Integer.parseInt(a.getArtikelVoorraad());
                vooraad += 1;
                a.setArtikelVoorraad(Integer.toString(vooraad));
            }
        }
    }

    public void voorraadOmlaag(String artikelNr) throws IOException, BiffException {
        for (Artikel a: getArtikels()) {
            if (a.getArtikelNr().equals(artikelNr)) {
                int vooraad = Integer.parseInt(a.getArtikelVoorraad());
                vooraad -= 1;
                a.setArtikelVoorraad(Integer.toString(vooraad));
            }
        }
    }

    /*
    public void doObserver() throws IOException, BiffException {
        ArtikelDbInMemory.getInstance().notifyObservers(getKlantObservable());
    }

    */

    //Observables
    public ObservableList<Artikel> getArtikelObservable() throws BiffException, IOException {
        ObservableList<Artikel> artikels = FXCollections.observableArrayList(getArtikels());
        return artikels;
    }

    public ObservableList<Artikel> getVerkoopObservable(String artikelNr) throws BiffException, IOException {
        ObservableList<Artikel> artikels = FXCollections.observableArrayList(getVerkoopArtikels(artikelNr));
        return artikels;
    }

    public ObservableList<Artikel> getDeleteVerkoopObservable(String artikelNr) throws BiffException, IOException {
        ObservableList<Artikel> artikels = FXCollections.observableArrayList(getDeleteVerkoopArtikels(artikelNr));
        return artikels;
    }

    public ObservableList<Artikel> getOnHoldObservable() throws BiffException, IOException {
        ObservableList<Artikel> artikels = FXCollections.observableArrayList(getOnHold());
        return artikels;
    }

    /*
    public ObservableList<String> getKlantObservable() throws BiffException, IOException {
        ObservableList<String> artikels = FXCollections.observableArrayList(mapToListString(getVerkoopArtikelsNietDubbel()));
        return artikels;
    }
    */

}