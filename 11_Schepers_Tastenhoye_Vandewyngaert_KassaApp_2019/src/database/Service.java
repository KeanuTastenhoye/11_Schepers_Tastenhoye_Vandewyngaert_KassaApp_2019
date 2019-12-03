package database;

import database.LoadSaveFactory;
import domain.Artikel;
import jxl.read.biff.BiffException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Service extends Observable {

    public Service() {
    }

    //Vraagt strategie op en stuurt resultaat naar factory via de main
    public String getStrategy() {
        String lijn;
        String keuze = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\opslagStrategieProperties"));

            while ((lijn = reader.readLine()) != null) {
                if (lijn.charAt(0) != '#') {
                    if (lijn.equals("Opslagstrategie=Tekst")) {
                        keuze = "Tekst";
                    } else {
                        keuze ="Excel";
                    }
                } else {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keuze;
    }

    //Maakt van de Arraylist<ArrayList<String>> een List<Artikel> voor de observableList
    public List<Artikel> getArtikels() throws BiffException, IOException {
        LoadSaveFactory fact = new LoadSaveFactory();
        ArrayList<Artikel> art = new ArrayList<Artikel>();
        List<Artikel> lijst = new ArrayList<>();

        art = fact.maakLoadSaveStrategie(getStrategy()).load(fact.getCorrectFile());

        /*for (int i = 0; i < art.size(); i++) {
            Artikel artikel = new Artikel();

             artikel.setArtikelNr(art.get(i).getArtikelNr());
            artikel.setArtikelNaam(art.get(i).getArtikelNaam());
            artikel.setArtikelGroep(art.get(i).getArtikelGroep());
            artikel.setArtikelPrijs(art.get(i).getArtikelPrijs());
            artikel.setArtikelVoorraad(art.get(i).getArtikelVoorraad());

            lijst.add(artikel);
        }*/



        return art;
    }
}