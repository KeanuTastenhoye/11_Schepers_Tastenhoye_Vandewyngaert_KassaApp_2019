package database;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class TekstLoadSaveTemplate {

    final void bestandOpties(File f, ArrayList<ArrayList<String>> a) throws BiffException, IOException, RowsExceededException, WriteException {
        load(f);
        save(f, a);
    }

    abstract ArrayList<ArrayList<String>> load(File file) throws BiffException, IOException;
    abstract void save(File file, ArrayList<ArrayList<String>> args) throws BiffException, IOException, RowsExceededException, WriteException;
}
