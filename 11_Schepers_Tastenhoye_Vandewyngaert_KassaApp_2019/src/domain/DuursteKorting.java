package domain;

import database.ArtikelDbInMemory;
/**
 * //@author Eline
 */
public class DuursteKorting implements KortingStrategy {

    private int korting;
    private Artikel artikel;

    public DuursteKorting(int korting)
    {
        artikel=ArtikelDbInMemory.getInstance().getDuurste();
        this.korting=korting;
    }

    @Override
    public int getKorting() {

        return korting;
    }
}
