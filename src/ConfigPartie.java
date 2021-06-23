import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.logging.Logger;
import Enum.*;

public class ConfigPartie {

    private ConfigPartie(){}
    //config du plateau
    public static int largeur_plateau = 3;
    public static int hauteur_plateau = 3;

    //config souhait piece

    //config souhait indice

    //config export graphique
    public static int taille_tuile = 100;
    public static String rep_image="image/";
    public static BufferedImage img_fond = DrawTools.getImage(rep_image+"15x15.png");
    public static BufferedImage img_vide = DrawTools.getImage(rep_image+"vide.png");//carre de 100*100 transparent
    public static HashMap<Integer,BufferedImage> hashMapHauteurImage = getHashMapHauteurImage();
    public static HashMap<CoteBordure,BufferedImage> hashMapBorder = getHashMapBorder();

    private static HashMap<CoteBordure, BufferedImage> getHashMapBorder() {
        HashMap<CoteBordure,BufferedImage> hash_retour = new HashMap<>();
        for(CoteBordure cote:CoteBordure.values()){
            hash_retour.put(cote,DrawTools.getImage(rep_image+cote+".png"));
        }
        return hash_retour;
    }

    //Logger
public static final Logger LOGGER = Logger.getLogger( ConfigPartie.class.getName() );


    private static HashMap<Integer, BufferedImage> getHashMapHauteurImage() {
        HashMap<Integer, BufferedImage> a_retourner = new HashMap<>();
        a_retourner.put(1,DrawTools.getImage(rep_image+"h1.png"));
        a_retourner.put(2,DrawTools.getImage(rep_image+"h2.png"));
        a_retourner.put(3,DrawTools.getImage(rep_image+"h3.png"));
        return a_retourner;
    }

}
