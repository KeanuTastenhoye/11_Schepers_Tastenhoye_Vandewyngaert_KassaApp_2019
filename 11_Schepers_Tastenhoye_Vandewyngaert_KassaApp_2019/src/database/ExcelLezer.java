package database;

import domain.Artikel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class ExcelLezer implements LoadSave {
    private static final ExcelLezer SINGLE_INSTANCE = new ExcelLezer();
    private ExcelPlugin excel;
    private LoadSaveFactory fact;
    private ArrayList<ArrayList<String>> artikels= new ArrayList<>();


    private ExcelLezer() {
        this.excel = new ExcelPlugin();
        this.fact = new LoadSaveFactory();
    }

    public static ExcelLezer getInstance() {
        return SINGLE_INSTANCE;
    }

    //Stuurt naar de ExcelPugin om de excel te lezen
    @Override
    public ArrayList<Artikel> load(File file) throws BiffException, IOException {
            ArrayList<ArrayList<String>> reader= excel.read(file);
            HashMap<String ,Artikel> map = new HashMap();
            ArrayList<Artikel> artikels=new ArrayList<>();

            for (int i=0; i < reader.size(); i++) {
                Artikel a = new Artikel(reader.get(i).get(0),
                                        reader.get(i).get(1),
                                        reader.get(i).get(2),
                                        reader.get(i).get(3),
                                        reader.get(i).get(4));
                map.put(a.getArtikelNr(), a);
            }

            ArtikelDbInMemory artikelDB = ArtikelDbInMemory.getInstance();
            artikelDB.setArtikels(map);

            for(Artikel a: map.values()) {
                artikels.add(a);
            }

            return artikels;
        }

    //Stuurt naar de ExcelPlugin om naar de excel te schrijven
    @Override
    public void save(ArrayList<ArrayList<String>> artikels, File file) throws BiffException, IOException, RowsExceededException, WriteException {
        file = fact.getCorrectFile();
        excel.write(file, artikels);
    }
}