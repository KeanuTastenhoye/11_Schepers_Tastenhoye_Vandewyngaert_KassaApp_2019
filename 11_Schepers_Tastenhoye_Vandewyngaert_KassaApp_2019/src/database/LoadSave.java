package database;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface LoadSave {

    ArrayList<ArrayList<String>> load(File file) throws BiffException, IOException;

    void save(File file, ArrayList<ArrayList<String>> args) throws BiffException, IOException, RowsExceededException, WriteException;
}