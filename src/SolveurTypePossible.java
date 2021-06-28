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

	public List<TypeCase> typePossibleList;
	
	public SolveurTypePossible(List<TypeCase> typePossibleList)
	{
		this.typePossibleList = typePossibleList;
	}
	
}
