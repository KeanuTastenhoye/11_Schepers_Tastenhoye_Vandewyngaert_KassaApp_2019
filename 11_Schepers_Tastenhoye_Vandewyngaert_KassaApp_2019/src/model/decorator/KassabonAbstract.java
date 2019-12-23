package model.decorator;

import domain.Artikel;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;

public abstract class KassabonAbstract {
    String text ="";

    public String getText(){
        return text;
    }

    public double getTotaalPrijs(List<Artikel> artikels){
        double totaal = 0;

        for (Artikel a: artikels) {
            totaal += Double.parseDouble(a.getArtikelPrijs());
        }
        return totaal;
    }
    public abstract String print(List<Artikel> artList) throws BiffException, IOException;
}
