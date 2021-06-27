import java.awt.image.BufferedImage;
import java.util.HashMap;

import Enum.*;

public class BordureCase {
    public HashMap<CoteBordure,Boolean> bordureBooleanHashMap;




    public BordureCase() {
        bordureBooleanHashMap = new HashMap<>();
        bordureBooleanHashMap.put(CoteBordure.BAS,true);
        bordureBooleanHashMap.put(CoteBordure.HAUT,true);
        bordureBooleanHashMap.put(CoteBordure.GAUCHE,true);
        bordureBooleanHashMap.put(CoteBordure.DROITE,true);
    }

    public BufferedImage export(boolean recto){
        BufferedImage img = DrawTools.getImage(ConfigPartie.img_vide);

        for (CoteBordure coteBordure : CoteBordure.values()){
            if (bordureBooleanHashMap.get(coteBordure)){
                if (recto){
                    DrawTools.drawImageCenter(img,ConfigPartie.hashMapBorderR.get(coteBordure),50,50);
                }else{
                    DrawTools.drawImageCenter(img,ConfigPartie.hashMapBorderV.get(coteBordure),50,50);
                }

            }
        }
        return img;
    }

}
