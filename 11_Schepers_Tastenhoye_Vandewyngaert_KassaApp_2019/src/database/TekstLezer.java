package database;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.*;
import java.util.*;

public class TekstLezer extends TekstLoadSaveTemplate implements LoadSave {

    private static final TekstLezer SINGLE_INSTANCE = new TekstLezer();
    private ArrayList<ArrayList<String>> artikels;
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
    public ArrayList<ArrayList<String>> load(File file) throws BiffException, IOException {
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

                ArrayList<String> artikel = new ArrayList<>();

                artikel.add(artikelNr);
                artikel.add(artikelNaam);
                artikel.add(artikelGroep);
                artikel.add(artikelPrijs);
                artikel.add(artikelVoorraad);

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
    public void save(File file, ArrayList<ArrayList<String>> args) throws BiffException, IOException, RowsExceededException, WriteException {
        file = fact.getCorrectFile();
        PrintWriter writer = new PrintWriter(file, "UTF-8");

        for (ArrayList<String> a: args) {
            writer.println(a);
        }
        writer.close();
    }
}