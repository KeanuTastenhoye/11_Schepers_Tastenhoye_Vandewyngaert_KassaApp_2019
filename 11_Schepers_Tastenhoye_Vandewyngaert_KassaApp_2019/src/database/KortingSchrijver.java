package database;

import domain.KortingEnum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

//hier wordt de korting keuze van de kassierster bijgehouden
public class KortingSchrijver {
    private Properties property;
    private KortingEnum kortingEnum;
    private ArrayList<String> kortingen=new ArrayList<>();

    public KortingSchrijver() {
        property = new Properties();}

    public void write(KortingEnum categorie, String percentage) {
        try {
            kortingEnum = categorie;
            property.setProperty("Categorie", kortingEnum.getOmschrijving());
            property.setProperty("Percentage", percentage);
            //File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\kortingStrategieProperties");
            File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\kortingStrategieProperties");
            OutputStream out = new FileOutputStream(file);
            property.store(out, "Properties file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(KortingEnum categorie, String subCategorie, String percentage) {
        //try {
        kortingEnum = categorie;
        property.setProperty("Categorie", kortingEnum.getOmschrijving());
        property.setProperty("SubCategorie", subCategorie);
        property.setProperty("Percentage", percentage);
        kortingen.add(property.toString());
        //File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\kortingStrategieProperties");
        File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019");
        try {
            PrintWriter writer = new PrintWriter(file);
            for(String s: kortingen)
            {
                writer.println(s);
                System.out.println(s);

            }
            writer.close();
            System.out.println(kortingen.size());
            //OutputStream out = new FileOutputStream(file);
            //property.store(out, "Properties file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}