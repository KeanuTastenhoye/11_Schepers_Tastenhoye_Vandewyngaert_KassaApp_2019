package model.decorator;

import domain.Artikel;

import java.util.List;

public class FoorterMetBtw   extends KassabonDecorator{
    private  KassabonAbstract kassabon;
    private  String text;


    @Override
    public String getText() {
        //hier moet je naar de enum gaan
        //prijs zonder koring + korting bedrag meoten hier komen
        return "dit is hoe veel btw er is : \n";
    }

    @Override
    public String print(List<Artikel> artikels) {
        return kassabon.print(artikels) + getText();
    }
}
