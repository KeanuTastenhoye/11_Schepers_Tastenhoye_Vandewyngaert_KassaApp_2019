package model.decorator;

import domain.Artikel;

import java.util.List;

public class FooterKorting  extends KassabonDecorator{
    private  KassabonAbstract kassabon;
    private  String text;


    @Override
    public String getText() {
        //hier moet je naar de enum gaan
        //prijs zonder koring + korting bedrag meoten hier komen
        return "Ohh wat leuk, je hebt korting: \n";
    }

    @Override
    public String print(List<Artikel> artikels) {
        return kassabon.print(artikels) + getText();
    }
}
