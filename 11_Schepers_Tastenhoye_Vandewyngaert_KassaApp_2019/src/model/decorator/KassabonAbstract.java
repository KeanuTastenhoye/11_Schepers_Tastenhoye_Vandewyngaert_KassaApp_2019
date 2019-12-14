package model.decorator;

import domain.Artikel;
import java.util.List;

public abstract class KassabonAbstract {
    String text ="";

    public String getText(){
        return text;
    }
    public abstract String print(List<Artikel>artikels);
}
