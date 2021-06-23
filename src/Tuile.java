import java.awt.image.BufferedImage;
import java.util.List;


import Enum.*;

public class Tuile extends Case{
    int hauteur;

    public Tuile(int hauteur,Coordonnee coord) {
        super();
        super.isVide=false;
        super.coordonnee=coord;
        this.hauteur = hauteur;
    }

    @Override
    public BufferedImage export(){
        //couleur == hauteur
        BufferedImage img = ConfigPartie.hashMapHauteurImage.get(hauteur);
        //bordure a ajouter
        BufferedImage bordures = this.bordureCase.export();
        DrawTools.drawImageCenter(img,bordures,50,50);
        return img;
    }

    //compare pour cette tuile, si une tuile dans la liste a une bordure commune.
    //agit uniquement si la valeur de bordureCase est encore a false.
    public void maj_bordure_commune(CoteBordure coteBordure, List<Tuile> tuileList) {
        for (Tuile tuile : tuileList) {
            if (this.coordonnee.getCoord(coteBordure).equals(tuile.coordonnee)) {
                switch (coteBordure) {
                    case BAS -> {
                        this.maj_bordure(CoteBordure.BAS, true);
                        tuile.maj_bordure(CoteBordure.HAUT, true);
                    }
                    case HAUT -> {
                        this.maj_bordure(CoteBordure.HAUT, true);
                        tuile.maj_bordure(CoteBordure.BAS, true);
                    }
                    case GAUCHE -> {
                        this.maj_bordure(CoteBordure.GAUCHE, true);
                        tuile.maj_bordure(CoteBordure.DROITE, true);
                    }
                    case DROITE -> {
                        this.maj_bordure(CoteBordure.DROITE, true);
                        tuile.maj_bordure(CoteBordure.GAUCHE, true);
                    }
                }
            }

        }

    }
}
