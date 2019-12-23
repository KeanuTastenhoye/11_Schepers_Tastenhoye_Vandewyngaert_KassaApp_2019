package database;

import domain.Artikel;
import domain.DomainException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
//@author Phloy

public class ArtikelDbContext {

    private ArtikelDbStrategy strategy;

    public ArtikelDbContext(ArtikelDbStrategy strategy) {
        this.strategy = strategy;
    }

    public void saveProducts(ArrayList<Artikel> artikels, File file) throws DomainException, BiffException, IOException, WriteException {
        strategy.save(artikels,file);
    }

    public ArrayList<Artikel> loadProducts(File file) throws DomainException, IOException, BiffException {
        return strategy.load(file);
    }
}
