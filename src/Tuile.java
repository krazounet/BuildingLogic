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
        switch (coteBordure){
            case BAS -> {
                if (this.bordureCase.BAS == true)return;//possible si traite par une autre tuile pour gagner du temps
                for (Tuile tuile : tuileList){
                    if ((tuile.coordonnee.x == this.coordonnee.x)&&(tuile.coordonnee.y == this.coordonnee.y-1)){//tuile est bien en dessous.
                        this.bordureCase.BAS=true;
                        tuile.bordureCase.HAUT=true;
                        return;
                    }
                }
            }
            case HAUT -> {
                if (this.bordureCase.HAUT == true)return;//possible si traite par une autre tuile pour gagner du temps
                for (Tuile tuile : tuileList){
                    if ((tuile.coordonnee.x == this.coordonnee.x)&&(tuile.coordonnee.y == this.coordonnee.y+1)){//tuile est bien au dessus.
                        this.bordureCase.HAUT=true;
                        tuile.bordureCase.BAS=true;
                        return;
                    }
                }
            }

            case GAUCHE -> {
                if (this.bordureCase.GAUCHE == true)return;//possible si traite par une autre tuile pour gagner du temps
                for (Tuile tuile : tuileList){
                    if ((tuile.coordonnee.x == this.coordonnee.x-1)&&(tuile.coordonnee.y == this.coordonnee.y)){//tuile est bien a gauche
                        this.bordureCase.GAUCHE=true;
                        tuile.bordureCase.DROITE=true;
                        return;
                    }
                }
            }
            case DROITE -> {
                if (this.bordureCase.DROITE == true)return;//possible si traite par une autre tuile pour gagner du temps
                for (Tuile tuile : tuileList){
                    if ((tuile.coordonnee.x == this.coordonnee.x+1)&&(tuile.coordonnee.y == this.coordonnee.y)){//tuile est bien a droite
                        this.bordureCase.DROITE=true;
                        tuile.bordureCase.GAUCHE=true;
                        return;
                    }
                }
            }

        }
    }
}
