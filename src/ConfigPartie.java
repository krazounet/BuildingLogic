import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.logging.Logger;
import Enum.*;

public class ConfigPartie {

    private ConfigPartie(){}
    //config du plateau
    public static int largeur_plateau = 6;
    public static int hauteur_plateau = 6;

    //config algo placement piece
    public static int nb_essai_max = 1000;
    //TODO limiter le nombre de piece.

    //config souhait indice
    //TODO comme pour Minecraft rendre parametrable l'affichage des indice. Piece neutre/visible/bon sens.

    //config export graphique
    public static int taille_tuile = 100;
    public static String rep_image="image/";
    public static String rep_image_polymino ="image/Polymino/";
    public static String rep_export="export/";
    public static String img_fond = rep_image+"15x15.png";
    public static String img_vide = rep_image+"vide.png";//carre de 100*100 transparent
    public static String img_case = rep_image+"carre.png";//carre de 100*100 avec bordure
    public static HashMap<Integer,String> hashMapHauteurImage = getHashMapHauteurImage();
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


    private static HashMap<Integer, String> getHashMapHauteurImage() {
        HashMap<Integer, String> a_retourner = new HashMap<>();
        a_retourner.put(1,rep_image+"h1.png");
        a_retourner.put(2,rep_image+"h2.png");
        a_retourner.put(3,rep_image+"h3.png");
        return a_retourner;
    }

}
