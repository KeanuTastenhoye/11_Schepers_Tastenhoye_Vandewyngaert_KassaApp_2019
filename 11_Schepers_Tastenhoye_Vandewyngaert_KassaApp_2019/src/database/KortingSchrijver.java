package database;

import domain.KortingEnum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

//hier wordt de korting keuze van de kassierster bijgehouden
public class KortingSchrijver {
    private Properties property;
    private KortingEnum kortingEnum;

    public KortingSchrijver() {
        property = new Properties();}

    public void write(KortingEnum categorie, String percentage) {
        try {
            kortingEnum = categorie;
            property.setProperty("Categorie", kortingEnum.getOmschrijving());
            property.setProperty("Percentage", percentage);
            //File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\opslagStrategieProperties");
            File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\opslagStrategieProperties");
            OutputStream out = new FileOutputStream(file);
            property.store(out, "Properties file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(KortingEnum categorie, String subCategorie, String percentage) {
        try {
            kortingEnum = categorie;
            property.setProperty("Categorie", kortingEnum.getOmschrijving());
            property.setProperty("SubCategorie", subCategorie);
            property.setProperty("Percentage", percentage);
            //File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\kortingStrategieProperties");
            File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\kortingStrategieProperties");
            OutputStream out = new FileOutputStream(file);
            property.store(out, "Properties file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}