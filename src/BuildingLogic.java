import java.util.Arrays;

import Enum.ContrainteLevel;

public class BuildingLogic {
    public static void main(String[] args){
        Probleme probleme =new Probleme(Arrays.asList(ContrainteLevel.PIECE_PRESENTE_I4, ContrainteLevel.PIECE_PRESENTE_T, ContrainteLevel.PIECE_PRESENTE_O, ContrainteLevel.PIECE_PRESENTE_L, ContrainteLevel.PIECE_PRESENTE_Z, ContrainteLevel.PIECE_PRESENTE_J, ContrainteLevel.PIECE_PRESENTE_S, ContrainteLevel.PIECE_PRESENTE_V, ContrainteLevel.NB_PIECES_PRESENTE_8));
//        probleme.export("");
        probleme.exportGraphical("G_");
        Solveur solveur = new Solveur(probleme);
        int nbSolutions = solveur.analyse(true);
    	
    	System.out.println("Nombre de solutions : " + nbSolutions);
    }
}
