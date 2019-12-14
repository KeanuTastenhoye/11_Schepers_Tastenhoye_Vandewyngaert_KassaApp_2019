package model.decorator;

import domain.Artikel;

import java.util.List;

public abstract class KassabonDecorator extends KassabonAbstract {

    public abstract String getText();
    public abstract String print(List<Artikel> artikels);
}
