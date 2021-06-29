import java.awt.image.BufferedImage;
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
		
		List<SolveurTypePossible.TypeCase> orderType = Arrays.asList(SolveurTypePossible.TypeCase.VIDE, SolveurTypePossible.TypeCase.TYPE1, SolveurTypePossible.TypeCase.TYPE2, SolveurTypePossible.TypeCase.TYPE3);

		for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
		{
			for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
			{
				SolveurTypePossible solveurTypePossible = new SolveurTypePossible(orderType);
				tableauTypePossible[x][y] = solveurTypePossible;
			}
		}
		
		int getNbItem = SolveurTypePossible.getNbItem(tableauTypePossible);
		int oldGetNbItem;

		do
		{
			oldGetNbItem = getNbItem;

			for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
			{
				for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
				{
					SolveurTypePossible solveurTypePossible = getSolveurTypePossibleFromIndicePicross(x, y, tableauTypePossible);
					tableauTypePossible[x][y] = solveurTypePossible;
				}
			}
			getNbItem = SolveurTypePossible.getNbItem(tableauTypePossible);
		} while(oldGetNbItem != getNbItem);
		
		exportGraphical(tableauTypePossible);
		System.out.println("NBItems : " + getNbItem);
	}
	
	private void exportGraphical(SolveurTypePossible[][] tableauTypePossible)
	{
		BufferedImage tableau = new BufferedImage(ConfigPartie.largeur_plateau * 200, ConfigPartie.largeur_plateau * 200, BufferedImage.TYPE_INT_ARGB);
		List<SolveurTypePossible.TypeCase> orderType = Arrays.asList(SolveurTypePossible.TypeCase.VIDE, SolveurTypePossible.TypeCase.TYPE1, SolveurTypePossible.TypeCase.TYPE2, SolveurTypePossible.TypeCase.TYPE3);
		List<BufferedImage> imageType = Arrays.asList(DrawTools.getImage("image/SolveurImgVide.png"), DrawTools.getImage("image/" + ConfigPartie.style + "Type1.png"), DrawTools.getImage("image/" + ConfigPartie.style + "Type2.png"), DrawTools.getImage("image/" + ConfigPartie.style + "Type3.png"), DrawTools.getImage("image/SolveurImgUnknow.png"));
		
		for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
		{
			for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
			{
				BufferedImage imageToDraw = imageType.get(4);
				if(tableauTypePossible[x][y].typePossibleList.size() == 1)
					imageToDraw = imageType.get(orderType.indexOf(tableauTypePossible[x][y].typePossibleList.get(0)));
				DrawTools.drawImageCenter(tableau, imageToDraw, x * 200 + 100, y * 200 + 100);
			}
		}
		DrawTools.saveFile(tableau, "Export/Solveur.png");
	}
	
	private SolveurTypePossible getSolveurTypePossibleFromIndicePicross(int x, int y, SolveurTypePossible[][] tableauTypePossible)
	{
		List<SolveurTypePossible.TypeCase> typePossibleList = new ArrayList<>();
		List<SolveurTypePossible.TypeCase> orderType = Arrays.asList(SolveurTypePossible.TypeCase.VIDE, SolveurTypePossible.TypeCase.TYPE1, SolveurTypePossible.TypeCase.TYPE2, SolveurTypePossible.TypeCase.TYPE3);

		int nbIndicesVert = probleme.ensembleIndices.indicePicrossListVertical.get(x).list_hauteurs.size();
		int nbIndicesHoriz = probleme.ensembleIndices.indicePicrossListHorizontal.get(y).list_hauteurs.size();
		
		if(nbIndicesVert == 0 || nbIndicesHoriz == 0)
		{
			typePossibleList.add(SolveurTypePossible.TypeCase.VIDE);
		}
		else
		{
			// Calcul des possibilités pour la colone
			List<SolveurTypePossible.TypeCase> typePossibleInOrder = new ArrayList<>();
			for(Integer hauteur : probleme.ensembleIndices.indicePicrossListVertical.get(x).list_hauteurs)
				typePossibleInOrder.add(orderType.get(hauteur));
	
			List<SolveurTypePossible> listPossiblesConnus = new ArrayList<>();
			for(int yy = 0; yy < ConfigPartie.hauteur_plateau; yy++)
				listPossiblesConnus.add(tableauTypePossible[x][yy]);
			List<SolveurTypePossible.TypeCase> typePossibleListVertical = getTypePossibleList(ConfigPartie.hauteur_plateau, typePossibleInOrder, y, listPossiblesConnus);
			
			// Calcul des possibilités pour la ligne
			typePossibleInOrder = new ArrayList<>();
			for(Integer hauteur : probleme.ensembleIndices.indicePicrossListHorizontal.get(y).list_hauteurs)
				typePossibleInOrder.add(orderType.get(hauteur));
	
			listPossiblesConnus = new ArrayList<>();
			for(int xx = 0; xx < ConfigPartie.largeur_plateau; xx++)
				listPossiblesConnus.add(tableauTypePossible[xx][y]);
			List<SolveurTypePossible.TypeCase> typePossibleListHorizontal = getTypePossibleList(ConfigPartie.largeur_plateau, typePossibleInOrder, x, listPossiblesConnus);
			
	/*		int wideIndice = ConfigPartie.hauteur_plateau - nbIndices + 1;
			for(int n = 0; n < ConfigPartie.hauteur_plateau - nbIndices; n++)
				typePossibleOrder.add(SolveurTypePossible.TypeCase.VIDE);
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
			}*/
			
			typePossibleList.addAll(typePossibleListVertical);
			typePossibleList.retainAll(typePossibleListHorizontal);
		}
		
		return(new SolveurTypePossible(typePossibleList));
	}
	
	private List<SolveurTypePossible.TypeCase> getTypePossibleList(int size, List<SolveurTypePossible.TypeCase> typePossibleInOrder, int xy, List<SolveurTypePossible> listPossiblesConnus)
	{
		List<SolveurTypePossible.TypeCase> typePossibleList = new ArrayList<>();
		List<List<SolveurTypePossible.TypeCase>> listAllPossibleDispositions =  getAllPossibleDispositions(size, typePossibleInOrder, xy, listPossiblesConnus);
		for(List<SolveurTypePossible.TypeCase> listPossibleDispositions : listAllPossibleDispositions)
		{
			SolveurTypePossible.TypeCase thisTypeCase = listPossibleDispositions.get(xy);
			if(!typePossibleList.contains(thisTypeCase))
				typePossibleList.add(thisTypeCase);
		}
		return(typePossibleList);
	}
	
	private List<List<SolveurTypePossible.TypeCase>> getAllPossibleDispositions(int size, List<SolveurTypePossible.TypeCase> typePossibleInOrder, int xy, List<SolveurTypePossible> listPossiblesConnus)
	{
		List<List<SolveurTypePossible.TypeCase>> listAllPossibleDispositions = new ArrayList<>();
		
		List<List<Integer>> listAllArrangements = getAllArrangements(size, typePossibleInOrder.size());
		
		for(List<Integer> listArrangements : listAllArrangements)
		{
			List<SolveurTypePossible.TypeCase> listPossibleDispositions = new ArrayList<>();
			for(int pos = 0; pos < size; pos++)
			{
				int type = listArrangements.indexOf(pos);
				SolveurTypePossible.TypeCase typeToAdd = SolveurTypePossible.TypeCase.VIDE;
				if(type != -1)
					typeToAdd = typePossibleInOrder.get(type);
				listPossibleDispositions.add(typeToAdd);
			}
			
			if(isThisListCompatible(listPossibleDispositions, listPossiblesConnus))
				listAllPossibleDispositions.add(listPossibleDispositions);
		}
		
		return(listAllPossibleDispositions);
	}
	
	private boolean isThisListCompatible(List<SolveurTypePossible.TypeCase> listPossibleDispositions, List<SolveurTypePossible> listPossiblesConnus)
	{
		for(int n = 0; n < listPossibleDispositions.size(); n++)
			if(!listPossiblesConnus.get(n).typePossibleList.contains(listPossibleDispositions.get(n)))
				return(false);
		
		return(true);
	}
	
	private List<List<Integer>> getAllArrangements(int size, int depth)
	{
		List<List<Integer>> listAllArangements = new ArrayList<List<Integer>>();
		
		recursiveGetAllArangements(listAllArangements, new ArrayList<Integer>(), size, depth);
		
		return(listAllArangements);
	}
	
	private void recursiveGetAllArangements(List<List<Integer>> listAllArangements, List<Integer> currentArrangement, int size, int depth)
	{
		if(currentArrangement.size() == depth)
		{
			List<Integer> arrangementToAdd = new ArrayList<>();
			arrangementToAdd.addAll(currentArrangement);
			listAllArangements.add(arrangementToAdd);
			return;
		}
		
		int numberToStart = 0;
		if(currentArrangement.size() > 0)
			numberToStart = currentArrangement.get(currentArrangement.size() - 1) + 1;
		for(int numberToAdd = numberToStart; numberToAdd < size; numberToAdd++)
		{
			currentArrangement.add(numberToAdd);
			recursiveGetAllArangements(listAllArangements, currentArrangement, size, depth);
			currentArrangement.remove((Integer)numberToAdd);
		}
	}
}
