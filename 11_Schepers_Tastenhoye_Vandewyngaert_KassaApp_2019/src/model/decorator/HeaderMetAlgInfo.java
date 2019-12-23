package model.decorator;

import domain.Artikel;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;
//@author Phloy

public class HeaderMetAlgInfo extends KassabonDecorator {
    KassabonAbstract kassabon;
    String text;

    public HeaderMetAlgInfo(KassabonAbstract kassabon, String text) {
        this.kassabon = kassabon;
        this.text = text;
    }

    public HeaderMetAlgInfo(KassabonAbstract kassabon) {
        this.kassabon = kassabon;
        this.text = getText();
    }

    @Override
    public String getText() {
        //hier moet je naar de enum gaan
        return "Wat hebben we vandaag nou weer gekocht?: \n";
    }

    @Override
    public String print(List<Artikel> artList) throws BiffException, IOException {
        return this.text + "\n" +  kassabon.print(artList);
    }
}
