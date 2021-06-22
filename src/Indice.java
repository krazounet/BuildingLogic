import Enum.*;

import java.util.List;

public class Indice {
    int index;
    TypeIndice typeIndice;
    List<Integer> list_hauteur;

    public Indice(int index, TypeIndice typeIndice, List<Integer> list_hauteur) {
        this.index = index;
        this.typeIndice = typeIndice;
        this.list_hauteur = list_hauteur;
    }
}
