import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    
    public Polymino getClone()
    {
		List<Tuile> tuileSource = new ArrayList<>();
		for(Tuile tuileToAdd : this.tuileList)
		{
			tuileSource.add(new Tuile(tuileToAdd.hauteur, new Coordonnee(tuileToAdd.coordonnee.x, tuileToAdd.coordonnee.y), this.recto));
		}
		return(new Polymino(tuileSource, this.typePolymino, this.orientation, this.recto));
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

    public static List<Polymino> getFullListPolymino(Polymino polyminoModele)
    {
    	List<Polymino> listPolymino = new ArrayList<>();
    	listPolymino.add(getRotation(polyminoModele, 0, false));
    	listPolymino.add(getRotation(polyminoModele, 1, false));
    	listPolymino.add(getRotation(polyminoModele, 2, false));
    	listPolymino.add(getRotation(polyminoModele, 3, false));

    	if(ConfigPartie.rectoverso)
    	{
	    	listPolymino.add(getRotation(polyminoModele, 0, true));
	    	listPolymino.add(getRotation(polyminoModele, 1, true));
	    	listPolymino.add(getRotation(polyminoModele, 2, true));
	    	listPolymino.add(getRotation(polyminoModele, 3, true));
    	}
    	
    	removeDoublon(listPolymino);

        return(listPolymino);
    }
    
    private static void removeDoublon(List<Polymino> listPolymino)
    {
    	for(int pA = 0; pA < listPolymino.size() - 1; pA++)
    	{
    		Polymino polyA = listPolymino.get(pA);
    		for(int pB = pA + 1; pB < listPolymino.size(); pB++)
    		{
        		Polymino polyB = listPolymino.get(pB);
        		if(comparePolymino(polyA, polyB))
        		{
        			listPolymino.remove(pB);
        			pB--;
        		}
    		}
    	}
    }
    
    private static boolean comparePolymino(Polymino polyA, Polymino polyB)
    {
    	if(polyA.tuileList.size() != polyB.tuileList.size())
    		return(false);

    	for(Tuile tuileFromPolyA : polyA.tuileList)
    	{
    		boolean tuilePresente = false;
        	for(Tuile tuileFromPolyB : polyB.tuileList)
        	{
        		if(tuileFromPolyA.coordonnee.x == tuileFromPolyB.coordonnee.x && tuileFromPolyA.coordonnee.y == tuileFromPolyB.coordonnee.y && tuileFromPolyA.getHauteur() == tuileFromPolyB.getHauteur())
        		{
        			tuilePresente = true;
        			break;
        		}
        	}
        	if(!tuilePresente)
        		return(false);
    	}
    	return(true);
    }

	private static Polymino getRotation(Polymino polyminoSource, int rotation, boolean flip)
	{
		Polymino polyminoToReturn = polyminoSource.getClone();

		for(int r = 0; r < rotation; r++)
		{
			List<Tuile> tuileList = new ArrayList<>();
			for(Tuile tuileToAdd : polyminoToReturn.tuileList)
			{
				Coordonnee coordToAdd = new Coordonnee(polyminoToReturn.getMaxY() - tuileToAdd.coordonnee.y, tuileToAdd.coordonnee.x);
				
				tuileList.add(new Tuile(tuileToAdd.hauteur, coordToAdd, polyminoSource.recto));
			}
			
			polyminoToReturn = new Polymino(tuileList, polyminoSource.typePolymino, rotation, polyminoSource.recto);
		}

		if(flip)
		{
			List<Tuile> tuileListFlip = new ArrayList<>();
			for(Tuile tuileToAdd : polyminoToReturn.tuileList)
			{
				Coordonnee coordToAdd = new Coordonnee(polyminoToReturn.getMaxX() - tuileToAdd.coordonnee.x, tuileToAdd.coordonnee.y);
				
				tuileListFlip.add(new Tuile(tuileToAdd.hauteur, coordToAdd, !polyminoSource.recto));
			}
			
			polyminoToReturn = new Polymino(tuileListFlip, polyminoSource.typePolymino, rotation, !polyminoSource.recto);
		}
		
		return(polyminoToReturn);
	}

}
