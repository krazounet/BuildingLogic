import java.awt.image.BufferedImage;
import Enum.*;

public class Case {
    Coordonnee coordonnee;
    boolean isVide=true;
    BordureCase bordureCase=new BordureCase();

    public Case(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;

    }

    public Case() {

    }

    public boolean a_une_bordure(CoteBordure cote){
        return bordureCase.bordureBooleanHashMap.get(cote);
    }
    public void maj_bordure(CoteBordure cote,Boolean bool){
        bordureCase.bordureBooleanHashMap.put(cote,bool);
    }
    public BufferedImage export(){
        return DrawTools.getImage("image/carre.png");
    }

}
