package model.decorator;

import controller.ArtikelController;
import domain.Artikel;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Kassabon extends KassabonAbstract {
    private ArtikelController controller;


    public Kassabon() {
        controller = new ArtikelController();
    }


    // Get aantal per gescand product
    public double getTotaalPrijs(List<Artikel> artikels){
        double totaal = 0;

        for (Artikel a: artikels) {
            totaal += Double.parseDouble(a.getArtikelPrijs());
        }
        return totaal;
    }


    private List<Artikel> getPrintableListVanArtikels() throws BiffException, IOException {
        List<Artikel> artikels1 = controller.getAllScannedArtikels();
        List<Artikel> artikelPrintfz = new ArrayList<>();

        for (Artikel a: artikels1) {
            if (controller.contains(artikelPrintfz, a)) {

            }
        }
        return artikels1;
    }

    public String print(List<Artikel> artList) throws BiffException, IOException {
        Map<String, List<String>> artMap = controller.getVerkoopArtikelsNietDubbel(artList);
        double totaal = 0;

        String decorator = "Omschrijving                 |Aantal       |Prijs  \n";
        decorator += "---------------------------------------------------\n";

        for (String key:artMap.keySet()) {
            decorator += (artMap.get(key).get(0) + "                          |" + artMap.get(key).get(1) + "       |" + artMap.get(key).get(2) + "\n");
        totaal += Double.parseDouble(artMap.get(key).get(2));
        }

        decorator += "---------------------------------------------------\n";
        //KORTING MOET ER NOG AF GETROKKEN WORDEN
        decorator += "Betaald (incl korting)                        " + getTotaalPrijs(artList) + " EUR\n";
        return decorator;
    }


}
