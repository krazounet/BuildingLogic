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
	
	public SolveurPlacementPiece(SolveurPiece piece)
	{
		this.piece = piece;
		for(int n = 0; n < piece.listPolymino.size(); n++)
		{
			SolveurPolymino polymino = piece.listPolymino.get(n);
			if(polymino.listCoordonneesPossible.size() > 0)
			{
				this.idPolymino = n;
				this.idCoordonnee = 0;
			}
		}
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
