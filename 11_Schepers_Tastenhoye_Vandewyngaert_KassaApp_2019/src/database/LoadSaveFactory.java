package database;

import java.io.File;
//@author Phloy,Keanu

public class LoadSaveFactory {

    private Service s = new Service();
    Methode opslagMethode;
    //Maakt de correcte lezer afhankelijk van de strategie
    public LoadSave maakLoadSaveStrategie(String strategie) {
        //LoadSave ls;
        if (strategie.equals("Tekst")) {
            setOpslagMethode(Methode.TEXT);
            return TekstLezer.getInstance();
            //ls = new TekstLezer();
        } else if (strategie.equals("Excel")) {
            setOpslagMethode(Methode.EXCEL);
            return ExcelLezer.getInstance();
            //ls = new ExcelLezer();
        } else {
            throw new IllegalArgumentException("Volgende strategie is niet geimplementeerd in deze app.: " + strategie);
        }
        //return ls;
    }

    //Zorgt dat de lezer de correcte file gebruikt
    public File getCorrectFile() {
        File file;

        if (s.getStrategy().equals("Tekst")) {
            file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\artikel.txt");
        } else if (s.getStrategy().equals("Excel")) {
            file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\artikel.xls");
        } else {
            throw new IllegalArgumentException("Niet geimplementeerde strategie");
        }

        return file;
    }

    public void setOpslagMethode(Methode opslagMethode) {
        this.opslagMethode = opslagMethode;
    }
}