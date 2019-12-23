package domain;
/**
 * //@author Eline
 */
public class DrempelKorting implements KortingStrategy {

    private int kortingEUR,drempel;

    public DrempelKorting(int kortingEUR,int drempel)
    {
        this.kortingEUR=kortingEUR;
        this.drempel=drempel;
    }


    @Override
    public int getKorting() {
        return kortingEUR;
    }
}
