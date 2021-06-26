import java.awt.image.BufferedImage;
import java.util.List;


import Enum.*;

public class Tuile extends Case{
    HauteurTuile hauteur;
    boolean recto;


    public Tuile(HauteurTuile hauteur,Coordonnee coord,boolean recto) {
        super();
        super.isVide=false;
        super.coordonnee=coord;
        this.hauteur = hauteur;
        this.recto = recto;
    }
    
    public int getHauteur()
    {
    	if(recto)
    		return(hauteur.hauteurRecto);
    	else
    		return(hauteur.hauteurVerso);
    }

    @Override
    public BufferedImage export(){
        //couleur == hauteur
        BufferedImage img = DrawTools.getImage(ConfigPartie.hashMapHauteurImage.get(getHauteur()));
        //bordure a ajouter
        BufferedImage bordures = this.bordureCase.export();
        DrawTools.drawImageCenter(img,bordures,(double) ConfigPartie.taille_tuile/2,(double) ConfigPartie.taille_tuile/2);
        return img;
    }

    //compare pour cette tuile, si une tuile dans la liste a une bordure commune.
    //agit uniquement si la valeur de bordureCase est encore a false.
    public void maj_bordure_commune(CoteBordure coteBordure, List<Tuile> tuileList) {
        for (Tuile tuile : tuileList) {
            if (this == tuile)return;
            Coordonnee coordonnee_adjacent=this.coordonnee.getCoord(coteBordure);
            boolean b_test=coordonnee_adjacent.equals(tuile.coordonnee);
            if (b_test) {
                switch (coteBordure) {
                    case BAS -> {//inversion
                        tuile.maj_bordure(CoteBordure.BAS, false);
                        this.maj_bordure(CoteBordure.HAUT, false);
                    }
                    case HAUT -> {//inversion
                        tuile.maj_bordure(CoteBordure.HAUT, false);
                        this.maj_bordure(CoteBordure.BAS, false);
                    }
                    case GAUCHE -> {
                        this.maj_bordure(CoteBordure.GAUCHE, false);
                        tuile.maj_bordure(CoteBordure.DROITE, false);
                    }
                    case DROITE -> {
                        this.maj_bordure(CoteBordure.DROITE, false);
                        tuile.maj_bordure(CoteBordure.GAUCHE, false);
                    }
                }
            }

        }

    }
}
