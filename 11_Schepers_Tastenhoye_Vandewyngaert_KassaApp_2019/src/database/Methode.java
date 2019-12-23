package database;
//@author Keanu,Eline

public enum Methode {
    TEXT("Tekst", "database.TekstLezer"),
    EXCEL("Excel", "database.ExcelPugin");

    private final String naam;
    private final String klasseNaam;

    Methode(String naam, String klasseNaam) {
        this.naam = naam;
        this.klasseNaam = klasseNaam;
    }

    public String getNaam() { return naam; }
    public String getKlasseNaam() { return klasseNaam; }
}