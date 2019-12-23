package domain;

import java.util.ArrayList;
import java.util.List;
/**
 * //@author Eline
 */
public class KortingContext {

    private KortingStrategy kortingStrategy;

    public KortingContext(){

    }

    public void setKortingInterface(KortingStrategy kortingStrategy){
        this.kortingStrategy = kortingStrategy;
    }

    public int getKorting(){
        return kortingStrategy.getKorting();
    }

    public List<String> getKortingLijst(){
        List <String> kortinglijst = new ArrayList<String>();
        for (KortingEnum korting:KortingEnum.values()){
            kortinglijst.add(korting.toString());
        }
        return kortinglijst;
    }
}
