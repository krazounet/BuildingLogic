import Enum.*;

public class Coordonnee {
    int x;
    int y;

    public Coordonnee(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordonnee getCoord(CoteBordure coteBordure){
        switch (coteBordure) {
            case BAS -> {return new Coordonnee(x,y-1);
            }
            case HAUT -> {return new Coordonnee(x,y+1);
            }
            case GAUCHE -> {return new Coordonnee(x-1,y);
            }
            case DROITE -> {return new Coordonnee(x+1,y);
            }
        }
       return null;
    }
    @Override
    public boolean equals(Object c_test){
        if (this == c_test) { return true; }
        if (c_test == null || getClass() != c_test.getClass()){return false;}
        Coordonnee other =(Coordonnee)c_test;
        if (other.x != this.x)return false;
        if (other.y != this.y)return false;
        return true;
    }
}
