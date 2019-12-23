package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

public class DecoratorWriter {
    private Properties property;

    public DecoratorWriter() {
        property = new Properties();}

    public void write(List<String> keuze, String input) {
        try {
            property.setProperty("Keuze", keuze.toString());
            property.setProperty("Input", input);
            File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\DecoratorKassabonProperties");
            OutputStream out = new FileOutputStream(file);
            property.store(out, "Properties file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void write(List<String> keuze) {
        try {
            property.setProperty("Keuze", keuze.toString());
            File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\DecoratorKassabonProperties");
            OutputStream out = new FileOutputStream(file);
            property.store(out, "Properties file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
