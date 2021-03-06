import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Enum.TypeIndiceLigne;
import Enum.TypePolymino;

public class SolveurCoordonneesPossible
{
	Probleme probleme;
	public List<SolveurPiece> listPieces;
	public SolveurTypePossible[][] tableauTypePossible;
	
	public SolveurCoordonneesPossible(Probleme probleme, List<Piece> listPieces, SolveurTypePossible[][] tableauTypePossible)
	{
		this.probleme = probleme;
		this.listPieces = new ArrayList<>();
		for(Piece pieceToConvert : listPieces)
		{
			this.listPieces.add(new SolveurPiece(pieceToConvert));
		}
		
		// On supprimme les polyminos interdit par les indices
/*		for(SolveurPiece piece : this.listPieces)
		{
			for(int idPolymino = 0; idPolymino < piece.listPolymino.size(); idPolymino++)
			{
				SolveurPolymino polymino = piece.listPolymino.get(idPolymino);
				for(IndicePiece indicePiece : probleme.ensembleIndices.indicePieceList)
				{
					if(indicePiece.polymino.typePolymino == polymino.typePolymino)
					{
						switch(indicePiece.typeIndicePiece)
						{
						case FACE_CONNU:
							if(indicePiece.polymino.recto != polymino.recto)
							{
								piece.listPolymino.remove(idPolymino);
								idPolymino--;
								continue;
							}
							break;
						case EMPLACEMENT_CONNU:
							// Il faudra placer cette pi?ce en plus
						case ROTATION_CONNU:
							if(indicePiece.polymino.recto != polymino.recto || indicePiece.polymino.orientation != polymino.orientation)
							{
								piece.listPolymino.remove(idPolymino);
								idPolymino--;
								continue;
							}
							break;
						}
					}
				}
			}
		}*/
		
		this.tableauTypePossible = tableauTypePossible;
	}
	
	public void update()
	{
		for(SolveurPiece piece : listPieces)
		{
			for(SolveurPolymino polymino : piece.listPolymino)
			{
				for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
				{
					for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
					{
						if(isPolyminoPlacable(polymino, x, y))
						{
							boolean polyminoToIgnore = false;
							for(IndicePiece indicePiece : probleme.ensembleIndices.indicePieceList)
							{
								if(indicePiece.polymino.typePolymino == polymino.typePolymino)
								{
									switch(indicePiece.typeIndicePiece)
									{
									case EMPLACEMENT_CONNU:
										if(indicePiece.polymino.getMinX() != x || indicePiece.polymino.getMinY() != y)
											polyminoToIgnore = true;
									}
								}
							}
							if(!polyminoToIgnore)
								polymino.listCoordonneesPossible.add(new Point(x, y));
						}
					}
				}
			}
		}
	}
	
	public boolean isPolyminoPlacable(SolveurPolymino polymino, int x, int y)
	{

		for(Tuile tuile : polymino.tuileList)
		{
			if(tuile.coordonnee.x + x < ConfigPartie.largeur_plateau && tuile.coordonnee.y + y < ConfigPartie.hauteur_plateau)
			{
				if(!tableauTypePossible[tuile.coordonnee.x + x][tuile.coordonnee.y + y].typePossibleList.contains(SolveurTypePossible.orderType.get(tuile.getHauteur())))
					return(false);
			}
			else
				return(false);
		}
		return(true);
	}
	
	public int tryToSolve(boolean export)
	{
		// Converti la solution en SolveurPlacementPiece
		TypePolymino[][] tableauTypePolymino = new TypePolymino[ConfigPartie.largeur_plateau][ConfigPartie.hauteur_plateau];
		SolveurTypePossible.TypeCase[][] tableauTypeCase = new SolveurTypePossible.TypeCase[ConfigPartie.largeur_plateau][ConfigPartie.hauteur_plateau];

		for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
		{
			for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
			{
				tableauTypePolymino[x][y] = probleme.getTypePolyminoThisCase(x, y);
				tableauTypeCase[x][y] = SolveurTypePossible.orderType.get(probleme.getTypeCaseThisCase(x, y));
			}
		}
		
		int nbSolutions = 0;
		
		List<SolveurPlacementPiece> actualSolution = new ArrayList<>();
		for(SolveurPiece piece : listPieces)
		{
			actualSolution.add(new SolveurPlacementPiece(piece, 0, 0));
		}
		
		boolean allCaseOK = true;
		do
		{
			if(check(actualSolution))
			{
//				System.out.println("Une disposition correcte !!! (sans chevauchement)");
				if(checkFinal(actualSolution, tableauTypePolymino, tableauTypeCase))
				{
//					System.out.println("Une disposition correcte totale !!!");
					if(export)
						exportGraphicalSolution(actualSolution, nbSolutions);
					nbSolutions++;
				}
			}
			
			allCaseOK = true;
			for(int n = actualSolution.size() - 1; n >= 0; n--)
			{
				if(actualSolution.get(n).getActualPolymino().listCoordonneesPossible.size() <= actualSolution.get(n).idCoordonnee + 1)
				{
					actualSolution.get(n).idCoordonnee = 0;
					if(actualSolution.get(n).piece.listPolymino.size() > actualSolution.get(n).idPolymino + 1)
					{
						actualSolution.get(n).idPolymino++;
						allCaseOK = false;
						break;
					}
					else
					{
						actualSolution.get(n).idPolymino = 0;
						continue;
					}
				}
				else
				{
					actualSolution.get(n).idCoordonnee++;
					allCaseOK = false;
					break;
				}
			}
		} while(!allCaseOK);
		
		return(nbSolutions);
	}
	
	private void exportGraphicalSolution(List<SolveurPlacementPiece> actualSolution, int id)
	{
		BufferedImage tableau = new BufferedImage(ConfigPartie.largeur_plateau * 200, ConfigPartie.largeur_plateau * 200, BufferedImage.TYPE_INT_ARGB);
		List<BufferedImage> imageType = Arrays.asList(DrawTools.getImage("image/SolveurImgVide.png"), DrawTools.getImage("image/" + ConfigPartie.style + "Type1.png"), DrawTools.getImage("image/" + ConfigPartie.style + "Type2.png"), DrawTools.getImage("image/" + ConfigPartie.style + "Type3.png"), DrawTools.getImage("image/SolveurImgUnknow.png"));
		
		for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
		{
			for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
			{
				BufferedImage imageToDraw = imageType.get(0);
				SolveurTypePossible.TypeCase type = getTypeThisCase(actualSolution, x, y);
				if(type != null)
					imageToDraw = imageType.get(SolveurTypePossible.orderType.indexOf(type));
				DrawTools.drawImageCenter(tableau, imageToDraw, x * 200 + 100, y * 200 + 100);
				DrawTools.drawText(tableau, "" + getIDPolyminoThisCase(actualSolution, x, y), x * 200 + 100, y * 200 + 100, "Arial", Color.RED, 100, 0);
			}
		}
		DrawTools.saveFile(tableau, "Export/Solution" + id + ".png");
	}
	
	public boolean check(List<SolveurPlacementPiece> actualSolution)
	{
		for(int polymino1 = 0; polymino1 < actualSolution.size() - 1; polymino1++)
		{
			for(int polymino2 = polymino1 + 1; polymino2 < actualSolution.size(); polymino2++)
			{
				if(actualSolution.get(polymino1).getActualPolymino().listCoordonneesPossible.size() == 0 || actualSolution.get(polymino2).getActualPolymino().listCoordonneesPossible.size() == 0)
					return(false);
				for(Point point1 : actualSolution.get(polymino1).getActualCoordonnees())
				{
					for(Point point2 : actualSolution.get(polymino2).getActualCoordonnees())
					{
						if(point1.equals(point2))
							return(false);
					}
				}
			}
		}
		return(true);
	}
	
	public boolean checkFinal(List<SolveurPlacementPiece> actualSolution, TypePolymino[][] tableauTypePolymino, SolveurTypePossible.TypeCase[][] tableauTypeCase)
	{
		// Converti la solution en SolveurPlacementPiece
		TypePolymino[][] tableauTypePolyminoActual = new TypePolymino[ConfigPartie.largeur_plateau][ConfigPartie.hauteur_plateau];
		SolveurTypePossible.TypeCase[][] tableauTypeCaseActual = new SolveurTypePossible.TypeCase[ConfigPartie.largeur_plateau][ConfigPartie.hauteur_plateau];

		boolean sameSolution = true;
		for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
		{
			if(!sameSolution)
				break;
			for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
			{
				tableauTypePolyminoActual[x][y] = getTypePolyminoThisCase(actualSolution, x, y);
				tableauTypeCaseActual[x][y] = SolveurTypePossible.orderType.get(getTypeCaseThisCase(actualSolution, x, y));
				if(tableauTypePolyminoActual[x][y] != tableauTypePolymino[x][y] || tableauTypeCaseActual[x][y] != tableauTypeCase[x][y])
				{
					sameSolution = false;
					break;
				}
			}
		}
		if(sameSolution)
			return(false);

		// check des indices picross verticaux
		for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
		{
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
				continue;
			List<SolveurTypePossible.TypeCase> typePossibleInOrder = new ArrayList<>();
			for(Integer hauteur : probleme.ensembleIndices.indicePicrossListVertical.get(x).list_hauteurs)
				typePossibleInOrder.add(SolveurTypePossible.orderType.get(hauteur));
			List<SolveurTypePossible.TypeCase> typeThisRow = new ArrayList<>();
			for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
			{
				SolveurTypePossible.TypeCase typeCase = getTypeThisCase(actualSolution, x, y);
				if(typeCase != null)
				{
					typeThisRow.add(typeCase);
				}
			}
			
			if(typePossibleInOrder.size() != typeThisRow.size())
				return(false);
			
			for(int n = 0; n < typePossibleInOrder.size(); n++)
				if(typePossibleInOrder.get(n) != typeThisRow.get(n))
					return(false);
		}

		// check des indices picross horizontaux
		for(int y = 0; y < ConfigPartie.hauteur_plateau; y++)
		{
        	boolean ligneToIgnore = false;
			for(IndiceLigne indiceLigne : probleme.ensembleIndices.indiceLigneListHorizontal)
			{
        		if(indiceLigne.ligne == y && indiceLigne.typeIndiceLigne == TypeIndiceLigne.INDICES_PICROSS_HIDDEN)
        		{
        			ligneToIgnore = true;
        			break;
        		}
			}
			if(ligneToIgnore)
				continue;
			List<SolveurTypePossible.TypeCase> typePossibleInOrder = new ArrayList<>();
			for(Integer hauteur : probleme.ensembleIndices.indicePicrossListHorizontal.get(y).list_hauteurs)
				typePossibleInOrder.add(SolveurTypePossible.orderType.get(hauteur));
			List<SolveurTypePossible.TypeCase> typeThisRow = new ArrayList<>();
			for(int x = 0; x < ConfigPartie.largeur_plateau; x++)
			{
				SolveurTypePossible.TypeCase typeCase = getTypeThisCase(actualSolution, x, y);
				if(typeCase != null)
				{
					typeThisRow.add(typeCase);
				}
			}
			
			if(typePossibleInOrder.size() != typeThisRow.size())
				return(false);
			
			for(int n = 0; n < typePossibleInOrder.size(); n++)
				if(typePossibleInOrder.get(n) != typeThisRow.get(n))
					return(false);
		}
		return(true);
	}
	
	public SolveurTypePossible.TypeCase getTypeThisCase(List<SolveurPlacementPiece> actualSolution, int x, int y)
	{
		for(SolveurPlacementPiece piece : actualSolution)
		{
			Point actualCoord = piece.getActualCoordonnee();
			for(Tuile tuile : piece.getActualPolymino().tuileList)
			{
				if(x == tuile.coordonnee.x + actualCoord.x && y == tuile.coordonnee.y + actualCoord.y)
				{
					return(SolveurTypePossible.orderType.get(tuile.getHauteur()));
				}
			}
		}
		return(null);
	}

	public TypePolymino getTypePolyminoThisCase(List<SolveurPlacementPiece> actualSolution, int x, int y)
	{
		for(SolveurPlacementPiece piece : actualSolution)
		{
			Point actualCoord = piece.getActualCoordonnee();
			for(Tuile tuile : piece.getActualPolymino().tuileList)
			{
				if(x == tuile.coordonnee.x + actualCoord.x && y == tuile.coordonnee.y + actualCoord.y)
				{
					return(piece.getActualPolymino().typePolymino);
				}
			}
		}
		return(null);
	}

	public int getIDPolyminoThisCase(List<SolveurPlacementPiece> actualSolution, int x, int y)
	{
		for(SolveurPlacementPiece piece : actualSolution)
		{
			Point actualCoord = piece.getActualCoordonnee();
			for(Tuile tuile : piece.getActualPolymino().tuileList)
			{
				if(x == tuile.coordonnee.x + actualCoord.x && y == tuile.coordonnee.y + actualCoord.y)
				{
					return(actualSolution.indexOf(piece));
				}
			}
		}
		return(-1);
	}
	
    public int getTypeCaseThisCase(List<SolveurPlacementPiece> actualSolution, int x, int y)
    {
		for(SolveurPlacementPiece piece : actualSolution)
		{
			Point actualCoord = piece.getActualCoordonnee();
			for(Tuile tuile : piece.getActualPolymino().tuileList)
			{
				if(x == tuile.coordonnee.x + actualCoord.x && y == tuile.coordonnee.y + actualCoord.y)
				{
					return(tuile.getHauteur());
				}
			}
		}
		return(0);
    }
}
