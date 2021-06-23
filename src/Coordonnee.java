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
}
