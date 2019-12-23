package database;

import domain.Artikel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//@author Keanu

public abstract class TekstLoadSaveTemplate {

    final void bestandOpties(File f, ArrayList<ArrayList<String>> a) throws BiffException, IOException, RowsExceededException, WriteException {
        load(f);

        save(a,f);
    }

    abstract ArrayList<Artikel> load(File file) throws BiffException, IOException;
    abstract void save( ArrayList<ArrayList<String>> args,File file) throws BiffException, IOException, RowsExceededException, WriteException;


}
