package database;

import domain.Artikel;
import domain.DomainException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//@author Keanu

public interface ArtikelDbStrategy {
    ArrayList<Artikel> load(File file) throws IOException, BiffException;
    void save(ArrayList<Artikel> artikels, File file) throws DomainException, WriteException, IOException, BiffException;
}
