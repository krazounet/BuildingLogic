import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Probleme {
    Plateau plateau;
    List <Polymino> Liste_polyminos_utilises;
    EnsembleIndices<Indice> ensembleIndices;

    public Probleme() {
        //generation plateau
        plateau = new Plateau();

        //init des Pieces qui seront placees
        Liste_polyminos_utilises =new ArrayList<>();

        //placement Pieces
        placementPieces();

        //Creation des indices
        ensembleIndices =new EnsembleIndices<>(plateau);

    }

    private void placementPieces() {
        //ici boucle pour essayer de placer des pieces
        int nb_essai=0;
        int max_essai=1000;
        List <Piece> pieceListdisponibles =  getListPiece();
        while (nb_essai<max_essai){
            //on selectionne aleatoirement un polymino
            if (pieceListdisponibles.size() == 0){break;}//si aucune piece est disponible coupons l algo.
            Piece piece = pieceListdisponibles.get(new Random().nextInt(pieceListdisponibles.size()));
            Polymino polymino = piece.polyminoList.get(new Random().nextInt(piece.polyminoList.size()));

            //on selectionne une case vide aleatoirement
            Case case_vide = plateau.getRandomCaseVide();
            if (case_vide == null){break;}//si le plateau est plein, plus la peine de chercher !

            if (plateau.IsPolyminoPlacable(case_vide,polymino)){
                plateau.placePolymino(case_vide,polymino);
                //Retire la Piece du polymino des choix disponibles.
                pieceListdisponibles.remove(piece);
                //on l'ajoute a la liste des pieces du probleme
                Liste_polyminos_utilises.add(polymino);
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
        //export de la liste des pieces utilisee
        //export des indices et du tableau vierge
        //export de la solution
    }
}
