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
        
        // y a t il une contrainte de nombre de pi�ce ?
        int nbPieces = ConfigPartie.listPolyminoToUse.size();
        for(ContrainteLevel contrainte : contraintesList)
        	if(contrainte.name().indexOf("NB_PIECES_PRESENTE_") != -1)
        		nbPieces = contrainte.value;

        //placement Pieces
        placementPieces(nbPieces);

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
    		case NB_PIECES_PRESENTE_1:
    		case NB_PIECES_PRESENTE_2:
    		case NB_PIECES_PRESENTE_3:
    		case NB_PIECES_PRESENTE_4:
    		case NB_PIECES_PRESENTE_5:
    		case NB_PIECES_PRESENTE_6:
            case NB_PIECES_PRESENTE_7:
            case NB_PIECES_PRESENTE_8:
            case NB_PIECES_PRESENTE_9:
            case NB_PIECES_PRESENTE_10:
    			if(Liste_polyminos_utilises.size() != contrainteLevel.value)
    				return(false);
    			break;
    		case NB_RECTO_0:
    		case NB_RECTO_1:
    		case NB_RECTO_2:
    		case NB_RECTO_3:
    		case NB_RECTO_4:
    		case NB_RECTO_5:
    		case NB_RECTO_6:
    		case NB_RECTO_7:
    		case NB_RECTO_8:
    		case NB_RECTO_9:
    		case NB_RECTO_10:
    			int nbRecto = 0;
    			for(Polymino polyminoToTest : Liste_polyminos_utilises)
    				if(polyminoToTest.recto)
    					nbRecto++;
    			if(nbRecto != contrainteLevel.value)
    				return(false);
    			break;
    		case PIECE_PRESENTE_I4:
    		case PIECE_PRESENTE_I3:
    		case PIECE_PRESENTE_I2:
    		case PIECE_PRESENTE_O:
    		case PIECE_PRESENTE_T:
    		case PIECE_PRESENTE_L:
    		case PIECE_PRESENTE_J:
    		case PIECE_PRESENTE_S:
    		case PIECE_PRESENTE_Z:
    		case PIECE_PRESENTE_V:
    			boolean pieceIci = false;
    			for(Polymino polyminoToTest : Liste_polyminos_utilises)
    				if(polyminoToTest.typePolymino == contrainteLevel.typePolymino)
    					pieceIci = true;
    			if(!pieceIci)
    				return(false);
    		}
    	}
    	
    	return(true);
    }

    private void placementPieces(int nbPieces) {
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
            if(Liste_polyminos_utilises.size() >= nbPieces)
            	return;
            nb_essai++;
        }
    }

    public static List<Piece> getListPiece() {
        //chaque polymino est ajoute autant de fois que nécessaire.
        List <Piece> list_a_retourner=new ArrayList<>();
        for (TypePolymino tp : ConfigPartie.listPolyminoToUse){
            for (int nb_fois=0; nb_fois<ConfigPartie.nb_chaque_piece;nb_fois++){
                list_a_retourner.add(new Piece(tp));
            }

        }


        return list_a_retourner;
    }

    public static List<Piece> getListPieceFromList(List <Polymino> Liste_polyminos) {
        //chaque polymino est ajoute autant de fois que nécessaire.
        List <Piece> list_a_retourner=new ArrayList<>();
        for(Polymino polymino : Liste_polyminos)
        {
            list_a_retourner.add(new Piece(polymino.typePolymino));
        }

        return list_a_retourner;
    }
    
    public List <Polymino> getListInOrder()
    {
    	List<Polymino> listInOrder = new ArrayList<>();
    	
    	for(TypePolymino typePolymino : TypePolymino.values())
    	{
    		for(Polymino polymino : Liste_polyminos_utilises)
    		{
    			if(polymino.typePolymino == typePolymino)
    			{
    				listInOrder.add(polymino);
    			}
    		}
    	}
    	
    	return(listInOrder);
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
        for(Polymino poly : getListInOrder()){
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
