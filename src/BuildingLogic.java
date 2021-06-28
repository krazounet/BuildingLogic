import java.util.Arrays;

import Enum.ContrainteLevel;

public class BuildingLogic {
    public static void main(String[] args){
        Probleme probleme =new Probleme(Arrays.asList(ContrainteLevel.NB_PIECES_3));
        probleme.export("");
        probleme.exportGraphical("G_");
    }
}
