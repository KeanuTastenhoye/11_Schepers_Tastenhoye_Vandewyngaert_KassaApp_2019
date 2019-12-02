package database;

import domain.Artikel;
import domain.DomainException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.WriteAbortedException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArtikelDbContext {

    private ArtikelDbStrategy strategy;

    public ArtikelDbContext(ArtikelDbStrategy strategy) {
        this.strategy = strategy;
    }

    public void saveProducts(ArrayList<Artikel> artikels) throws DomainException {

        strategy.save(artikels);

    }

    public ArrayList<Artikel> loadProducts() throws DomainException, FileNotFoundException {

        return strategy.load();
    }


}
