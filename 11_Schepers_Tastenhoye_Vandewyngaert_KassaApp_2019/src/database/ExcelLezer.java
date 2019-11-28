package database;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelLezer implements LoadSave {

    private static final ExcelLezer SINGLE_INSTANCE = new ExcelLezer();
    private ExcelPlugin excel;
    private LoadSaveFactory fact;

    private ExcelLezer() {
        this.excel = new ExcelPlugin();
        this.fact = new LoadSaveFactory();
    }

    public static ExcelLezer getInstance() {
        return SINGLE_INSTANCE;
    }

    //Stuurt naar de ExcelPugin om de excel te lezen
    @Override
    public ArrayList<ArrayList<String>> load(File file) throws BiffException, IOException {
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
        file = fact.getCorrectFile();

        info = excel.read(file);

        return info;
    }

    //Stuurt naar de ExcelPlugin om naar de excel te schrijven
    @Override
    public void save(File file, ArrayList<ArrayList<String>> args) throws BiffException, IOException, RowsExceededException, WriteException {
        file = fact.getCorrectFile();
        excel.write(file, args);
    }
}