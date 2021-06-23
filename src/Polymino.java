import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import Enum.*;

//un polymino est l'ensemble de tuiles dans une position definie sur une face et une orientation definie
public class Polymino {

    List<Tuile> tuileList;
    int hauteur;//ici c'est la hauteur du polymino en nombre de tuile. Confusion avec hauteur du batiment dans la tuile.
    int largeur;
    TypePolymino typePolymino;
    int orientation;
    boolean recto;



    public Polymino(List<Tuile> tuileList, int hauteur, int largeur, TypePolymino typePolymino, int orientation, boolean recto) {
        this.tuileList = tuileList;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.typePolymino = typePolymino;
        this.orientation = orientation;
        this.recto = recto;
        //algo bordure ?
        updateBordure();
    }

    private void updateBordure() {
        for (Tuile tuile : tuileList){
            for (CoteBordure coteBordure : CoteBordure.values()){
                tuile.maj_bordure_commune(coteBordure,tuileList);
            }
        }
    }

    public BufferedImage export (){
        BufferedImage img =new BufferedImage(largeur*100,hauteur*100, BufferedImage.TYPE_INT_ARGB);

        return img;
    }

}
