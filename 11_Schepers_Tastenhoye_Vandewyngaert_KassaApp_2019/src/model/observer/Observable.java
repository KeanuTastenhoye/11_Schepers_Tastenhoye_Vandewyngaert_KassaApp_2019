package model.observer;

import jxl.read.biff.BiffException;

import java.io.IOException;

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers() throws IOException, BiffException;
}
