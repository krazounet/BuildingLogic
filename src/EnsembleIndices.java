import java.util.ArrayList;
import java.util.List;
import Enum.*;

public class EnsembleIndices<Indice> {
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
            indicePicrossListHorizontal.add(new IndicePicross(ord, TypeIndicePicross.COLONNE,new ArrayList<>()));
        }

        //les indices sont generes a partir du plateau
        for (Case case_tab[] : plateau.tableau){
            for(Case case_tmp : case_tab){
                Tuile tuile_tmp=null;
                if (case_tmp instanceof Tuile){tuile_tmp=(Tuile)case_tmp;}
                if (tuile_tmp !=null){
                    indicePicrossListVertical.get(case_tmp.coordonnee.x).list_hauteurs.add(tuile_tmp.hauteur);
                    indicePicrossListHorizontal.get(case_tmp.coordonnee.y).list_hauteurs.add(tuile_tmp.hauteur);
                }
            }
        }
    }


}
