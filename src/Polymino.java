import java.awt.image.BufferedImage;
import java.util.List;
import Enum.*;

//un polymino est l'ensemble de tuiles dans une position definie sur une face et une orientation definie
public class Polymino {

    List<Tuile> tuileList;
    int hauteur;//ici c'est la hauteur du polymino en nombre de tuile. Confusion avec hauteur du batiment dans la tuile.
    int largeur;
    TypePolymino typePolymino;
    int orientation;
    boolean recto;



    public Polymino(List<Tuile> tuileList, TypePolymino typePolymino, int orientation, boolean recto) {

        this.tuileList = tuileList;
        this.hauteur = getMaxY() - getMinY() + 1;
        this.largeur = getMaxX() - getMinX() + 1;
        this.typePolymino = typePolymino;
        this.orientation = orientation;
        this.recto = recto;
        //algo bordure ?
        updateBordure();
    }
    
    public int getMinX()
    {
    	int minX = getMaxX();
    	for(Tuile tuileToTest : tuileList)
    	{
    		if(tuileToTest.coordonnee.x < minX)
    		{
    			minX = tuileToTest.coordonnee.x;
    		}
    	}
    	return(minX);
    }

    public int getMaxX()
    {
    	int maxX = 0;
    	for(Tuile tuileToTest : tuileList)
    	{
    		if(tuileToTest.coordonnee.x > maxX)
    		{
    			maxX = tuileToTest.coordonnee.x;
    		}
    	}
    	return(maxX);
    }

    public int getMinY()
    {
    	int minY = getMaxY();
    	for(Tuile tuileToTest : tuileList)
    	{
    		if(tuileToTest.coordonnee.y < minY)
    		{
    			minY = tuileToTest.coordonnee.y;
    		}
    	}
    	return(minY);
    }

    public int getMaxY()
    {
    	int maxY = 0;
    	for(Tuile tuileToTest : tuileList)
    	{
    		if(tuileToTest.coordonnee.y > maxY)
    		{
    			maxY = tuileToTest.coordonnee.y;
    		}
    	}
    	return(maxY);
    }

    private void updateBordure() {
        for (Tuile tuile : tuileList){
            for (CoteBordure coteBordure : CoteBordure.values()){
                if (!tuile.a_une_bordure(coteBordure))continue;//possible si traite par une autre tuile pour gagner du temps
                tuile.maj_bordure_commune(coteBordure,tuileList);
            }
        }
    }

    public BufferedImage export (){
        BufferedImage img =new BufferedImage(largeur*100,hauteur*100, BufferedImage.TYPE_INT_ARGB);
        for(Tuile tuile : tuileList){
            BufferedImage img_tuile=tuile.export();
            DrawTools.drawImageCenter(img,img_tuile,50+(tuile.coordonnee.x*100),50+(tuile.coordonnee.y*100));
        }
        return img;
    }

    public String getIdent (){
        return this.typePolymino+String.valueOf(this.orientation)+this.recto;
    }

}
