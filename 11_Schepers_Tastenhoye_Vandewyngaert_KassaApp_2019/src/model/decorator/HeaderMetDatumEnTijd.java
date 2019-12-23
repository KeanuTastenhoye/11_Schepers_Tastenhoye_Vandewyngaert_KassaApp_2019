package model.decorator;

import domain.Artikel;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class HeaderMetDatumEnTijd extends KassabonDecorator {

    private KassabonAbstract kassabon;
    private  String text;

    public HeaderMetDatumEnTijd(KassabonAbstract kassabon, String text) {
        this.kassabon = kassabon;
        this.text = text;
    }

    public HeaderMetDatumEnTijd(KassabonAbstract kassabon) {
        this.kassabon = kassabon;
        this.text = getText();
    }


    @Override
    public String getText() {
        //hier moet je naar de enum gaan

        LocalDateTime now = LocalDateTime.now();
        return now +" \n";
    }



    @Override
    public String print(List<Artikel> artList)throws BiffException, IOException {
        return getText() + kassabon.print(artList);
    }

}
