import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Probleme {
    Plateau plateau;
    List <Piece> pieceList;
    EnsembleIndices<Indice> ensembleIndices;

    public Probleme() {
        //generation plateau
        plateau = new Plateau();

        //choix Piece
        pieceList =getListPiece();

        //placement Pieces
        placementPieces();

        //Creation des indices
        ensembleIndices =new EnsembleIndices<>(plateau);
    }

    private void placementPieces() {
        //ici boucle pour essayer de placer des pieces
        int nb_essai=0;
        int max_essai=1000;
        while (nb_essai<max_essai){
            //on selectionne aleatoirement un polymino
            if (pieceList.size() == 0){break;}//si aucune piece est disponible coupons l algo.
            Piece piece = pieceList.get(new Random().nextInt(pieceList.size()));
            Polymino polymino = piece.polyminoList.get(new Random().nextInt(piece.polyminoList.size()));

            //on selectionne une case vide aleatoirement
            Case case_vide = plateau.getRandomCaseVide();
            if (case_vide == null){break;}//si le plateau est plein, plus la peine de chercher !

            if (plateau.IsPolyminoPlacable(case_vide,polymino)){
                plateau.placePolymino(case_vide,polymino);
                //Retire la Piece du polymino des choix.
                pieceList.remove(piece);
                //reinitilise le test
                nb_essai=0;
            }
            nb_essai++;
        }
    }

    private List<Piece> getListPiece() {
        //devra etre customise si on veut une sélection intelligente
        List <Piece> list_a_retourner=new ArrayList<>();
        list_a_retourner.add(Piece.L);
        list_a_retourner.add(Piece.O);
        return list_a_retourner;
    }

    public void export() {
    }
}
