import java.util.ArrayList;
import java.util.List;
import Enum.*;

//une piece est un ensemble de positionnement du polymino
public class Piece {
    List<Polymino> polyminoList;

    public Piece(List<Polymino> polyminoList) {
        this.polyminoList = polyminoList;
    }

    public static Piece O = getO();
    public static Piece L = getL();

    private static Piece getL() {
        List<Polymino> polyminoList =new ArrayList<>();
        List<Tuile> tuileList=new ArrayList<>();
        tuileList.add(new Tuile(3,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,1)));
        tuileList.add(new Tuile(1,new Coordonnee(0,2)));
        polyminoList.add(new Polymino(tuileList,3,2, TypePolymino.L,0,true));

        tuileList.clear();
        tuileList.add(new Tuile(3,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,-1)));
        tuileList.add(new Tuile(1,new Coordonnee(2,0)));
        polyminoList.add(new Polymino(tuileList,2,3, TypePolymino.L,1,true));

        tuileList.clear();
        tuileList.add(new Tuile(3,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(-1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,-1)));
        tuileList.add(new Tuile(1,new Coordonnee(0,-2)));
        polyminoList.add(new Polymino(tuileList,3,2, TypePolymino.L,2,true));

        tuileList.clear();
        tuileList.add(new Tuile(3,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(-1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,1)));
        tuileList.add(new Tuile(1,new Coordonnee(-2,0)));
        polyminoList.add(new Polymino(tuileList,3,2, TypePolymino.L,2,true));


        return new Piece(polyminoList);
    }

    private static Piece getO() {
        List<Polymino> polyminoList =new ArrayList<>();
        List<Tuile> tuileList=new ArrayList<>();
        tuileList.add(new Tuile(1,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,1)));
        tuileList.add(new Tuile(1,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2, TypePolymino.O,0,true));

        tuileList.clear();
        tuileList.add(new Tuile(2,new Coordonnee(0,0)));
        tuileList.add(new Tuile(1,new Coordonnee(1,0)));
        tuileList.add(new Tuile(1,new Coordonnee(0,1)));
        tuileList.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2, TypePolymino.O,1,true));

        tuileList.clear();
        tuileList.add(new Tuile(1,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,1)));
        tuileList.add(new Tuile(3,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2, TypePolymino.O,0,false));

        tuileList.clear();
        tuileList.add(new Tuile(2,new Coordonnee(0,0)));
        tuileList.add(new Tuile(1,new Coordonnee(1,0)));
        tuileList.add(new Tuile(3,new Coordonnee(0,1)));
        tuileList.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2, TypePolymino.O,1,false));

        tuileList.clear();
        tuileList.add(new Tuile(3,new Coordonnee(0,0)));
        tuileList.add(new Tuile(2,new Coordonnee(1,0)));
        tuileList.add(new Tuile(2,new Coordonnee(0,1)));
        tuileList.add(new Tuile(1,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2, TypePolymino.O,2,false));

        tuileList.clear();
        tuileList.add(new Tuile(2,new Coordonnee(0,0)));
        tuileList.add(new Tuile(3,new Coordonnee(1,0)));
        tuileList.add(new Tuile(1,new Coordonnee(0,1)));
        tuileList.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileList,2,2, TypePolymino.O,3,false));

        return new Piece(polyminoList);

    }
}
