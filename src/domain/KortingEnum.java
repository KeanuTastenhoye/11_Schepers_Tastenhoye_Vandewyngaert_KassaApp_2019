package domain;

public enum KortingEnum {

    GROEP("groep","domain.GroepsKorting"),
    DREMPEL("drempel","domain.DrempelKorting"),
    DUURSTE("duurste","domain.DuursteKorting");

    private final String omschrijving;
    private final String klassenaam;

    KortingEnum(String omschrijving,String klassenaam)
    {
        this.omschrijving=omschrijving;
        this.klassenaam=klassenaam;
    }
    public String getOmschrijving() { return omschrijving; }
    public String getKlasseNaam() { return klassenaam; }
}
