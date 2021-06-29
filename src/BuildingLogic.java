import java.util.Arrays;

import Enum.ContrainteLevel;

public class BuildingLogic {
    public static void main(String[] args){
        Probleme probleme =new Probleme(Arrays.asList(ContrainteLevel.NB_PIECES_5));
//        probleme.export("");
        probleme.exportGraphical("G_");
        Solveur solveur = new Solveur(probleme);
        int nbSolutions = solveur.analyse(true);
    	
    	System.out.println("Nombre de solutions : " + nbSolutions);
    }
}
