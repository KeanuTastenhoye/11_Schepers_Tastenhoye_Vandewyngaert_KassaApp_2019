package model.decorator;

import domain.Artikel;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;

public class FooterMetBtw extends KassabonDecorator{
    private  KassabonAbstract kassabon;
    private  String text;

    public FooterMetBtw(KassabonAbstract kassabon, String text) {
        this.kassabon = kassabon;
        this.text = text;
    }

    public FooterMetBtw(KassabonAbstract kassabon) {
        this.kassabon = kassabon;
        this.text = getText();
    }

    @Override
    public String getText() {
        //hier moet je naar de enum gaan
        //prijs zonder koring + korting bedrag meoten hier komen
        return "dit is hoe veel btw er is : \n";
    }

    @Override
    public String print(List<Artikel> artList) throws BiffException, IOException {
        return kassabon.print(artList) + getText();
    }
}
