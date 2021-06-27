import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
    
    public BufferedImage exportGraphical()
    {
    	BufferedImage img = new BufferedImage((1 + this.getMaxX() - this.getMinX()) * ConfigPartie.taille_tuile, (1 + this.getMaxY() - this.getMinY()) * ConfigPartie.taille_tuile, BufferedImage.TYPE_INT_ARGB);
    	for(Tuile tuile : tuileList)
    	{
    		DrawTools.drawImageTransformed(img, tuile.exportGraphical(), (ConfigPartie.taille_tuile / 2) + (tuile.coordonnee.x * ConfigPartie.taille_tuile), (ConfigPartie.taille_tuile / 2) + (tuile.coordonnee.y * ConfigPartie.taille_tuile), 0, 50);
    	}
    	
    	drawBordures(img);
    	return(img);
    }
    
    public void drawBordures(BufferedImage img)
    {
    	for(Tuile tuile : tuileList)
    	{
    		for(CoteBordureG coteBordure : CoteBordureG.values())
    		{
        		if(!isThisTuileExist(tuile.coordonnee.x + getXBordure(coteBordure), tuile.coordonnee.y + getYBordure(coteBordure)))
        		{
        			if(tuile.recto)
        				drawBordure(img, tuile, coteBordure, ConfigPartie.color_bordure_recto);
        			else
        				drawBordure(img, tuile, coteBordure, ConfigPartie.color_bordure_verso);
        		}
    		}
    	}
    }
    
    public void drawBordure(BufferedImage img, Tuile tuile, CoteBordureG coteBordure, Color color)
    {
    	Point start = getBordureStart(coteBordure);
    	Point end = getBordureEnd(coteBordure);
    	
    	int tailleSquare = ConfigPartie.taille_tuile - ConfigPartie.epaisseur_bord * 2;
    	
    	start.x = ConfigPartie.taille_tuile * tuile.coordonnee.x + ConfigPartie.epaisseur_bord + start.x * tailleSquare;
    	start.y = ConfigPartie.taille_tuile * tuile.coordonnee.y + ConfigPartie.epaisseur_bord + start.y * tailleSquare;

    	end.x = ConfigPartie.taille_tuile * tuile.coordonnee.x + ConfigPartie.epaisseur_bord + end.x * tailleSquare;
    	end.y = ConfigPartie.taille_tuile * tuile.coordonnee.y + ConfigPartie.epaisseur_bord + end.y * tailleSquare;
    	
		Graphics g = img.getGraphics();
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(color);
		g2.setStroke(new BasicStroke(ConfigPartie.epaisseur_bord * 2));
		g2.drawLine(start.x, start.y, end.x, end.y);
    }

    public Point getBordureStart(CoteBordureG coteBordure)
    {
    	int x = 0;
    	int y = 0;
    	switch(coteBordure)
    	{
    	case HAUT:
    	case GAUCHE:
    	case HAUT_GAUCHE:
    		x = 0;
    		y = 0;
    		break;
    	case BAS:
    	case DROITE:
    	case BAS_DROITE:
    		x = 1;
    		y = 1;
    		break;
    	case HAUT_DROITE:
    		x = 1;
    		y = 0;
    		break;
    	case BAS_GAUCHE:
    		x = 0;
    		y = 1;
    		break;
    	}
    	return(new Point(x, y));
    }
    
    public Point getBordureEnd(CoteBordureG coteBordure)
    {
    	int x = 0;
    	int y = 0;
    	switch(coteBordure)
    	{
    	case HAUT:
    	case DROITE:
    	case HAUT_DROITE:
    		x = 1;
    		y = 0;
    		break;
    	case BAS:
    	case GAUCHE:
    	case BAS_GAUCHE:
    		x = 0;
    		y = 1;
    		break;
    	case HAUT_GAUCHE:
    		x = 0;
    		y = 0;
    		break;
    	case BAS_DROITE:
    		x = 1;
    		y = 1;
    		break;
    	}
    	return(new Point(x, y));
    }
    
    public int getXBordure(CoteBordureG coteBordure)
    {
    	switch(coteBordure)
    	{
    	case GAUCHE:
    	case HAUT_GAUCHE:
    	case BAS_GAUCHE:
    		return(-1);
    	case DROITE:
    	case HAUT_DROITE:
    	case BAS_DROITE:
    		return(1);
		default:
			return(0);
    	}
    }
    
    public int getYBordure(CoteBordureG coteBordure)
    {
    	switch(coteBordure)
    	{
    	case HAUT:
    	case HAUT_GAUCHE:
    	case HAUT_DROITE:
    		return(-1);
    	case BAS:
    	case BAS_DROITE:
    	case BAS_GAUCHE:
    		return(1);
		default :
			return(0);
    	}
    }
    
    public boolean isThisTuileExist(int x, int y)
    {
    	for(Tuile tuile : tuileList)
    		if(tuile.coordonnee.x == x && tuile.coordonnee.y == y)
    			return(true);
    	return(false);
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
    	
    	if(ConfigPartie.remove_doublon)
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
