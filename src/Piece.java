import java.util.ArrayList;
import java.util.List;
import Enum.*;

//une piece est un ensemble de positionnement du polymino
public class Piece {
    List<Polymino> polyminoList;

    public Piece(List<Polymino> polyminoList) {
        this.polyminoList = polyminoList;
    }
    public Piece(Piece piece_to_clone) {
        this.polyminoList = piece_to_clone.polyminoList;
    }
    public Piece(TypePolymino tp) {
    	this.polyminoList = getPiece(tp).polyminoList;
    }

	private static Piece getPiece(TypePolymino typePolymino)
	{
        List<Tuile> tuileList=new ArrayList<>();

        switch(ConfigPartie.modele_repartition)
        {
        case 1:
			switch (typePolymino) {
				case I -> {
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(0, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(3, 2), new Coordonnee(0, 1), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 2), new Coordonnee(0, 2), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 1), new Coordonnee(0, 3), true));
				}
				case L -> {
					tuileList.add(new Tuile(new HauteurTuile(3, 2), new Coordonnee(0, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(1, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(0, 1), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 2), new Coordonnee(0, 2), true));
				}
				case O -> {
					tuileList.add(new Tuile(new HauteurTuile(1, 1), new Coordonnee(0, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 2), new Coordonnee(1, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 2), new Coordonnee(0, 1), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 3), new Coordonnee(1, 1), true));
				}
				case S -> {
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(0, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(3, 2), new Coordonnee(1, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 2), new Coordonnee(1, 1), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 1), new Coordonnee(2, 1), true));
				}
				case T -> {
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(0, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(3, 2), new Coordonnee(1, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 2), new Coordonnee(1, 1), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(2, 0), true));
				}
			}
			break;
        case 2:
			switch (typePolymino) {
				case I -> {
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(0, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(3, 2), new Coordonnee(0, 1), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 2), new Coordonnee(0, 2), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 1), new Coordonnee(0, 3), true));
				}
				case L -> {
					tuileList.add(new Tuile(new HauteurTuile(3, 2), new Coordonnee(0, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(1, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(0, 1), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 2), new Coordonnee(0, 2), true));
				}
				case O -> {
					tuileList.add(new Tuile(new HauteurTuile(3, 1), new Coordonnee(0, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 2), new Coordonnee(1, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 2), new Coordonnee(0, 1), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 3), new Coordonnee(1, 1), true));
				}
				case S -> {
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(0, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(3, 2), new Coordonnee(1, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 2), new Coordonnee(1, 1), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 1), new Coordonnee(2, 1), true));
				}
				case T -> {
					tuileList.add(new Tuile(new HauteurTuile(3, 1), new Coordonnee(0, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 2), new Coordonnee(1, 0), true));
					tuileList.add(new Tuile(new HauteurTuile(1, 2), new Coordonnee(1, 1), true));
					tuileList.add(new Tuile(new HauteurTuile(2, 1), new Coordonnee(2, 0), true));
				}
			}
			break;
        }

		if(ConfigPartie.same_rectoverso)
		{
			for(Tuile tuileToModif : tuileList)
			{
				tuileToModif.hauteur.hauteurVerso = tuileToModif.hauteur.hauteurRecto;
			}
		}
		
        Polymino polyminoModele = new Polymino(tuileList,typePolymino,0,true);
		return(new Piece(Polymino.getFullListPolymino(polyminoModele)));
	}
	

}
