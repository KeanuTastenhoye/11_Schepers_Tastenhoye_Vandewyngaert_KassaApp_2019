package model.decorator;

import domain.Artikel;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;

public class FooterKorting  extends KassabonDecorator {
    private KassabonAbstract kassabon;
    private  String text;


    @Override
    public String getText() {
        //hier moet je naar de enum gaan
        //prijs zonder koring + korting bedrag meoten hier komen
        return "Ohh wat leuk, je hebt korting: \n";
    }

    @Override
    public String print(List<Artikel> artList) throws BiffException, IOException {
        return kassabon.print(artList) + getText();
    }
}
