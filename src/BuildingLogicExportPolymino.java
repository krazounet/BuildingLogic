public class BuildingLogicExportPolymino {
    public static void main(String[] args){
        for(Piece piece : Probleme.getListPiece()){
            for(Polymino polymino: piece.polyminoList){
//                DrawTools.saveFile( polymino.export(),ConfigPartie.rep_export+"Polymino/"+polymino.typePolymino+"-"+polymino.orientation+"-"+polymino.recto+".png");
                DrawTools.saveFile( polymino.exportGraphical(),ConfigPartie.rep_export+"Polymino/G_"+polymino.typePolymino+"-"+polymino.orientation+"-"+polymino.recto+".png");
            }
        }
    }
}
