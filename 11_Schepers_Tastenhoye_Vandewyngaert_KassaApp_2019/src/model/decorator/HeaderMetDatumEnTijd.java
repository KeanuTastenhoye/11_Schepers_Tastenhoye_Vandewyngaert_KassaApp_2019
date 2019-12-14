package model.decorator;

import domain.Artikel;

import java.time.LocalDateTime;
import java.util.List;

public class HeaderMetDatumEnTijd extends KassabonDecorator{

    private  KassabonAbstract kassabon;
    private  String text;


    @Override
    public String getText() {
        //hier moet je naar de enum gaan

        LocalDateTime now = LocalDateTime.now();
        return now +" \n";
    }

    @Override
    public String print(List<Artikel> artikels) {
        return getText() + kassabon.print(artikels);
    }

}
