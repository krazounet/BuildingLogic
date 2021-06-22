import Enum.*;

import java.util.List;

public class IndicePicross extends Indice{
    int index;
    TypeIndicePicross typeIndice;
    List<Integer> list_hauteurs;

    public IndicePicross(int index, TypeIndicePicross typeIndice, List<Integer> list_hauteur) {
        this.index = index;
        this.typeIndice = typeIndice;
        this.list_hauteurs = list_hauteur;
    }
}

