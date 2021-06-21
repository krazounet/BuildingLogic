import java.util.List;
import Enum.*;

public class Polymino {

    List<Tuile> tuileList;
    int hauteur;
    int largeur;
    TypePolymino typePolymino;

    public Polymino(List<Tuile> tuileList, int hauteur, int largeur, TypePolymino typePolymino) {
        this.tuileList = tuileList;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.typePolymino = typePolymino;
    }


}
