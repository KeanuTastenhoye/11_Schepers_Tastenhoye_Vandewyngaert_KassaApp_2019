package database;

import domain.Artikel;
import domain.KortingEnum;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class KortingLezer {

    public HashMap<KortingEnum, ArrayList<String>> load() throws BiffException, IOException {

        HashMap<KortingEnum, ArrayList<String>> kortingen = new HashMap<>();
        String inhoud;
        File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\kortingStrategieProperties");

        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((inhoud = reader.readLine()) != null) {
            //{} haakjes weg doen
            String nieuweInhoud = inhoud.substring(1,50);
            //Alles apart bijhouden
            String[] parts = nieuweInhoud.split(",");
            //Alles toekennen
            String categorie = parts[1].substring(11).toUpperCase();
            String subCategorie = parts[0].substring(13).toUpperCase();
            String percentage = parts[2].substring(12).toUpperCase();


            if(!kortingen.containsKey(KortingEnum.valueOf(categorie)) || kortingen.get(KortingEnum.valueOf(categorie)).isEmpty()) {
                kortingen.put(KortingEnum.valueOf(categorie),new ArrayList<>());
            }

            kortingen.put(KortingEnum.valueOf(categorie), kortingen.get(KortingEnum.valueOf(categorie)));
            kortingen.get(KortingEnum.valueOf(categorie)).add(subCategorie);
            kortingen.get(KortingEnum.valueOf(categorie)).add(percentage);
        }
        return kortingen;
    }
}