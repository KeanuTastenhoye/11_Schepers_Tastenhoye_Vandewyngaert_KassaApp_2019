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
        //File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\kortingStrategieProperties");
        File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\kortingStrategieProperties");

        HashMap<KortingEnum, ArrayList<String>> kortingen = new HashMap<>();
        String inhoud;
        //File file = new File("D:\\UCLL\\FASE_2\\OO Ontwerpen\\untitled\\src\\bestanden\\kortingStrategieProperties");

        Scanner scannerFile = new Scanner(file);        // scanner voor File
        while (scannerFile.hasNextLine()) {                // voor elke lijn van het bestand
            Scanner scannerLijn = new Scanner(scannerFile.nextLine());    // scanner voor lijn
            scannerLijn.useDelimiter(", ");
            //System.out.println(scannerLijn.next());// scheidingstekens van verschillende delen in de huidige lijn
            String categorie = scannerLijn.next().toUpperCase();// eerste deel huidige lijn tot aan /
            System.out.println("categorie "+categorie);
            String subcategorie = scannerLijn.next();                // tweede deel huidige lijn tot aan /
            System.out.println(subcategorie);
            String percentage = scannerLijn.next();                // tweede deel huidige lijn tot aan /
            //percentage=percentage.substring(0,percentage.length()-1);
            System.out.println(percentage);
            if (!kortingen.containsKey(KortingEnum.valueOf(categorie))||kortingen.get(KortingEnum.valueOf(categorie)).isEmpty()) {
                kortingen.put(KortingEnum.valueOf(categorie),new ArrayList<>());
            }
            kortingen.put(KortingEnum.valueOf(categorie), kortingen.get(KortingEnum.valueOf(categorie)));

            kortingen.get(KortingEnum.valueOf(categorie)).add(subcategorie);
            kortingen.get(KortingEnum.valueOf(categorie)).add(percentage);
        }
        for (KortingEnum e: kortingen.keySet()) {
            System.out.println(e.toString());
        }
        return kortingen;
    }
}