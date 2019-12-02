package database;

import domain.Artikel;
import domain.DomainException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface ArtikelDbStrategy {


    ArrayList<Artikel> load() throws IOException, BiffException;
    void save(ArrayList<Artikel> artikels) throws DomainException, WriteException, IOException, BiffException;

}
