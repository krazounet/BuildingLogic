import java.util.Arrays;
import java.util.List;

public class SolveurTypePossible
{
	enum TypeCase
	{
		VIDE,
		TYPE1,
		TYPE2,
		TYPE3
	}

	public static List<TypeCase> orderType = Arrays.asList(TypeCase.VIDE, TypeCase.TYPE1, TypeCase.TYPE2, TypeCase.TYPE3);

	public List<TypeCase> typePossibleList;
	
	public SolveurTypePossible(List<TypeCase> typePossibleList)
	{
		this.typePossibleList = typePossibleList;
	}
	
	public static int getNbItem(SolveurTypePossible[][] tableauTypePossible)
	{
		int nbItem = 0;
		for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
		{
			for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
			{
				nbItem += tableauTypePossible[x][y].typePossibleList.size();
			}
		}
		return(nbItem);
	}
	
}
