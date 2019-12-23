package domain;

import database.LoadSave;
import database.Methode;

public class Factory {
    public static LoadSave maakMethode(String methodeNaam) {
        Methode methode = Methode.valueOf(methodeNaam);
        String klasseNaam = methode.getKlasseNaam();
        LoadSave loadSave = null;

        try {
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.newInstance();
            loadSave = (LoadSave) dbObject;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loadSave;
    }
}