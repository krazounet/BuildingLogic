import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Enum.*;

public class Probleme {
    Plateau plateau;
    List <Polymino> Liste_polyminos_utilises;
    EnsembleIndices ensembleIndices;

    public Probleme() {
        //generation plateau
        plateau = new Plateau();

        //init des Pieces qui seront placees
        Liste_polyminos_utilises =new ArrayList<>();

        //placement Pieces
        placementPieces();

        //Creation des indices
        ensembleIndices =new EnsembleIndices(plateau);

    }

    private void placementPieces() {
        //ici boucle pour essayer de placer des pieces
        int nb_essai=0;
        List <Piece> pieceListdisponibles =  getListPiece();
        while (nb_essai<ConfigPartie.nb_essai_max){
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

    public static List<Piece> getListPiece() {
        //chaque polymino est ajoute autant de fois que nécessaire.
        List <Piece> list_a_retourner=new ArrayList<>();
        for (TypePolymino tp : TypePolymino.values()){
            for (int nb_fois=0; nb_fois<ConfigPartie.nb_chaque_piece;nb_fois++){
                list_a_retourner.add(new Piece(tp));
            }

        }


        return list_a_retourner;
    }

    public void export(String numero) {
        //creation de l'identifiant
        String ident = numero+" - "+ConfigPartie.largeur_plateau+"&"+ConfigPartie.hauteur_plateau+"@";
        for(Polymino poly : Liste_polyminos_utilises){
            ident=ident+" "+poly.getIdent();
        }
        //fond de l'image
        BufferedImage fond = DrawTools.getImage(ConfigPartie.img_fond);
        //export de la liste des pieces utilisee
        int x_poly = 150;
        int y_poly = 0;//tous les 3 polys on va à la ligne
        int nb_pol = 0;
        for(Polymino poly : Liste_polyminos_utilises){
            if (nb_pol %3 == 0){y_poly=y_poly+150;x_poly=150;}
            BufferedImage img_poly=DrawTools.getImage(ConfigPartie.rep_image_polymino+poly.typePolymino+".png");
            DrawTools.drawImageTransformed(fond,img_poly,x_poly,y_poly,0,100);
            x_poly=x_poly+150;
            nb_pol++;
        }
        //export des indices et du tableau vierge
        BufferedImage img_probleme=ensembleIndices.export();
        BufferedImage img_zoom = DrawTools.Zoom(img_probleme,200);
        DrawTools.drawImageTransformed(fond,img_zoom,850,850,0,100);
        //watermark
        DrawTools.drawText(fond,ident, 850, 1720,"Arial", Color.BLACK, 20,0);
        DrawTools.saveFile(fond,ConfigPartie.rep_export+ident+"_PBM.png");
        //export de la solution
        BufferedImage fond_sol = DrawTools.getImage(ConfigPartie.img_fond);
        BufferedImage img_tab = plateau.export();
        BufferedImage tab_zoom = DrawTools.Zoom(img_tab,200);
        DrawTools.drawImageTransformed(fond_sol,tab_zoom,850,850,0,100);
        DrawTools.drawText(fond_sol,ident, 850, 1720,"Arial", Color.BLACK, 20,0);
        DrawTools.saveFile(fond_sol,ConfigPartie.rep_export+ident+"_SOL.png");
    }
}
