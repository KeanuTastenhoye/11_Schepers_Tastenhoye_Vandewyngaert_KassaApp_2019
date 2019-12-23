package model.decorator;

import domain.Artikel;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;

public class FooterKorting  extends KassabonDecorator {
    private KassabonAbstract kassabon;
    private  String text;
    private double prijs;

    public FooterKorting(KassabonAbstract kassabon, String text) {
        this.kassabon = kassabon;
        this.text = text;
    }

    public FooterKorting(KassabonAbstract kassabon) {
        this.kassabon = kassabon;
        this.text = getText();
    }

    @Override
    public String getText() {
        return "Ohh wat leuk, je hebt korting, anders moet je : ";
    }

    @Override
    public String print(List<Artikel> artList) throws BiffException, IOException {
        this.prijs = getTotaalPrijs(artList);
        return kassabon.print(artList) + getText() + prijs + " EUR betalen";
    }
}
