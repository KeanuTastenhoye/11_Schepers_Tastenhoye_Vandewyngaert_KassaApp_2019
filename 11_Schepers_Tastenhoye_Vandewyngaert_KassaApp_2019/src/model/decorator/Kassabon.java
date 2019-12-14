package model.decorator;

import domain.Artikel;

import java.util.ArrayList;
import java.util.List;

public abstract class Kassabon extends KassabonAbstract {
    private List<Artikel> artikels;


    public Kassabon(List<Artikel> artikels) {
        artikels = new ArrayList<>();
    }

    private int getAantal(List<Artikel> artikels, String naam){
        int aantal = 0;

        for (Artikel a: artikels) {
            if(a.getArtikelNaam().equals(naam)){
                aantal++;
            }
        }

        return aantal;
    }

    private boolean contains(List<Artikel> artikels, Artikel art){

        for (Artikel a:artikels) {
            if(a.getArtikelNaam().equals(art.getArtikelNaam())) return false;
            else if(a.getArtikelGroep().equals(art.getArtikelGroep())) return false;
        }
        return true;
    }

    private List<Artikel> getJuistListVanArtikels (List<Artikel> artikels){
        List<Artikel>artikels1 = new ArrayList<>();
        Artikel art = new Artikel();


        for (Artikel a:artikels) {

        }

        return artikels1;
    }



    public String print() {

        List<Artikel> artikelList = getJuistListVanArtikels(artikels);


        String decorator = "Omschrijving                 |Aantal       |Prijs  \n";
        decorator += "---------------------------------------------------\n";
        decorator += "                             |             |       \n";
        decorator += "";

        for (Artikel a:artikelList) {
            decorator += (a.getArtikelNaam() + "                    |      " + getAantal(artikels,a.getArtikelNaam()) + "      |       " + a.getArtikelPrijs() + "\n");
        }

        decorator += decorator += "                             |             |       \n";
        decorator += "---------------------------------------------------\n";
        //  decorator += "Betaald (incl korting                              \n";
        return decorator;
    }


}
