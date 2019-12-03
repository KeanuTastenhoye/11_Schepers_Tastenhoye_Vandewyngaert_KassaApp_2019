package database;

import domain.Artikel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface LoadSave {

    ArrayList<Artikel> load(File file) throws BiffException, IOException;

    void save(ArrayList<ArrayList<String>> artikels, File file) throws BiffException, IOException, RowsExceededException, WriteException;
}