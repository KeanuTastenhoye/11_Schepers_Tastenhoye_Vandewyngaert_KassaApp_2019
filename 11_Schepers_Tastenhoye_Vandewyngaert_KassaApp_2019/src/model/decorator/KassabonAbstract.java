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
    public abstract String print(List<Artikel> artList) throws BiffException, IOException;
}
