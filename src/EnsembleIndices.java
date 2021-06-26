import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import Enum.*;

public class EnsembleIndices {
    List<IndicePicross> indicePicrossListVertical;
    List<IndicePicross> indicePicrossListHorizontal;
    List<IndiceCase> indiceCaseList;

    public EnsembleIndices(Plateau plateau) {

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
    }

    //a retravailler dynamiquement
    public BufferedImage export() {

        //declaration de la taille de l'image.
        BufferedImage img_pbm = new BufferedImage((ConfigPartie.largeur_plateau*150)+100,(ConfigPartie.hauteur_plateau*150)+100,BufferedImage.TYPE_INT_ARGB);
        //Indicevretical
        int x_tmp=(ConfigPartie.largeur_plateau*50)+75;//calcul : espace pour les indices horizontal (largeur*50))
        int y_tmp;
        for(IndicePicross ind : indicePicrossListVertical){
            //pour chaque indice il fau calculer l'ordonnee initiale  pour que ça fasse joli
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
