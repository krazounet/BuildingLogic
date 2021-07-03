import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Enum.*;

public class EnsembleIndices {
    List<IndicePicross> indicePicrossListVertical;
    List<IndicePicross> indicePicrossListHorizontal;
    List<IndiceCase> indiceCaseList;
    List<IndicePiece> indicePieceList;
    List<IndiceLigne> indiceLigneListVertical;
    List<IndiceLigne> indiceLigneListHorizontal;

    public EnsembleIndices(Plateau plateau, List <Polymino> liste_polyminos_utilises) {

        indicePicrossListVertical=new ArrayList<>();
        indicePicrossListHorizontal=new ArrayList<>();
        for (int abs=0; abs < ConfigPartie.largeur_plateau; abs++){
            indicePicrossListVertical.add(new IndicePicross(abs, TypeIndicePicross.COLONNE,new ArrayList<>()));
        }
        for (int ord=0; ord < ConfigPartie.hauteur_plateau; ord++){
            indicePicrossListHorizontal.add(new IndicePicross(ord, TypeIndicePicross.LIGNE,new ArrayList<>()));
        }

        //les indices sont generes a partir du plateau
        for (Case[] case_tab : plateau.tableau){
            for(Case case_tmp : case_tab){
                Tuile tuile_tmp=null;
                if (case_tmp instanceof Tuile){tuile_tmp=(Tuile)case_tmp;}
                if (tuile_tmp !=null){
                    indicePicrossListVertical.get(case_tmp.coordonnee.x).list_hauteurs.add(tuile_tmp.getHauteur());
                    indicePicrossListHorizontal.get(case_tmp.coordonnee.y).list_hauteurs.add(tuile_tmp.getHauteur());
                }
            }
        }
        
        //Création des indices sur les pièces
        indicePieceList=new ArrayList<>();
        List <Polymino> liste_polyminos_clone = new ArrayList<>();
        liste_polyminos_clone.addAll(liste_polyminos_utilises);
        Collections.shuffle(liste_polyminos_clone);
        for(int n = 0; n < ConfigPartie.nb_pieces_placees; n++)
        {
        	indicePieceList.add(new IndicePiece(liste_polyminos_clone.get(n % liste_polyminos_clone.size()), TypeIndicePiece.EMPLACEMENT_CONNU));
        }
        for(int n = ConfigPartie.nb_pieces_placees; n < ConfigPartie.nb_pieces_placees + ConfigPartie.nb_pieces_face_connue; n++)
        {
        	indicePieceList.add(new IndicePiece(liste_polyminos_clone.get(n % liste_polyminos_clone.size()), TypeIndicePiece.FACE_CONNU));
        }
        for(int n = ConfigPartie.nb_pieces_placees + ConfigPartie.nb_pieces_face_connue; n < ConfigPartie.nb_pieces_placees + ConfigPartie.nb_pieces_face_connue + ConfigPartie.nb_pieces_rotation_connue; n++)
        {
        	indicePieceList.add(new IndicePiece(liste_polyminos_clone.get(n % liste_polyminos_clone.size()), TypeIndicePiece.ROTATION_CONNU));
        }
        
        //Création des indices sur les lignes

        // Vertical (pour garder la correspondance avec les indices Picross vertical = Colone)
        indiceLigneListVertical = new ArrayList<>();
        List<Integer> listColonne = new ArrayList<>();
        for(int abs = 0; abs < ConfigPartie.largeur_plateau; abs++)
        {
        	listColonne.add(abs);
        }
        Collections.shuffle(listColonne);
        
        for(int n = 0; n < ConfigPartie.nb_lignes_picross_hidden_vert; n++)
        {
        	indiceLigneListVertical.add(new IndiceLigne(listColonne.get(n), TypeIndiceLigne.INDICES_PICROSS_HIDDEN));
        }

        // Horizontal
        indiceLigneListHorizontal = new ArrayList<>();
        List<Integer> listLigne = new ArrayList<>();
        for(int abs = 0; abs < ConfigPartie.hauteur_plateau; abs++)
        {
        	listLigne.add(abs);
        }
        Collections.shuffle(listLigne);
        
        for(int n = 0; n < ConfigPartie.nb_lignes_picross_hidden_horiz; n++)
        {
        	indiceLigneListHorizontal.add(new IndiceLigne(listLigne.get(n), TypeIndiceLigne.INDICES_PICROSS_HIDDEN));
        }
        
        // Création des indices sur les cases vides
        List<Case> listCasesVides = new ArrayList<>();
        listCasesVides.addAll(plateau.caseVideList);
        Collections.shuffle(listCasesVides);
        
        indiceCaseList = new ArrayList<>();
        if(ConfigPartie.nb_cases_vides > plateau.caseVideList.size())
		{
			System.out.println("Attention !!! Pas assez de cases vides !!!");
			ConfigPartie.nb_cases_vides = plateau.caseVideList.size();
		}
        for(int n = 0; n < ConfigPartie.nb_cases_vides; n++)
        {
        	indiceCaseList.add(new IndiceCase(listCasesVides.get(n).coordonnee, TypeIndiceCase.VIDE));
        }
    }

    //a retravailler dynamiquement
    public BufferedImage exportGraphical()
    {
    	int tailleIndice = 40;
    	int decalageIndice = 15;
    	int startXTableau = (ConfigPartie.largeur_plateau*ConfigPartie.taille_tuile/2)+75;
    	int startYTableau = (ConfigPartie.hauteur_plateau*ConfigPartie.taille_tuile/2)+75;
        //declaration de la taille de l'image.
        BufferedImage img_pbm = new BufferedImage((ConfigPartie.largeur_plateau*150)+100,(ConfigPartie.hauteur_plateau*150)+100,BufferedImage.TYPE_INT_ARGB);
        //Indicevretical
        int x_tmp=startXTableau;//calcul : espace pour les indices horizontal (largeur*50))
        int y_tmp;
        for(int idInd = 0; idInd < indicePicrossListVertical.size(); idInd++)
        {
        	IndicePicross ind = indicePicrossListVertical.get(idInd);
        	boolean ligneToIgnore = false;
        	for(IndiceLigne indiceLigne : indiceLigneListVertical)
        	{
        		if(indiceLigne.ligne == idInd && indiceLigne.typeIndiceLigne == TypeIndiceLigne.INDICES_PICROSS_HIDDEN)
        		{
        			ligneToIgnore = true;
        			break;
        		}
        	}
        	if(ligneToIgnore)
        	{
	            y_tmp = startYTableau - ConfigPartie.taille_tuile / 2 - tailleIndice;
                DrawTools.drawImageTransformed(img_pbm,DrawTools.getImage("image/Unknow.png"),x_tmp,y_tmp,0,50);
        	}
        	else
        	{
	            //pour chaque indice il fau calculer l'ordonnee initiale  pour que Ã§a fasse joli
	            y_tmp = startYTableau - ConfigPartie.taille_tuile / 2 - ind.list_hauteurs.size() * tailleIndice + decalageIndice;
	            for (int haut : ind.list_hauteurs){
	                BufferedImage htmp = DrawTools.getImageSuperposed("image/" + ConfigPartie.style + "Type" + haut + ".png", "image/carreB.png");
	                DrawTools.drawImageTransformed(img_pbm,htmp,x_tmp,y_tmp,0,18);
	                y_tmp=y_tmp+tailleIndice;
	            }
        	}
            x_tmp=x_tmp+ConfigPartie.taille_tuile;
        }
        //indice horizontal

        y_tmp=startYTableau;
        for(int idInd = 0; idInd < indicePicrossListHorizontal.size(); idInd++)
        {
        	IndicePicross ind = indicePicrossListHorizontal.get(idInd);
        	boolean ligneToIgnore = false;
        	for(IndiceLigne indiceLigne : indiceLigneListHorizontal)
        	{
        		if(indiceLigne.ligne == idInd && indiceLigne.typeIndiceLigne == TypeIndiceLigne.INDICES_PICROSS_HIDDEN)
        		{
        			ligneToIgnore = true;
        			break;
        		}
        	}
        	if(ligneToIgnore)
        	{
	            x_tmp = startXTableau - ConfigPartie.taille_tuile / 2 - tailleIndice;
                DrawTools.drawImageTransformed(img_pbm,DrawTools.getImage("image/Unknow.png"),x_tmp,y_tmp,0,50);
        	}
        	else
        	{
	            x_tmp=startXTableau - ConfigPartie.taille_tuile / 2 - ind.list_hauteurs.size() * tailleIndice + decalageIndice;
	            for (int haut : ind.list_hauteurs){
	                BufferedImage htmp = DrawTools.getImageSuperposed("image/" + ConfigPartie.style + "Type" + haut + ".png", "image/carreB.png");
	                DrawTools.drawImageTransformed(img_pbm,htmp,x_tmp,y_tmp,0,18);
	                x_tmp=x_tmp+tailleIndice;
	            }
        	}
            y_tmp=y_tmp+ConfigPartie.taille_tuile;
        }
        //plateau vierge
        BufferedImage img_case_vide = DrawTools.getImage(ConfigPartie.img_case);
        x_tmp=startXTableau;
        y_tmp=startYTableau;
        for (int abs=0; abs < ConfigPartie.largeur_plateau; abs++){
            for (int ord=0; ord < ConfigPartie.hauteur_plateau; ord++){
            	boolean caseVide = false;
            	for(IndiceCase indiceCase : indiceCaseList)
            	{
            		if(indiceCase.typeIndiceCase == TypeIndiceCase.VIDE)
            		{
            			if(indiceCase.coord.x == abs && indiceCase.coord.y == ord)
            				caseVide = true;
            		}
            	}
            	if(caseVide)
                    DrawTools.drawImageCenter(img_pbm,DrawTools.getImage("image/carreVide.png"),x_tmp+(abs*100),y_tmp+(ord*100));
            	else
            		DrawTools.drawImageCenter(img_pbm,img_case_vide,x_tmp+(abs*100),y_tmp+(ord*100));
            }
        }
        
        //Indices pièce
        for(IndicePiece indiceToDraw : indicePieceList)
        {
        	switch(indiceToDraw.typeIndicePiece)
        	{
        	case EMPLACEMENT_CONNU:
        		img_pbm.getGraphics().drawImage(indiceToDraw.polymino.exportGraphical(),x_tmp - 50,y_tmp - 50, null);
        		break;
        	case FACE_CONNU:
        		// A coder
        		break;
        	case ROTATION_CONNU:
        		// A coder
        		break;
        	}
        }
    return img_pbm;
    }

    //a retravailler dynamiquement
    public BufferedImage export() {

        //declaration de la taille de l'image.
        BufferedImage img_pbm = new BufferedImage((ConfigPartie.largeur_plateau*150)+100,(ConfigPartie.hauteur_plateau*150)+100,BufferedImage.TYPE_INT_ARGB);
        //Indicevretical
        int x_tmp=(ConfigPartie.largeur_plateau*50)+75;//calcul : espace pour les indices horizontal (largeur*50))
        int y_tmp;
        for(IndicePicross ind : indicePicrossListVertical){
            //pour chaque indice il fau calculer l'ordonnee initiale  pour que Ã§a fasse joli
            y_tmp = 100+ (ConfigPartie.hauteur_plateau-ind.list_hauteurs.size())*40;
            for (int haut : ind.list_hauteurs){
                BufferedImage htmp = DrawTools.getImage(ConfigPartie.hashMapHauteurImage.get(haut));
                DrawTools.drawImageTransformed(img_pbm,htmp,x_tmp,y_tmp,0,30);
                y_tmp=y_tmp+40;
            }
            x_tmp=x_tmp+ConfigPartie.taille_tuile;
        }
        //indice horizontal

        y_tmp=(ConfigPartie.hauteur_plateau*50)+75;
        for(IndicePicross ind : indicePicrossListHorizontal){
            x_tmp=100+(ConfigPartie.largeur_plateau-ind.list_hauteurs.size())*40;
            for (int haut : ind.list_hauteurs){
                BufferedImage htmp = DrawTools.getImage(ConfigPartie.hashMapHauteurImage.get(haut));
                DrawTools.drawImageTransformed(img_pbm,htmp,x_tmp,y_tmp,0,30);
                x_tmp=x_tmp+40;
            }

            y_tmp=y_tmp+ConfigPartie.taille_tuile;
        }
        //plateau vierge
        BufferedImage img_case_vide = DrawTools.getImage(ConfigPartie.img_case);
        x_tmp=(ConfigPartie.largeur_plateau*50)+75;
        y_tmp=(ConfigPartie.hauteur_plateau*50)+75;
        for (int abs=0; abs < ConfigPartie.largeur_plateau; abs++){
            for (int ord=0; ord < ConfigPartie.hauteur_plateau; ord++){
                DrawTools.drawImageCenter(img_pbm,img_case_vide,x_tmp+(abs*100),y_tmp+(ord*100));
            }
        }
    return img_pbm;
    }
}
