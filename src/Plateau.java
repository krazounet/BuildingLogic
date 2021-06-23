import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plateau {
    Case[][] tableau;
    List<Case> caseVideList=new ArrayList<>();

    public Plateau() {
        tableau= new Case[ConfigPartie.largeur_plateau][ConfigPartie.largeur_plateau];
        //la case du tableau contient sa propre coordonee
        for (int x=0; x<ConfigPartie.largeur_plateau; x++){
            for (int y=0; y<ConfigPartie.hauteur_plateau; y++) {
                Case case_tmp= new Case(new Coordonnee(x,y));
                tableau[x][y]= case_tmp;
                caseVideList.add(case_tmp);
            }
        }

    }

    //renvoie null si le Plateau est rempli
    //sinon renvoie une case vide
    public Case getRandomCaseVide() {
        if (caseVideList.size() == 0)return null;
        return caseVideList.get(new Random().nextInt(caseVideList.size()));
    }


    public boolean IsPolyminoPlacable(Case case_vide, Polymino polymino) {
        int x_case_origine=case_vide.coordonnee.x;
        int y_case_origine=case_vide.coordonnee.y;
        for (Tuile tuile : polymino.tuileList ){
            int x_case_destination = x_case_origine+tuile.coordonnee.x;
            int y_case_destination = y_case_origine+tuile.coordonnee.y;
            //test si on depasse du tableau
            if (x_case_destination>ConfigPartie.largeur_plateau-1)return false;
            if (x_case_destination<0)return false;
            if (y_case_destination>ConfigPartie.hauteur_plateau-1)return false;
            if (y_case_destination<0)return false;

            //test si la case est vide
            if (!tableau[x_case_destination][y_case_destination].isVide)return false;
        }
        return true;
    }

    //place le polymino sur le plateau, mets a jour la liste des cases vides.
    public void placePolymino(Case case_vide, Polymino polymino) {
        int x_case_origine=case_vide.coordonnee.x;
        int y_case_origine=case_vide.coordonnee.y;
        for (Tuile tuile : polymino.tuileList ){
            int x_case_destination = x_case_origine+tuile.coordonnee.x;
            int y_case_destination = y_case_origine+tuile.coordonnee.y;
            //on retire de la list des cases vide la case de destination
            caseVideList.remove(tableau[x_case_destination][y_case_destination]);
            //mise a jour de la case du tableau
            tableau[x_case_destination][y_case_destination]=tuile;
            //mise a jour des coordonnees de la tuile
            tuile.coordonnee.x=x_case_destination;
            tuile.coordonnee.y=y_case_destination;
        }

    }

    public BufferedImage export(){
        BufferedImage img= new BufferedImage(ConfigPartie.largeur_plateau*ConfigPartie.taille_tuile,ConfigPartie.hauteur_plateau*ConfigPartie.taille_tuile,BufferedImage.TYPE_INT_ARGB);
        for (int x=0; x<ConfigPartie.largeur_plateau; x++){
            for (int y=0; y<ConfigPartie.hauteur_plateau; y++) {
                DrawTools.drawImageCenter(img,tableau[x][y].export(),(double) ConfigPartie.taille_tuile/2+(x*ConfigPartie.taille_tuile),(double) ConfigPartie.taille_tuile/2+(y*ConfigPartie.taille_tuile));
            }
        }
        return img;
    }
}
