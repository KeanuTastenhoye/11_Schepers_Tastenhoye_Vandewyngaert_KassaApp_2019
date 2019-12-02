package database;

import database.ArtikelDbStrategy;
import database.LoadSave;
import domain.Artikel;
import domain.DomainException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ArtikelDbInMemory implements ArtikelDbStrategy {
    private LoadSave strategy;
    private HashMap<String,Artikel> artikels;

    public void leesIn() throws FileNotFoundException {



    }

    @Override
    public ArrayList<Artikel> load(String file) throws IOException, BiffException {
        /*File file = new File("artikel.txt");
        ArrayList<Artikel> artikels = new ArrayList();
        Scanner scannerFile = new Scanner(file);  		// scanner voor File
        while (scannerFile.hasNextLine()) {  				// voor elke lijn van het bestand
            Scanner scannerLijn = new Scanner(scannerFile.nextLine());  	// scanner voor lijn
            scannerLijn.useDelimiter(" ");  				// scheidingstekens van verschillende delen in de huidige lijn
            String artikelcode = scannerLijn.next(); 			// eerste deel huidige lijn tot aan /
            String naam = scannerLijn.next();// tweede deel huidige lijn tot aan /
            String groep=scannerLijn.next();
            String prijs=scannerLijn.next();
            String voorraad=scannerLijn.next();
            Artikel artikel = new Artikel(artikelcode, naam,groep,prijs,voorraad);
            artikels.add(artikel);
        }
        for(Artikel artikel : artikels)
        {
            System.out.println(artikel.getArtikelNaam());
        }
        return artikels;*/
        return strategy.load(file);
    }

    @Override
    public void save(ArrayList<Artikel> artikels,String file) throws DomainException, WriteException, IOException, BiffException {

        /*File file = new File("artikel.txt");
        try {
            PrintWriter writer = new PrintWriter(file);
            for(Artikel artikel:artikels)
            {
                writer.println(artikel.toString());
            }
            writer.close();
        }  catch (FileNotFoundException ex) {
            throw new DomainException("Fout bij het wegschrijven", ex);
        }*/
        strategy.save(artikels,file);

    }

    public Artikel findArtikel(String id)
    {
        return null;
        //todo
    }
}
