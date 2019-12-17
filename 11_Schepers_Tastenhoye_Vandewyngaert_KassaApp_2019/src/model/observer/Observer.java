package model.observer;

import domain.Artikel;
import javafx.collections.ObservableList;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;

public interface Observer {
    void update(ObservableList<String> klantlist) throws IOException, BiffException;
}

