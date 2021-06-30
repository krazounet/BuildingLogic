package Enum;

public enum ContrainteLevel
{
	NB_PIECES_PRESENTE_1(1),
	NB_PIECES_PRESENTE_2(2),
	NB_PIECES_PRESENTE_3(3),
	NB_PIECES_PRESENTE_4(4),
	NB_PIECES_PRESENTE_5(5),
	NB_PIECES_PRESENTE_6(6),
	NB_PIECES_PRESENTE_7(7),
	NB_PIECES_PRESENTE_8(8),
	NB_PIECES_PRESENTE_9(9),
	NB_PIECES_PRESENTE_10(10),
	NB_RECTO_0(0),
	NB_RECTO_1(1),
	NB_RECTO_2(2),
	NB_RECTO_3(3),
	NB_RECTO_4(4),
	NB_RECTO_5(5),
	NB_RECTO_6(6),
	NB_RECTO_7(7),
	NB_RECTO_8(8),
	NB_RECTO_9(9),
	NB_RECTO_10(10),
	PIECE_PRESENTE_I4(TypePolymino.I4),
	PIECE_PRESENTE_I3(TypePolymino.I3),
	PIECE_PRESENTE_I2(TypePolymino.I2),
	PIECE_PRESENTE_O(TypePolymino.O),
	PIECE_PRESENTE_T(TypePolymino.T),
	PIECE_PRESENTE_L(TypePolymino.L),
	PIECE_PRESENTE_J(TypePolymino.J),
	PIECE_PRESENTE_S(TypePolymino.S),
	PIECE_PRESENTE_Z(TypePolymino.Z),
	PIECE_PRESENTE_V(TypePolymino.V);

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
