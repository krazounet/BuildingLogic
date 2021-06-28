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

    public Probleme(List<ContrainteLevel> contraintesList) {
    	do
    	{
        //generation plateau
        plateau = new Plateau();

        //init des Pieces qui seront placees
        Liste_polyminos_utilises =new ArrayList<>();
        
        //placement Pieces
        placementPieces();

        //Creation des indices
        ensembleIndices =new EnsembleIndices(plateau, Liste_polyminos_utilises);
    	} while(!areAllContraintesOK(contraintesList));

    }
    
    private boolean areAllContraintesOK(List<ContrainteLevel> contraintesList)
    {
    	for(ContrainteLevel contrainteLevel : contraintesList)
    	{
    		switch(contrainteLevel)
    		{
    		case NB_PIECES_2:
    		case NB_PIECES_3:
    		case NB_PIECES_4:
    		case NB_PIECES_5:
    		    case NB_PIECES_6 :
    			if(Liste_polyminos_utilises.size() != contrainteLevel.value)
    				return(false);
    			break;
    		case NB_PIECES_RECTO_2:
    		case NB_PIECES_RECTO_3:
    		case NB_PIECES_RECTO_4:
    		case NB_PIECES_RECTO_5:
    			int nbRecto = 0;
    			for(Polymino polyminoToTest : Liste_polyminos_utilises)
    				if(polyminoToTest.recto)
    					nbRecto++;
    			if(nbRecto != contrainteLevel.value)
    				return(false);
    			break;
    		case PIECE_PRESENTE_I:
    		case PIECE_PRESENTE_O:
    		case PIECE_PRESENTE_T:
    		case PIECE_PRESENTE_L:
    		case PIECE_PRESENTE_S:
    			for(Polymino polyminoToTest : Liste_polyminos_utilises)
    				if(polyminoToTest.typePolymino == contrainteLevel.typePolymino)
    					return(true);
				return(false);
    		}
    	}
    	
    	return(true);
    }
/*
    private Object returnValueOfContrainte(ContrainteLevel contrainte)
    {
		switch(contrainte)
		{
		case NB_PIECES_2:
		case NB_PIECES_RECTO_2:
			return(2);
		case NB_PIECES_3:
		case NB_PIECES_RECTO_3:
			return(3);
		case NB_PIECES_4:
		case NB_PIECES_RECTO_4:
			return(4);
		case NB_PIECES_5:
		case NB_PIECES_RECTO_5:
			return(5);
            case NB_PIECES_6:
                return 6;
		case PIECE_PRESENTE_I:
			return(TypePolymino.I);
		case PIECE_PRESENTE_O:
			return(TypePolymino.O);
		case PIECE_PRESENTE_T:
			return(TypePolymino.T);
		case PIECE_PRESENTE_L:
			return(TypePolymino.L);
		case PIECE_PRESENTE_S:
			return(TypePolymino.S);
		}
		return(null);
    }
*/
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

    public void exportGraphical(String numero)
    {
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

            // Gestion des indices recto verso
            String stringPolyToDraw = "";

            for(IndicePiece indiceToCheck : ensembleIndices.indicePieceList)
            {
            	if(indiceToCheck.polymino == poly && indiceToCheck.typeIndicePiece == TypeIndicePiece.FACE_CONNU)
            	{
            		if(poly.recto)
            			stringPolyToDraw = "R";
            		else
            			stringPolyToDraw = "V";
            	}
            }
            
            BufferedImage img_poly=DrawTools.getImage(ConfigPartie.rep_image_polymino+poly.typePolymino+stringPolyToDraw+".png");
            
            // Gestion des 
            
            for(IndicePiece indiceToCheck : ensembleIndices.indicePieceList)
            {
            	if(indiceToCheck.polymino == poly && indiceToCheck.typeIndicePiece == TypeIndicePiece.ROTATION_CONNU)
            	{
            		BufferedImage img_poly_rotated =DrawTools.getImage(ConfigPartie.rep_image_polymino + "Rotation.png");
            		DrawTools.drawImageTransformed(img_poly_rotated, poly.exportGraphicalCentered(), img_poly.getWidth() / 2, img_poly.getHeight() / 2, 0, 25);
            		img_poly = img_poly_rotated;
            	}
            }
            
            DrawTools.drawImageTransformed(fond,DrawTools.Zoom(img_poly, 150),x_poly,y_poly,0,100);
            x_poly=x_poly+150;
            nb_pol++;
        }
        //export des indices et du tableau vierge
        BufferedImage img_probleme=ensembleIndices.exportGraphical();
        BufferedImage img_zoom = DrawTools.Zoom(img_probleme,200);
        DrawTools.drawImageTransformed(fond,img_zoom,850,850,0,100);
        //watermark
        DrawTools.drawText(fond,ident, 850, 1720,"Arial", Color.BLACK, 20,0);
        DrawTools.saveFile(fond,ConfigPartie.rep_export+ident+"_PBM.png");
        //export de la solution
        BufferedImage fond_sol = DrawTools.getImage(ConfigPartie.img_fond);
        BufferedImage img_tab = plateau.exportGraphical(Liste_polyminos_utilises);
        BufferedImage tab_zoom = DrawTools.Zoom(img_tab,200);
        DrawTools.drawImageTransformed(fond_sol,tab_zoom,850,850,0,100);
        DrawTools.drawText(fond_sol,ident, 850, 1720,"Arial", Color.BLACK, 20,0);
        DrawTools.saveFile(fond_sol,ConfigPartie.rep_export+ident+"_SOL.png");
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
