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
    public int getAantal(List<Artikel> artikels, String naam){
        int aantal = 0;

        for (Artikel a: artikels) {
            if(a.getArtikelNaam().equals(naam)){
                aantal++;
            }
        }
        return aantal;
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
        Map<String, List<Artikel>> artMap = controller.getVerkoopArtikelsNietDubbel(artList);

        String decorator = "Omschrijving                 |Aantal       |Prijs  \n";
        decorator += "---------------------------------------------------\n";

        for (String key:artMap.keySet()) {
            decorator += (artMap.get(key).get(0) + "                          |" + artMap.get(key).get(1) + "       |" + artMap.get(key).get(2) + "\n");
        }

        decorator += "---------------------------------------------------\n";
        //  decorator += "Betaald (incl korting                              \n";
        return decorator;
    }


}
