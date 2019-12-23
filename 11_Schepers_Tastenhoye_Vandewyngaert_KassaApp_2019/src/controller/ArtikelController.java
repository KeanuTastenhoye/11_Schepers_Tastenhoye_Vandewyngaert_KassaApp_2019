package controller;

import database.ArtikelDbInMemory;
import database.KortingLezer;
import domain.Artikel;
import database.Service;

import domain.KortingEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;

import java.time.LocalDateTime;
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
    private int aantalVerkoopSessies;
    private List<Artikel> artikels;
    //private List<Artikel> nieuweArtikels;
    //private List<Artikel> oudeArtikels;
    private List<Artikel> onHold;
    //private List<Artikel> verkoopArtikels;
    //private List<Artikel> klantArtikels;
    private KortingLezer lezer;
    //private double prijs;
    //private double onHoldPrijs;
    private LocalDateTime now;
    private Double totaalBedrag;
    private Double korting;
    private Double teBetalenBedrag;

    private Stage stage;
// @author Keanu,Eline,Phloy
    public ArtikelController() {
        service = new Service();
        aantalVerkoopSessies=0;
        artikels = new ArrayList<>();
        //nieuweArtikels = new ArrayList<>();
        //oudeArtikels = new ArrayList<>();
        onHold = new ArrayList<>();
        //verkoopArtikels = new ArrayList<>();
        //klantArtikels = new ArrayList<>();
        lezer=new KortingLezer();

        this.stage = new Stage();
    }

    //Standaard Getters
    public Service getService() {
        return this.service;
    }
    public void addVerkoopsessie(){aantalVerkoopSessies++;}
    public void resetVerkoopsessies(){aantalVerkoopSessies=0;}
    public int getAantalVerkoopSessies(){return aantalVerkoopSessies;}
    public List<Artikel> getArtikels() throws BiffException, IOException {
        List<Artikel> artik = new ArrayList<>();
        for (Artikel a: service.getArtikels()) {
            if (!isInList(artik, a)) {
                artik.add(a);
            }
        }
        return artik;
    }
    public List<Artikel> getOnHold() { return onHold; }

    public double getOnHoldPrijs() {
        double prijs=0;
        for(Artikel a: onHold) {
            prijs+=Double.parseDouble(a.getArtikelPrijs());
        }
        return prijs;
    }

    //Methodes verbonden met tabs
    public List<Artikel> getVerkoopArtikels(String artikelNr) throws BiffException, IOException {
        for (Artikel a: getService().getArtikels()) {
            if (a.getArtikelNr().equals(artikelNr)) {
                artikels.add(a);
                //verkoopArtikels.add(a);
                System.out.println("Verk Artikels: " + artikels.toString());
                return artikels;
            }
        }
        throw new IllegalArgumentException("Het artikel nummer bestaat niet");
    }

    public boolean hasGroup(String groep) {
        for(Artikel a : artikels) {
            if(a.getArtikelGroep().equals(groep)) {
                return true;
            }
        }
        return  false;
    }

    public boolean contains(List<Artikel> artikels, Artikel art){
        for (Artikel a:artikels) {
            if (a.getArtikelNaam().equals(art.getArtikelNaam())) return false;
            else if (a.getArtikelGroep().equals(art.getArtikelGroep())) return false;
        }
        return true;
    }

    public boolean isInList(List<Artikel> artikels, Artikel art) {
        boolean waarde = false;

        if (!artikels.isEmpty()) {
            for (Artikel a : artikels) {
                if (a.getArtikelNr().equals(art.getArtikelNr())) {
                    return true;
                }
                waarde = false;
            }
        }

        return waarde;
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

    public Map<String, List<Artikel>> getVerkoopArtikelsNietDubbelObserver(List<Artikel> kutlijst) throws BiffException, IOException {
        Map<String, List<Artikel>> artikelMap = new HashMap<>();
        //int aantal = 1;

        for (Artikel a: kutlijst) {
            //bestaand artikel aantal ++
            if (artikelMap.containsKey(a.getArtikelNr())) {
                //int aantalInLijst = Integer.parseInt(artikelMap.get(a.getArtikelNr()).get(1)) + 1;

                List<Artikel> artikelLijst = new ArrayList<>();

                Artikel artikel = new Artikel(a.getArtikelNr(), a.getArtikelNaam(), a.getArtikelGroep(), a.getArtikelPrijs(), a.getArtikelVoorraad());
                artikelLijst.add(artikel);
                //artikelLijst.add(a.getArtikelNaam());
                //artikelLijst.add(String.valueOf(aantalInLijst));
                //artikelLijst.add(a.getArtikelPrijs());

                artikelMap.replace(a.getArtikelNr(), artikelLijst);
            } else {//anders  gwn toevoegen aan de lijst
                List<Artikel> artikelLijst = new ArrayList<>();

                Artikel artikel = new Artikel(a.getArtikelNr(), a.getArtikelNaam(), a.getArtikelGroep(), a.getArtikelPrijs(), a.getArtikelVoorraad());
                artikelLijst.add(artikel);
                //artikelLijst.add(a.getArtikelNaam());
                //artikelLijst.add(String.valueOf(aantal));
                //artikelLijst.add(a.getArtikelPrijs());

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

    public List<Artikel> mapToListArtikel(Map<String, List<Artikel>> artikels) {
        List<Artikel> arts = new ArrayList<>();

        for (String key:artikels.keySet()) {
            arts.addAll(artikels.get(key));
        }
        return arts;
    }

    public List<Artikel> getAllScannedArtikels() {
        //return klantArtikels;
        return artikels;
    }

    public List<Artikel> getAllScannedArtikelsv2() {
        //return verkoopArtikels;
        return artikels;
    }

    public void setLoggen(Double prijs, Double kort) {
        now = LocalDateTime.now();
        totaalBedrag = prijs;
        korting = kort;
        teBetalenBedrag = totaalBedrag - korting;
    }

    public String loggen() {
        return "BETAALD " + now + " - " + totaalBedrag + " - " + korting + " - " + teBetalenBedrag;
    }

    public double getAmountOfKorting() throws IOException, BiffException {
        double res=0;
        if(getKortingen().containsKey(KortingEnum.GROEP)) {
            double groepskorting=0;
            for(Artikel a: getAllScannedArtikelsv2()) {
                //System.out.println("KIJK HIERRRRRRRRRRRR"+getKortingen().get(KortingEnum.GROEP).get(0)+""+a.getArtikelGroep()+"h");
                if(a.getArtikelGroep().equals(getKortingen().get(KortingEnum.GROEP).get(0))) {
                    groepskorting+=(Double.parseDouble(a.getArtikelPrijs()))*Integer.parseInt(getKortingen().get(KortingEnum.GROEP).get(1))/100;
                }
            }
            res+=groepskorting;
        }
        if (getKortingen().containsKey(KortingEnum.DREMPEL)) {
            double drempelkorting = 0;
            if (getTotalPriceScannedItems() >= Integer.parseInt(getKortingen().get(KortingEnum.DREMPEL).get(1))) {
                for (Artikel a : getAllScannedArtikelsv2()) {
                    if (a.getArtikelGroep() == getKortingen().get(KortingEnum.GROEP).get(1)) {
                        drempelkorting += (Double.parseDouble(a.getArtikelPrijs()))/100*20;
                    }
                }
                res += drempelkorting * Integer.parseInt(getKortingen().get(KortingEnum.DREMPEL).get(1))/100;
            }
        }

        if (getKortingen().containsKey(KortingEnum.DUURSTE)) {
            double duursteKorting = 0;

            for (Artikel a : getAllScannedArtikelsv2()) {
                if (Double.parseDouble(a.getArtikelPrijs())>duursteKorting) {
                    duursteKorting= (Double.parseDouble(a.getArtikelPrijs()));
                }
            }
            res += (duursteKorting*Integer.parseInt(getKortingen().get(KortingEnum.DUURSTE).get(1)))/100;


        }
        return res;

    }

    public double getTotalPriceScannedItems() {
        double res=0;
        for(Artikel a:getAllScannedArtikelsv2()) {
            res+=Double.parseDouble(a.getArtikelPrijs());
        }
        return res;
    }

    public HashMap<KortingEnum,ArrayList<String>> getKortingen() throws IOException, BiffException {
        return lezer.load();
    }

    public List<Artikel> getDeleteVerkoopArtikels(String artikelNr) throws BiffException, IOException {

        /*oudeArtikels.clear();
        nieuweArtikels.clear();
        oudeArtikels.addAll(artikels);
        for (Artikel a: artikels) {
            if (!a.getArtikelNr().equals(artikelNr)) {
                nieuweArtikels.add(a);

            } else {
                oudeArtikels.remove(a);
                return nieuweArtikels;
            }
        }
        artikels.clear();
        artikels.addAll(oudeArtikels);
        return nieuweArtikels;*/
        for(Artikel a: artikels)
        {
            if(a.getArtikelNr().equals(artikelNr))
            {
                artikels.remove(a);
                return artikels;
            }
        }
        return artikels;
    }

    public double getVerkoopPrijs(String artikelNr) {
        /*for (Artikel a: verkoopArtikels) {
            if (a.getArtikelNr().equals(artikelNr)) {
                prijs += Double.parseDouble(a.getArtikelPrijs());
                return prijs - onHoldPrijs;
            }
        }
        throw new IllegalArgumentException("Het artikel nummer bestaat niet");*/
        double prijs=0;
        double onHoldPrijs=0;
        if(artikels.isEmpty()){return prijs;}
        for (Artikel a: artikels) {

            prijs += Double.parseDouble(a.getArtikelPrijs());
            //return prijs - onHoldPrijs;

        }
        return prijs;
        //throw new IllegalArgumentException("Het artikel nummer bestaat niet");
    }

    public double getDeleteVerkoopPrijs(String artikelNr) {
        /*try {
            for (Artikel a: getArtikels()) {
                if (a.getArtikelNr().equals(artikelNr)) {
                    verkoopArtikels.remove(a);
                    prijs -= Double.parseDouble(a.getArtikelPrijs());
                    return prijs;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Het artikel nummer bestaat niet");*/

        return getVerkoopPrijs(artikelNr);
    }

    public void setOnHold() {
        onHold.addAll(artikels);
        //verkoopArtikels.removeAll(verkoopArtikels);
        artikels.removeAll(onHold);
    }

    public void setOnHoldPrijs() {
        for (Artikel a: onHold) {
            //onHoldPrijs += Double.parseDouble(a.getArtikelPrijs());
        }
    }

    public void clearOnHold() {
        artikels.removeAll(artikels);
        artikels.addAll(onHold);
        onHold.removeAll(onHold);
        //todo: changed this
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

    public void doObserver() throws IOException, BiffException {
        ArtikelDbInMemory.getInstance().notifyObservers(getKlantObservable());
    }

    public void emptyList() {
        artikels = new ArrayList<>();
    }

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

    public ObservableList<Artikel> getKlantObservable() throws BiffException, IOException {
        ObservableList<Artikel> artikels = FXCollections.observableArrayList(mapToListArtikel(getVerkoopArtikelsNietDubbelObserver(getAllScannedArtikelsv2())));
        return artikels;
    }
}