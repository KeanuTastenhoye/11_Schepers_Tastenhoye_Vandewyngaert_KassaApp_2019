package database;

import domain.Artikel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.*;
import java.util.*;
//@author Phloy,Eline

public class TekstLezer extends TekstLoadSaveTemplate implements LoadSave {

    private static final TekstLezer SINGLE_INSTANCE = new TekstLezer();
    private ArrayList<Artikel> artikels;
    private LoadSaveFactory fact;

    private TekstLezer() {
        this.artikels = new ArrayList<>();
        this.fact = new LoadSaveFactory();
    }

    public static TekstLezer getInstance() {
        return SINGLE_INSTANCE;
    }

    //Methode om de txt te lezen
    @Override
    public ArrayList<Artikel> load(File file) throws BiffException, IOException {
        String inhoud;
        file = fact.getCorrectFile();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((inhoud = reader.readLine()) != null) {
            String[] parts = inhoud.split(",");
            if (parts.length >= 4) {
                String artikelNr = parts[0];
                String artikelNaam = parts[1];
                String artikelGroep = parts[2];
                String artikelPrijs = parts[3];
                String artikelVoorraad = parts[4];

                Artikel artikel = new Artikel();

                artikel.setArtikelNr(artikelNr);
                artikel.setArtikelNaam(artikelNaam);
                artikel.setArtikelGroep(artikelGroep);
                artikel.setArtikelPrijs(artikelPrijs);
                artikel.setArtikelVoorraad(artikelVoorraad);

                artikels.add(artikel);
            } else {
                System.out.println("Ignoring line: " + inhoud);
            }
        }
        reader.close();
        return artikels;
    }

    //Methode om naar de txt te schrijven
    @Override
    public void save(ArrayList<ArrayList<String>> args, File file) throws BiffException, IOException, RowsExceededException, WriteException {

        file = fact.getCorrectFile();
        PrintWriter writer = new PrintWriter(file, "UTF-8");

        for (ArrayList<String> a : args) {
            writer.println(a);
        }
        writer.close();
    }
}
