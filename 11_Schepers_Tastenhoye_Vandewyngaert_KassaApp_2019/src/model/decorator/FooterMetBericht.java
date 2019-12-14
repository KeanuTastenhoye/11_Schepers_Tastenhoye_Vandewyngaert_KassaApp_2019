package model.decorator;

import domain.Artikel;

import java.util.List;

public class FooterMetBericht   extends KassabonDecorator{
    private  KassabonAbstract kassabon;
    private  String text;


    @Override
    public String getText() {
        //hier moet je naar de enum gaan

        return "THANK YOU come again \n";
    }

    @Override
    public String print(List<Artikel> artikels) {
        return kassabon.print(artikels) + getText();
    }
}
