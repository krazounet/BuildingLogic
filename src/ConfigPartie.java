import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import Enum.*;

public class ConfigPartie {

    private ConfigPartie(){}
    
    //Config Style
    public static String style = "CT"; //CT = City / DI = Diamonds / VG = Vegetables / AB = Abstract
    
    public static int modele_repartition;
    public static boolean same_rectoverso;
    public static boolean rectoverso;
    public static boolean remove_doublon;
    public static List<TypePolymino> listPolyminoToUse;

    public static int modele = initSettings(1); // 1 = Fabien / 2 = Yoann / 3 = Recto Seul
    
    //config du plateau
    public static int largeur_plateau = 6;
    public static int hauteur_plateau = 6;

    //config algo placement piece
    public static int nb_essai_max = 100000;
    public static int nb_chaque_piece = 2;//2 veut dire que chaque PIECE (OSTLI) est présente 2 fois.
    //TODO limiter le nombre de piece.

    //config souhait indice
    //TODO comme pour Minecraft rendre parametrable l'affichage des indice. Piece neutre/visible/bon sens.
    public static int nb_pieces_placees = 1;
    public static int nb_pieces_face_connue = 0;
    public static int nb_pieces_rotation_connue = 0;

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
    
    private static int initSettings(int settings)
    {
    	switch(settings)
    	{
    	case 1:
    	    modele_repartition = 1;
    	    listPolyminoToUse = Arrays.asList(TypePolymino.I4, TypePolymino.J, TypePolymino.O, TypePolymino.Z, TypePolymino.T);
    	    same_rectoverso = false;
    	    rectoverso = true;
    	    remove_doublon = true;
    		break;
    	case 2:
    	    modele_repartition = 2;
    	    listPolyminoToUse = Arrays.asList(TypePolymino.I4, TypePolymino.J, TypePolymino.O, TypePolymino.Z, TypePolymino.T);
    	    same_rectoverso = true;
    	    rectoverso = true;
    	    remove_doublon = true;
    		break;
    	case 3:
    	    modele_repartition = 3;
    	    listPolyminoToUse = Arrays.asList(TypePolymino.I4, TypePolymino.I3, TypePolymino.I2, TypePolymino.O, TypePolymino.T, TypePolymino.L, TypePolymino.J, TypePolymino.S, TypePolymino.Z, TypePolymino.V);
    	    same_rectoverso = false;
    	    rectoverso = false;
    	    remove_doublon = true;
    		break;
    	}
    	return(settings);
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
