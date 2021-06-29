import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SolveurPolymino
{
	List<Tuile> tuileList;
	public List<Point> listCoordonneesPossible;
	
	public SolveurPolymino(List<Tuile> tuileList)
	{
		this.tuileList = tuileList;
		listCoordonneesPossible = new ArrayList<>();
	}
	
	public int getNbPositionPossible()
	{
		return(listCoordonneesPossible.size());
	}
}
