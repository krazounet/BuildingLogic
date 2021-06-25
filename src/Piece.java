import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import Enum.*;

//une piece est un ensemble de positionnement du polymino
public class Piece {
    List<Polymino> polyminoList;

    public Piece(List<Polymino> polyminoList) {
        this.polyminoList = polyminoList;

    }

    public static Piece O = getO();
    public static Piece L = getL();
    public static Piece I = getI();
    public static Piece S = getS();
    public static Piece T = getT();
    
	private static Polymino getRotation(Polymino polyminoSource, int rotation, boolean flip)
	{
		List<Tuile> tuileSource = new ArrayList<>();
		for(Tuile tuileToAdd : polyminoSource.tuileList)
		{
			tuileSource.add(new Tuile(tuileToAdd.hauteur, new Coordonnee(tuileToAdd.coordonnee.x, tuileToAdd.coordonnee.y)));
		}
		Polymino polyminoToReturn = new Polymino(tuileSource, polyminoSource.typePolymino, rotation, polyminoSource.recto);

		for(int r = 0; r < rotation; r++)
		{
			List<Tuile> tuileList = new ArrayList<>();
			for(Tuile tuileToAdd : polyminoToReturn.tuileList)
			{
				Coordonnee coordToAdd = new Coordonnee(polyminoToReturn.getMaxY() - tuileToAdd.coordonnee.y, tuileToAdd.coordonnee.x);
				
				tuileList.add(new Tuile(tuileToAdd.hauteur, coordToAdd));
			}
			
			polyminoToReturn = new Polymino(tuileList, polyminoSource.typePolymino, rotation, polyminoSource.recto);
		}

		if(flip)
		{
			List<Tuile> tuileListFlip = new ArrayList<>();
			for(Tuile tuileToAdd : polyminoToReturn.tuileList)
			{
				Coordonnee coordToAdd = new Coordonnee(polyminoToReturn.getMaxX() - tuileToAdd.coordonnee.x, tuileToAdd.coordonnee.y);
				
				tuileListFlip.add(new Tuile(tuileToAdd.hauteur, coordToAdd));
			}
			
			polyminoToReturn = new Polymino(tuileListFlip, polyminoSource.typePolymino, rotation, !polyminoSource.recto);
		}
		
		return(polyminoToReturn);
	}

    private static Piece getT() {
        List<Polymino> polyminoList =new ArrayList<>();
        List<Tuile> tuileListT0t=new ArrayList<>();
        tuileListT0t.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListT0t.add(new Tuile(3,new Coordonnee(1,0)));
        tuileListT0t.add(new Tuile(1,new Coordonnee(1,1)));
        tuileListT0t.add(new Tuile(2,new Coordonnee(2,0)));
        polyminoList.add(new Polymino(tuileListT0t,TypePolymino.T,0,true));

        polyminoList.add(getRotation(polyminoList.get(0), 1, false));
        polyminoList.add(getRotation(polyminoList.get(0), 2, false));
        polyminoList.add(getRotation(polyminoList.get(0), 3, false));

        polyminoList.add(getRotation(polyminoList.get(0), 0, true));
        polyminoList.add(getRotation(polyminoList.get(0), 1, true));
        polyminoList.add(getRotation(polyminoList.get(0), 2, true));
        polyminoList.add(getRotation(polyminoList.get(0), 3, true));

/*        List<Tuile> tuileListT1t=new ArrayList<>();
        tuileListT1t.add(new Tuile(2,new Coordonnee(0,2)));
        tuileListT1t.add(new Tuile(3,new Coordonnee(0,1)));
        tuileListT1t.add(new Tuile(1,new Coordonnee(1,1)));
        tuileListT1t.add(new Tuile(2,new Coordonnee(0,0)));
        polyminoList.add(new Polymino(tuileListT1t,TypePolymino.T,1,true));

        List<Tuile> tuileListT2t=new ArrayList<>();
        tuileListT2t.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListT2t.add(new Tuile(3,new Coordonnee(1,1)));
        tuileListT2t.add(new Tuile(1,new Coordonnee(1,0)));
        tuileListT2t.add(new Tuile(2,new Coordonnee(2,1)));
        polyminoList.add(new Polymino(tuileListT2t,TypePolymino.T,2,true));

        List<Tuile> tuileListT3t=new ArrayList<>();
        tuileListT3t.add(new Tuile(2,new Coordonnee(1,2)));
        tuileListT3t.add(new Tuile(3,new Coordonnee(1,1)));
        tuileListT3t.add(new Tuile(1,new Coordonnee(0,1)));
        tuileListT3t.add(new Tuile(2,new Coordonnee(1,0)));
        polyminoList.add(new Polymino(tuileListT3t,TypePolymino.T,3,true));

        List<Tuile> tuileListT0f=new ArrayList<>();
        tuileListT0f.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListT0f.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListT0f.add(new Tuile(2,new Coordonnee(1,1)));
        tuileListT0f.add(new Tuile(1,new Coordonnee(2,0)));
        polyminoList.add(new Polymino(tuileListT0f,TypePolymino.T,0,false));

        List<Tuile> tuileListT1f=new ArrayList<>();
        tuileListT1f.add(new Tuile(1,new Coordonnee(0,2)));
        tuileListT1f.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListT1f.add(new Tuile(2,new Coordonnee(1,1)));
        tuileListT1f.add(new Tuile(1,new Coordonnee(0,0)));
        polyminoList.add(new Polymino(tuileListT1f,TypePolymino.T,1,false));*/

        return new Piece(polyminoList);
    }

    private static Piece getS() {
        List<Polymino> polyminoList =new ArrayList<>();
        List<Tuile> tuileListS0t=new ArrayList<>();
        tuileListS0t.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListS0t.add(new Tuile(3,new Coordonnee(1,0)));
        tuileListS0t.add(new Tuile(2,new Coordonnee(1,1)));
        tuileListS0t.add(new Tuile(1,new Coordonnee(2,1)));
        polyminoList.add(new Polymino(tuileListS0t,TypePolymino.S,0,true));
        
        polyminoList.add(getRotation(polyminoList.get(0), 1, false));
        polyminoList.add(getRotation(polyminoList.get(0), 2, false));
        polyminoList.add(getRotation(polyminoList.get(0), 3, false));

        polyminoList.add(getRotation(polyminoList.get(0), 0, true));
        polyminoList.add(getRotation(polyminoList.get(0), 1, true));
        polyminoList.add(getRotation(polyminoList.get(0), 2, true));
        polyminoList.add(getRotation(polyminoList.get(0), 3, true));

/*        List<Tuile> tuileListS1t=new ArrayList<>();
        tuileListS1t.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListS1t.add(new Tuile(3,new Coordonnee(1,1)));
        tuileListS1t.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListS1t.add(new Tuile(1,new Coordonnee(0,2)));
        polyminoList.add(new Polymino(tuileListS1t,TypePolymino.S,1,true));

        List<Tuile> tuileListS2t=new ArrayList<>();
        tuileListS2t.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListS2t.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListS2t.add(new Tuile(3,new Coordonnee(1,1)));
        tuileListS2t.add(new Tuile(2,new Coordonnee(2,1)));
        polyminoList.add(new Polymino(tuileListS2t,TypePolymino.S,2,true));

        List<Tuile> tuileListS3t=new ArrayList<>();
        tuileListS3t.add(new Tuile(1,new Coordonnee(1,0)));
        tuileListS3t.add(new Tuile(2,new Coordonnee(1,1)));
        tuileListS3t.add(new Tuile(3,new Coordonnee(0,1)));
        tuileListS3t.add(new Tuile(2,new Coordonnee(0,2)));
        polyminoList.add(new Polymino(tuileListS3t,TypePolymino.S,3,true));

        List<Tuile> tuileListS0f=new ArrayList<>();
        tuileListS0f.add(new Tuile(1,new Coordonnee(0,1)));
        tuileListS0f.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListS0f.add(new Tuile(2,new Coordonnee(1,1)));
        tuileListS0f.add(new Tuile(1,new Coordonnee(2,0)));
        polyminoList.add(new Polymino(tuileListS0f,TypePolymino.S,0,false));

        List<Tuile> tuileListS1f=new ArrayList<>();
        tuileListS1f.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListS1f.add(new Tuile(2,new Coordonnee(1,1)));
        tuileListS1f.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListS1f.add(new Tuile(1,new Coordonnee(1,2)));
        polyminoList.add(new Polymino(tuileListS1f,TypePolymino.S,1,false));*/


        return new Piece(polyminoList);
    }


    private static Piece getI() {
        List<Polymino> polyminoList =new ArrayList<>();
        List<Tuile> tuileListI0t=new ArrayList<>();
        tuileListI0t.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListI0t.add(new Tuile(3,new Coordonnee(0,1)));
        tuileListI0t.add(new Tuile(2,new Coordonnee(0,2)));
        tuileListI0t.add(new Tuile(1,new Coordonnee(0,3)));
        polyminoList.add(new Polymino(tuileListI0t,TypePolymino.I,0,true));
        
        polyminoList.add(getRotation(polyminoList.get(0), 1, false));
        polyminoList.add(getRotation(polyminoList.get(0), 2, false));
        polyminoList.add(getRotation(polyminoList.get(0), 3, false));

        polyminoList.add(getRotation(polyminoList.get(0), 0, true));
        polyminoList.add(getRotation(polyminoList.get(0), 1, true));
        polyminoList.add(getRotation(polyminoList.get(0), 2, true));
        polyminoList.add(getRotation(polyminoList.get(0), 3, true));

/*        List<Tuile> tuileListI1t=new ArrayList<>();
        tuileListI1t.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListI1t.add(new Tuile(3,new Coordonnee(1,0)));
        tuileListI1t.add(new Tuile(2,new Coordonnee(2,0)));
        tuileListI1t.add(new Tuile(1,new Coordonnee(3,0)));
        polyminoList.add(new Polymino(tuileListI1t,TypePolymino.I,1,true));

        List<Tuile> tuileListI2t=new ArrayList<>();
        tuileListI2t.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListI2t.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListI2t.add(new Tuile(3,new Coordonnee(0,2)));
        tuileListI2t.add(new Tuile(2,new Coordonnee(0,3)));
        polyminoList.add(new Polymino(tuileListI2t,TypePolymino.I,2,true));

        List<Tuile> tuileListI3t=new ArrayList<>();
        tuileListI3t.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListI3t.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListI3t.add(new Tuile(3,new Coordonnee(2,0)));
        tuileListI3t.add(new Tuile(2,new Coordonnee(3,0)));
        polyminoList.add(new Polymino(tuileListI3t,TypePolymino.I,3,true));

        List<Tuile> tuileListI0f=new ArrayList<>();
        tuileListI0f.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListI0f.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListI0f.add(new Tuile(2,new Coordonnee(0,2)));
        tuileListI0f.add(new Tuile(1,new Coordonnee(0,3)));
        polyminoList.add(new Polymino(tuileListI0f,TypePolymino.I,0,false));

        List<Tuile> tuileListI1f=new ArrayList<>();
        tuileListI1f.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListI1f.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListI1f.add(new Tuile(2,new Coordonnee(2,0)));
        tuileListI1f.add(new Tuile(1,new Coordonnee(3,0)));
        polyminoList.add(new Polymino(tuileListI1f,TypePolymino.I,1,false));*/




        return new Piece(polyminoList);
    }

    private static Piece getL() {
        List<Polymino> polyminoList =new ArrayList<>();
        List<Tuile> tuileListl0t=new ArrayList<>();
        tuileListl0t.add(new Tuile(3,new Coordonnee(0,0)));
        tuileListl0t.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListl0t.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListl0t.add(new Tuile(1,new Coordonnee(0,2)));
        polyminoList.add(new Polymino(tuileListl0t,TypePolymino.L,0,true));
        
        polyminoList.add(getRotation(polyminoList.get(0), 1, false));
        polyminoList.add(getRotation(polyminoList.get(0), 2, false));
        polyminoList.add(getRotation(polyminoList.get(0), 3, false));

        polyminoList.add(getRotation(polyminoList.get(0), 0, true));
        polyminoList.add(getRotation(polyminoList.get(0), 1, true));
        polyminoList.add(getRotation(polyminoList.get(0), 2, true));
        polyminoList.add(getRotation(polyminoList.get(0), 3, true));

/*        List<Tuile> tuileListl1t=new ArrayList<>();
        tuileListl1t.add(new Tuile(3,new Coordonnee(0,1)));
        tuileListl1t.add(new Tuile(2,new Coordonnee(1,1)));
        tuileListl1t.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListl1t.add(new Tuile(1,new Coordonnee(2,1)));
        polyminoList.add(new Polymino(tuileListl1t,TypePolymino.L,1,true));

        List<Tuile> tuileListl2t=new ArrayList<>();
        tuileListl2t.add(new Tuile(3,new Coordonnee(1,2)));
        tuileListl2t.add(new Tuile(2,new Coordonnee(0,2)));
        tuileListl2t.add(new Tuile(2,new Coordonnee(1,1)));
        tuileListl2t.add(new Tuile(1,new Coordonnee(1,0)));
        polyminoList.add(new Polymino(tuileListl2t,TypePolymino.L,2,true));

        List<Tuile> tuileListl3t=new ArrayList<>();
        tuileListl3t.add(new Tuile(3,new Coordonnee(2,0)));
        tuileListl3t.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListl3t.add(new Tuile(2,new Coordonnee(2,1)));
        tuileListl3t.add(new Tuile(1,new Coordonnee(0,0)));
        polyminoList.add(new Polymino(tuileListl3t,TypePolymino.L,3,true));

        List<Tuile> tuileListl0f=new ArrayList<>();
        tuileListl0f.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListl0f.add(new Tuile(1,new Coordonnee(1,0)));
        tuileListl0f.add(new Tuile(1,new Coordonnee(1,1)));
        tuileListl0f.add(new Tuile(2,new Coordonnee(1,2)));
        polyminoList.add(new Polymino(tuileListl0f,TypePolymino.L,0,false));

        List<Tuile> tuileListl1f=new ArrayList<>();
        tuileListl1f.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListl1f.add(new Tuile(1,new Coordonnee(1,0)));
        tuileListl1f.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListl1f.add(new Tuile(2,new Coordonnee(2,0)));
        polyminoList.add(new Polymino(tuileListl1f,TypePolymino.L,1,false));

        List<Tuile> tuileListl2f=new ArrayList<>();
        tuileListl2f.add(new Tuile(2,new Coordonnee(1,2)));
        tuileListl2f.add(new Tuile(1,new Coordonnee(0,2)));
        tuileListl2f.add(new Tuile(1,new Coordonnee(0,1)));
        tuileListl2f.add(new Tuile(2,new Coordonnee(0,0)));
        polyminoList.add(new Polymino(tuileListl2f,TypePolymino.L,2,false));

        List<Tuile> tuileListl3f=new ArrayList<>();
        tuileListl3f.add(new Tuile(2,new Coordonnee(2,0)));
        tuileListl3f.add(new Tuile(1,new Coordonnee(1,1)));
        tuileListl3f.add(new Tuile(1,new Coordonnee(2,1)));
        tuileListl3f.add(new Tuile(2,new Coordonnee(0,1)));
        polyminoList.add(new Polymino(tuileListl3f,TypePolymino.L,3,false));*/

        return new Piece(polyminoList);
    }

    private static Piece getO() {
        List<Polymino> polyminoList =new ArrayList<>();
        List<Tuile> tuileListO0t=new ArrayList<>();
        tuileListO0t.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListO0t.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListO0t.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListO0t.add(new Tuile(1,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO0t,TypePolymino.O,0,true));
        
        polyminoList.add(getRotation(polyminoList.get(0), 1, false));
        polyminoList.add(getRotation(polyminoList.get(0), 2, false));
        polyminoList.add(getRotation(polyminoList.get(0), 3, false));

        polyminoList.add(getRotation(polyminoList.get(0), 0, true));
        polyminoList.add(getRotation(polyminoList.get(0), 1, true));
        polyminoList.add(getRotation(polyminoList.get(0), 2, true));
        polyminoList.add(getRotation(polyminoList.get(0), 3, true));

/*        List<Tuile> tuileListO1t=new ArrayList<>();
        tuileListO1t.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListO1t.add(new Tuile(1,new Coordonnee(1,0)));
        tuileListO1t.add(new Tuile(1,new Coordonnee(0,1)));
        tuileListO1t.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO1t,TypePolymino.O,1,true));

        List<Tuile> tuileListO0f=new ArrayList<>();
        tuileListO0f.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListO0f.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListO0f.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListO0f.add(new Tuile(3,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO0f,TypePolymino.O,0,false));

        List<Tuile> tuileListO1f=new ArrayList<>();
        tuileListO1f.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListO1f.add(new Tuile(1,new Coordonnee(1,0)));
        tuileListO1f.add(new Tuile(3,new Coordonnee(0,1)));
        tuileListO1f.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO1f,TypePolymino.O,1,false));

        List<Tuile> tuileListO2f=new ArrayList<>();
        tuileListO2f.add(new Tuile(3,new Coordonnee(0,0)));
        tuileListO2f.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListO2f.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListO2f.add(new Tuile(1,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO2f,TypePolymino.O,2,false));

        List<Tuile> tuileListO3f=new ArrayList<>();
        tuileListO3f.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListO3f.add(new Tuile(3,new Coordonnee(1,0)));
        tuileListO3f.add(new Tuile(1,new Coordonnee(0,1)));
        tuileListO3f.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO3f,TypePolymino.O,3,false));*/

        return new Piece(polyminoList);

    }
}
