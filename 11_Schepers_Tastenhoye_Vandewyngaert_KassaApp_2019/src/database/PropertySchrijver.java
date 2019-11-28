package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertySchrijver {
    private Properties property;

    public PropertySchrijver() {
        property = new Properties();}

    public void write(String manier) {
        try {
            property.setProperty("Opslagstrategie", manier);
            File file = new File("11_Schepers_Tastenhoye_Vandewyngaert_KassaApp_2019\\src\\bestanden\\opslagStrategieProperties");
            OutputStream out = new FileOutputStream(file);
            property.store(out, "Properties file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}