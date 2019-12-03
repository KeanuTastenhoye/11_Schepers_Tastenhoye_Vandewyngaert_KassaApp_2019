package database;

import domain.Artikel;
import domain.DomainException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface ArtikelDbStrategy {
    ArrayList<Artikel> load(String file) throws IOException, BiffException;
    void save(ArrayList<Artikel> artikels,String file) throws DomainException, WriteException, IOException, BiffException;
}
