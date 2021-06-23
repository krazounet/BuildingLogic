import java.awt.image.BufferedImage;

public class Case {
    Coordonnee coordonnee;
    boolean isVide=true;
    BordureCase bordureCase;

    public Case(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;

    }

    public Case() {

    }


    public BufferedImage export(){
        return DrawTools.getImage("/image/carre.png");
    }

}
