package model.observer;

import domain.Artikel;
import javafx.collections.ObservableList;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(ObservableList<Artikel> klantlist) throws IOException, BiffException;
}
