import java.awt.image.BufferedImage;
import java.util.HashMap;

import Enum.*;

public class BordureCase {
    public HashMap<CoteBordure,Boolean> bordureBooleanHashMap;




    public BordureCase() {
        bordureBooleanHashMap = new HashMap<>();
        bordureBooleanHashMap.put(CoteBordure.BAS,false);
        bordureBooleanHashMap.put(CoteBordure.HAUT,false);
        bordureBooleanHashMap.put(CoteBordure.GAUCHE,false);
        bordureBooleanHashMap.put(CoteBordure.DROITE,false);
    }

    public BufferedImage export(){
        BufferedImage img = ConfigPartie.img_vide;
        for (CoteBordure coteBordure : CoteBordure.values()){
            if (bordureBooleanHashMap.get(coteBordure)){
                DrawTools.drawImageCenter(img,ConfigPartie.hashMapBorder.get(coteBordure),50,50);
            }
        }
        return img;
    }
}
