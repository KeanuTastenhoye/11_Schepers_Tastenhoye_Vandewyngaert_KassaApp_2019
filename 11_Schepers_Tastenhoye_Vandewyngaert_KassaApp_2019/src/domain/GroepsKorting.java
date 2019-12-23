package domain;

import javafx.scene.Group;
//@author Eline
public class GroepsKorting implements KortingStrategy {

    private int korting;
    private String groep;

    public GroepsKorting(int korting,String groep)
    {
        this.groep=groep;
        this.korting=korting;
    }

    @Override
    public int getKorting() {

        return korting;
    }
}
