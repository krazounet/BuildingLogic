import java.util.ArrayList;
import java.util.List;

public class SolveurPiece
{
	List<SolveurPolymino> listPolymino;
	
	public SolveurPiece(Piece piece)
	{
		listPolymino = new ArrayList<>();
		for(Polymino polymino : piece.polyminoList)
		{
			listPolymino.add(new SolveurPolymino(polymino.tuileList, polymino.typePolymino, polymino.orientation, polymino.recto));
		}
	}
	
	public int getNbPositionsPossible()
	{
		int nbPositionsPossible = 0;
		for(SolveurPolymino polymino : listPolymino)
			nbPositionsPossible += polymino.getNbPositionPossible();
		return(nbPositionsPossible);
	}
}
