import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Enum.TypeIndiceCase;
import Enum.TypeIndiceLigne;
import Enum.TypePolymino;

public class Solveur
{
	Probleme probleme;
	SolveurTypePossible[][] tableauTypePossible;
	
	public Solveur(Probleme probleme)
	{
		this.probleme = probleme;
		tableauTypePossible = new SolveurTypePossible[ConfigPartie.largeur_plateau][ConfigPartie.hauteur_plateau];
	}
	
	public int analyse(boolean export)
	{
		
		for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
		{
			for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
			{
            	boolean caseVide = false;
            	for(IndiceCase indiceCase : probleme.ensembleIndices.indiceCaseList)
            	{
            		if(indiceCase.typeIndiceCase == TypeIndiceCase.VIDE)
            		{
            			if(indiceCase.coord.x == x && indiceCase.coord.y == y)
            				caseVide = true;
            		}
            	}
            	if(caseVide)
            	{
					SolveurTypePossible solveurTypePossible = new SolveurTypePossible(Arrays.asList(SolveurTypePossible.TypeCase.VIDE));
					tableauTypePossible[x][y] = solveurTypePossible;
            	}
            	else
            	{
					SolveurTypePossible solveurTypePossible = new SolveurTypePossible(SolveurTypePossible.orderType);
					tableauTypePossible[x][y] = solveurTypePossible;
            	}
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
					SolveurTypePossible solveurTypePossible = getSolveurTypePossibleFromIndicePicross(x, y);
					tableauTypePossible[x][y] = solveurTypePossible;
				}
			}
			getNbItem = SolveurTypePossible.getNbItem(tableauTypePossible);
		} while(oldGetNbItem != getNbItem);
		
		if(export)
			exportGraphical();
		
		List<Piece> listPieces = Probleme.getListPieceFromList(probleme.Liste_polyminos_utilises, probleme.ensembleIndices);
		SolveurCoordonneesPossible coordPossible = new SolveurCoordonneesPossible(probleme, listPieces, tableauTypePossible);
		coordPossible.update();
		for(SolveurPiece piece : coordPossible.listPieces)
		{
			for(int n = 0; n < piece.listPolymino.size(); n++)
			{
				SolveurPolymino polymino = piece.listPolymino.get(n);
				if(polymino.listCoordonneesPossible.size() == 0)
				{
					piece.listPolymino.remove(polymino);
					n--;
				}
			}
		}
		for(SolveurPiece piece : coordPossible.listPieces)
		{
			System.out.println("Nombre de positions possibles pour la pièce '" + piece.listPolymino.get(0).typePolymino + "' : " + piece.getNbPositionsPossible());
		}
		
		int nbSolutions = coordPossible.tryToSolve(export);
		return(nbSolutions);
	}
	
	private void exportGraphical()
	{
		BufferedImage tableau = new BufferedImage(ConfigPartie.largeur_plateau * 200, ConfigPartie.largeur_plateau * 200, BufferedImage.TYPE_INT_ARGB);
		List<BufferedImage> imageType = Arrays.asList(DrawTools.getImage("image/SolveurImgVide.png"), DrawTools.getImage("image/" + ConfigPartie.style + "Type1.png"), DrawTools.getImage("image/" + ConfigPartie.style + "Type2.png"), DrawTools.getImage("image/" + ConfigPartie.style + "Type3.png"), DrawTools.getImage("image/SolveurImgUnknow.png"));
		
		for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
		{
			for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
			{
				BufferedImage imageToDraw = imageType.get(4);
				if(tableauTypePossible[x][y].typePossibleList.size() == 1)
					imageToDraw = imageType.get(SolveurTypePossible.orderType.indexOf(tableauTypePossible[x][y].typePossibleList.get(0)));
				DrawTools.drawImageCenter(tableau, imageToDraw, x * 200 + 100, y * 200 + 100);
			}
		}
		DrawTools.saveFile(tableau, "Export/Solveur.png");
	}
	
	private SolveurTypePossible getSolveurTypePossibleFromIndicePicross(int x, int y)
	{
		List<SolveurTypePossible.TypeCase> typePossibleList = new ArrayList<>();

		int nbIndicesVert = probleme.ensembleIndices.indicePicrossListVertical.get(x).list_hauteurs.size();
		int nbIndicesHoriz = probleme.ensembleIndices.indicePicrossListHorizontal.get(y).list_hauteurs.size();
		
		if(nbIndicesVert == 0 || nbIndicesHoriz == 0)
		{
			typePossibleList.add(SolveurTypePossible.TypeCase.VIDE);
		}
		else
		{
			// Calcul des possibilités pour la colone
			List<SolveurTypePossible.TypeCase> typePossibleListVertical;
			
        	boolean ligneToIgnore = false;
			for(IndiceLigne indiceLigne : probleme.ensembleIndices.indiceLigneListVertical)
			{
        		if(indiceLigne.ligne == x && indiceLigne.typeIndiceLigne == TypeIndiceLigne.INDICES_PICROSS_HIDDEN)
        		{
        			ligneToIgnore = true;
        			break;
        		}
			}
			if(ligneToIgnore)
			{
				typePossibleListVertical = SolveurTypePossible.orderType;
			}
			else
			{
				List<SolveurTypePossible.TypeCase> typePossibleInOrder = new ArrayList<>();
				for(Integer hauteur : probleme.ensembleIndices.indicePicrossListVertical.get(x).list_hauteurs)
					typePossibleInOrder.add(SolveurTypePossible.orderType.get(hauteur));
		
				List<SolveurTypePossible> listPossiblesConnus = new ArrayList<>();
				for(int yy = 0; yy < ConfigPartie.hauteur_plateau; yy++)
					listPossiblesConnus.add(tableauTypePossible[x][yy]);
				typePossibleListVertical = getTypePossibleList(ConfigPartie.hauteur_plateau, typePossibleInOrder, y, listPossiblesConnus);
			}
			
			// Calcul des possibilités pour la ligne
			List<SolveurTypePossible.TypeCase> typePossibleListHorizontal;
			
        	ligneToIgnore = false;
			for(IndiceLigne indiceLigne : probleme.ensembleIndices.indiceLigneListHorizontal)
			{
        		if(indiceLigne.ligne == y && indiceLigne.typeIndiceLigne == TypeIndiceLigne.INDICES_PICROSS_HIDDEN)
        		{
        			ligneToIgnore = true;
        			break;
        		}
			}
			if(ligneToIgnore)
			{
				typePossibleListHorizontal = SolveurTypePossible.orderType;
			}
			else
			{
				List<SolveurTypePossible.TypeCase> typePossibleInOrder = new ArrayList<>();
				for(Integer hauteur : probleme.ensembleIndices.indicePicrossListHorizontal.get(y).list_hauteurs)
					typePossibleInOrder.add(SolveurTypePossible.orderType.get(hauteur));
		
				List<SolveurTypePossible> listPossiblesConnus = new ArrayList<>();
				for(int xx = 0; xx < ConfigPartie.largeur_plateau; xx++)
					listPossiblesConnus.add(tableauTypePossible[xx][y]);
				typePossibleListHorizontal = getTypePossibleList(ConfigPartie.largeur_plateau, typePossibleInOrder, x, listPossiblesConnus);
			}
			
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
