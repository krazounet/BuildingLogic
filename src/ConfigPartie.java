import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.logging.Logger;
import Enum.*;

public class ConfigPartie {

    private ConfigPartie(){}
    
    //Config Style
    public static String style = "CT"; //CT = City / DI = Diamonds / VG = Vegetables / AB = Abstract
    
    //config pieces
    public static boolean rectoverso = true;
    public static boolean same_rectoverso = true;
    public static boolean remove_doublon = true;
    public static int modele_repartition = 2;

    //config du plateau
    public static int largeur_plateau = 5;
    public static int hauteur_plateau = 5;

    //config algo placement piece
    public static int nb_essai_max = 100000;
    public static int nb_chaque_piece = 1;//2 veut dire que chaque PIECE (OSTLI) est présente 2 fois.
    //TODO limiter le nombre de piece.

    //config souhait indice
    //TODO comme pour Minecraft rendre parametrable l'affichage des indice. Piece neutre/visible/bon sens.
    public static int nb_pieces_placees = 1;
    public static int nb_pieces_face_connue = 1;
    public static int nb_pieces_rotation_connue = 1;

    //config export graphique
    public static int taille_tuile = 100;
    public static int epaisseur_bord = 6;
    public static Color color_bordure_recto = Color.BLACK;
    public static Color color_bordure_verso = new Color(0, 138, 255);
    public static String rep_image="image/";
    public static String rep_image_polymino ="image/Polymino/";
    public static String rep_image_bordures ="image/bordures/";
    public static String rep_export="export/";
    public static String img_fond = rep_image+"15x15.png";
    public static String img_vide = rep_image+"vide.png";//carre de 100*100 transparent
    public static String img_case = rep_image+"carre.png";//carre de 100*100 avec bordure
    public static HashMap<Integer,String> hashMapHauteurImage = getHashMapHauteurImage();
    public static HashMap<CoteBordure,BufferedImage> hashMapBorderR = getHashMapBorder("R");
    public static HashMap<CoteBordure,BufferedImage> hashMapBorderV = getHashMapBorder("V");

    private static HashMap<CoteBordure, BufferedImage> getHashMapBorder(String RV) {
        HashMap<CoteBordure,BufferedImage> hash_retour = new HashMap<>();
        for(CoteBordure cote:CoteBordure.values()){
            hash_retour.put(cote,DrawTools.getImage(rep_image_bordures+RV+cote+".png"));
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
