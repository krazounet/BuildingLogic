import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ConfigPartie {

    //config du plateau
    public static int largeur_plateau = 3;
    public static int hauteur_plateau = 3;

    //config souhait piece

    //config souhait indice

    //config export graphique
    public static BufferedImage img_fond = DrawTools.getImage("/image/fond.png");
    public static HashMap<Integer,BufferedImage> hashMapHauteurImage = getHashMapHauteurImage();


    private static HashMap<Integer, BufferedImage> getHashMapHauteurImage() {
        HashMap<Integer, BufferedImage> a_retourner = new HashMap<>();
        a_retourner.put(1,DrawTools.getImage("/image/h1.png"));
        a_retourner.put(2,DrawTools.getImage("/image/h2.png"));
        a_retourner.put(3,DrawTools.getImage("/image/h3.png"));
        return a_retourner;
    }

}
