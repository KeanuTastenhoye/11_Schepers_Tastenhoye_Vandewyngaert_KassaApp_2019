package domain;

import java.util.ArrayList;
import java.util.List;

public class KortingContext {

    private KortingStrategy kortingStrategy;

    public KortingContext(){

    }

    public void setTaalInterface(KortingStrategy kortingStrategy){
        this.kortingStrategy = kortingStrategy;
    }

    public int getAanspreking(){
        return kortingStrategy.getKorting();
    }

    public List<String> getKortingLijst(){
        List <String> kortinglijst = new ArrayList<String>();
        for (KortingEnum taal:KortingEnum.values()){
            kortinglijst.add(taal.toString());
        }
        return kortinglijst;
    }
}
