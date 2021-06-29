import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SolveurPlacementPiece
{
	SolveurPiece piece;
	int idPolymino;
	int idCoordonnee;
	
	public SolveurPlacementPiece(SolveurPiece piece, int idPolymino, int idCoordonnee)
	{
		this.piece = piece;
		this.idPolymino = idPolymino;
		this.idCoordonnee = idCoordonnee;
	}
	
	public SolveurPolymino getActualPolymino()
	{
		return(piece.listPolymino.get(idPolymino));
	}
	
	public Point getActualCoordonnee()
	{
		return(new Point(getActualPolymino().listCoordonneesPossible.get(idCoordonnee).x, getActualPolymino().listCoordonneesPossible.get(idCoordonnee).y));
	}

	public List<Point> getActualCoordonnees()
	{
		List<Point> listPoints = new ArrayList<>();
		
		for(Tuile tuile : getActualPolymino().tuileList)
		{
			Point point = new Point(tuile.coordonnee.x + getActualPolymino().listCoordonneesPossible.get(idCoordonnee).x, tuile.coordonnee.y + getActualPolymino().listCoordonneesPossible.get(idCoordonnee).y);
			listPoints.add(point);
		}
		
		return(listPoints);
	}
}
