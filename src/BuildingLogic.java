import java.util.Arrays;

import Enum.ContrainteLevel;

public class BuildingLogic {
    public static void main(String[] args){
        Probleme probleme =new Probleme(Arrays.asList());
//        probleme.export("");
        probleme.exportGraphical("G_");
        Solveur solveur = new Solveur(probleme);
        solveur.analyse();
    }
}
