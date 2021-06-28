import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solveur
{
	Probleme probleme;
	
	public Solveur(Probleme probleme)
	{
		this.probleme = probleme;
	}
	
	public void analyse()
	{
		SolveurTypePossible[][] tableauTypePossible = new SolveurTypePossible[ConfigPartie.largeur_plateau][ConfigPartie.hauteur_plateau];
		
		for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
		{
			for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
			{
				SolveurTypePossible solveurTypePossible = getSolveurTypePossibleFromIndicePicross(x, y);
				tableauTypePossible[x][y] = solveurTypePossible;
			}
		}
	}
	
	private SolveurTypePossible getSolveurTypePossibleFromIndicePicross(int x, int y)
	{
		List<SolveurTypePossible.TypeCase> typePossibleList = new ArrayList<>();
		List<SolveurTypePossible.TypeCase> orderType = Arrays.asList(SolveurTypePossible.TypeCase.VIDE, SolveurTypePossible.TypeCase.TYPE1, SolveurTypePossible.TypeCase.TYPE2, SolveurTypePossible.TypeCase.TYPE3);
		
		// Calcul des possibilités pour la colone
		List<SolveurTypePossible.TypeCase> typePossibleListVertical = new ArrayList<>();
		int nbIndices = probleme.ensembleIndices.indicePicrossListVertical.get(x).list_hauteurs.size();
		int wideIndice = ConfigPartie.hauteur_plateau - nbIndices + 1;
		List<SolveurTypePossible.TypeCase> typePossibleOrder = new ArrayList<>();
		for(int n = 0; n < ConfigPartie.hauteur_plateau - nbIndices; n++)
			typePossibleOrder.add(SolveurTypePossible.TypeCase.VIDE);
		for(Integer hauteur : probleme.ensembleIndices.indicePicrossListVertical.get(x).list_hauteurs)
			typePossibleOrder.add(orderType.get(hauteur));
		for(int n = 0; n < ConfigPartie.hauteur_plateau - nbIndices; n++)
			typePossibleOrder.add(SolveurTypePossible.TypeCase.VIDE);
		
		for(int n = 0; n < wideIndice; n++)
		{
			if(!typePossibleListVertical.contains(typePossibleOrder.get(y + n)))
				typePossibleListVertical.add(typePossibleOrder.get(y + n));
		}
		
		// Calcul des possibilités pour la ligne
		List<SolveurTypePossible.TypeCase> typePossibleListHorizontal = new ArrayList<>();
		nbIndices = probleme.ensembleIndices.indicePicrossListHorizontal.get(y).list_hauteurs.size();
		wideIndice = ConfigPartie.largeur_plateau - nbIndices + 1;
		typePossibleOrder = new ArrayList<>();
		for(int n = 0; n < ConfigPartie.largeur_plateau - nbIndices; n++)
			typePossibleOrder.add(SolveurTypePossible.TypeCase.VIDE);
		for(Integer hauteur : probleme.ensembleIndices.indicePicrossListHorizontal.get(y).list_hauteurs)
			typePossibleOrder.add(orderType.get(hauteur));
		for(int n = 0; n < ConfigPartie.largeur_plateau - nbIndices; n++)
			typePossibleOrder.add(SolveurTypePossible.TypeCase.VIDE);
		
		for(int n = 0; n < wideIndice; n++)
		{
			if(!typePossibleListHorizontal.contains(typePossibleOrder.get(x + n)))
				typePossibleListHorizontal.add(typePossibleOrder.get(x + n));
		}
		
		typePossibleList.addAll(typePossibleListVertical);
		typePossibleList.retainAll(typePossibleListHorizontal);
		
		return(new SolveurTypePossible(typePossibleList));
	}
}
