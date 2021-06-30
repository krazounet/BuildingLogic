import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enum.TypePolymino;

public class SolveurPolymino
{
	List<Tuile> tuileList;
	public List<Point> listCoordonneesPossible;
	TypePolymino typePolymino;
	int orientation;
	boolean recto;
	
	public SolveurPolymino(List<Tuile> tuileList, TypePolymino typePolymino, int orientation, boolean recto)
	{
		this.tuileList = tuileList;
		this.typePolymino = typePolymino;
		this.orientation = orientation;
		this.recto = recto;
		listCoordonneesPossible = new ArrayList<>();
	}
	
	public int getNbPositionPossible()
	{
		return(listCoordonneesPossible.size());
	}
}
