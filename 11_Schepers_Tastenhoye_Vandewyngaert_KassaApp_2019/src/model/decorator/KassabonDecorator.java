package model.decorator;

import domain.Artikel;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;
//@author Phloy

public abstract class KassabonDecorator extends KassabonAbstract {

    public abstract String getText();
    public abstract String print(List<Artikel> artList) throws BiffException, IOException;
}
