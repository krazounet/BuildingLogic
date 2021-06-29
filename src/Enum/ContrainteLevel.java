package Enum;

public enum ContrainteLevel
{
	NB_PIECES_2(2),
	NB_PIECES_3(3),
	NB_PIECES_4(4),
	NB_PIECES_5(5),
	NB_PIECES_6(6),
	NB_PIECES_7(7),
	NB_PIECES_8(8),
	NB_PIECES_RECTO_2(2),
	NB_PIECES_RECTO_3(3),
	NB_PIECES_RECTO_4(4),
	NB_PIECES_RECTO_5(5),
	PIECE_PRESENTE_I(TypePolymino.I),
	PIECE_PRESENTE_O(TypePolymino.O),
	PIECE_PRESENTE_T(TypePolymino.T),
	PIECE_PRESENTE_L(TypePolymino.L),
	PIECE_PRESENTE_S(TypePolymino.S);

	public int value;
	public TypePolymino typePolymino;
	ContrainteLevel (int v){
		this.value=v;
	}
	ContrainteLevel (){
		this.value=0;
		this.typePolymino=null;
	}
	ContrainteLevel (TypePolymino type){
		this.typePolymino=type;
	}
}
