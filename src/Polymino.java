import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import Enum.*;

public class Polymino {

    List<Tuile> tuileList;
    int hauteur;
    int largeur;
    TypePolymino typePolymino;
    int orientation;
    boolean recto;

   public static List<Polymino> carre = getCarre();
   public static List<Polymino> L = getL();

    private static List<Polymino> getL() {
        List<Polymino> polyminoList =new ArrayList<>();
        List<Tuile> tuileList=new ArrayList<>();
        tuileList.add(new Tuile(3,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,1)));
        tuileList.add(new Tuile(1,new Coordonnee(0,2)));
        polyminoList.add(new Polymino(tuileList,3,2,TypePolymino.L,0,true));

        return polyminoList;
    }

    private static List<Polymino> getCarre() {
        List<Polymino> polyminoList =new ArrayList<>();
        List<Tuile> tuileList=new ArrayList<>();
        tuileList.add(new Tuile(1,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,1)));
        tuileList.add(new Tuile(1,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2,TypePolymino.O,0,true));

        tuileList.clear();
        tuileList.add(new Tuile(2,new Coordonnee(0,0)));
        tuileList.add(new Tuile(1,new Coordonnee(1,0)));
        tuileList.add(new Tuile(1,new Coordonnee(0,1)));
        tuileList.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2,TypePolymino.O,1,true));

        tuileList.clear();
        tuileList.add(new Tuile(1,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,1)));
        tuileList.add(new Tuile(3,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2,TypePolymino.O,0,false));

        tuileList.clear();
        tuileList.add(new Tuile(2,new Coordonnee(0,0)));
        tuileList.add(new Tuile(1,new Coordonnee(1,0)));
        tuileList.add(new Tuile(3,new Coordonnee(0,1)));
        tuileList.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2,TypePolymino.O,1,false));

        tuileList.clear();
        tuileList.add(new Tuile(3,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,1)));
        tuileList.add(new Tuile(1,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2,TypePolymino.O,2,false));

        tuileList.clear();
        tuileList.add(new Tuile(2,new Coordonnee(0,0)));
        tuileList.add(new Tuile(3,new Coordonnee(1,0)));
        tuileList.add(new Tuile(1,new Coordonnee(0,1)));
        tuileList.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2,TypePolymino.O,3,false));

        return polyminoList;

    }

    public Polymino(List<Tuile> tuileList, int hauteur, int largeur, TypePolymino typePolymino, int orientation, boolean recto) {
        this.tuileList = tuileList;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.typePolymino = typePolymino;
        this.orientation = orientation;
        this.recto = recto;
    }

    public BufferedImage export (){
        BufferedImage img =new BufferedImage(largeur*100,hauteur*100, BufferedImage.TYPE_INT_ARGB);

        return img;
    }

}
