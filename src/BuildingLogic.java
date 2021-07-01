import java.util.Arrays;

import Enum.ContrainteLevel;

public class BuildingLogic {
    public static void main(String[] args){
        Probleme probleme =new Probleme(Arrays.asList(ContrainteLevel.PIECE_PRESENTE_I4, ContrainteLevel.PIECE_PRESENTE_O, ContrainteLevel.PIECE_PRESENTE_T, ContrainteLevel.PIECE_PRESENTE_L, ContrainteLevel.NB_PIECES_PRESENTE_4));
//        probleme.export("");
        Solveur solveur = new Solveur(probleme);
        int nbSolutions = solveur.analyse(true);
        probleme.exportGraphical(nbSolutions + "-G_");
    	System.out.println("Nombre de solutions : " + nbSolutions);
    }
}
