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
        List<Tuile> tuileListl0t=new ArrayList<>();
        tuileListl0t.add(new Tuile(3,new Coordonnee(0,0)));
        tuileListl0t.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListl0t.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListl0t.add(new Tuile(1,new Coordonnee(0,2)));
        polyminoList.add(new Polymino(tuileListl0t,3,2, TypePolymino.L,0,true));

        List<Tuile> tuileListl1t=new ArrayList<>();
        tuileListl1t.add(new Tuile(3,new Coordonnee(0,1)));
        tuileListl1t.add(new Tuile(2,new Coordonnee(1,1)));
        tuileListl1t.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListl1t.add(new Tuile(1,new Coordonnee(2,1)));
        polyminoList.add(new Polymino(tuileListl1t,2,3, TypePolymino.L,1,true));

        List<Tuile> tuileListl2t=new ArrayList<>();
        tuileListl2t.add(new Tuile(3,new Coordonnee(1,2)));
        tuileListl2t.add(new Tuile(2,new Coordonnee(0,2)));
        tuileListl2t.add(new Tuile(2,new Coordonnee(1,1)));
        tuileListl2t.add(new Tuile(1,new Coordonnee(1,0)));
        polyminoList.add(new Polymino(tuileListl2t,3,2, TypePolymino.L,2,true));

        List<Tuile> tuileListl3t=new ArrayList<>();
        tuileListl3t.add(new Tuile(3,new Coordonnee(2,0)));
        tuileListl3t.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListl3t.add(new Tuile(2,new Coordonnee(2,1)));
        tuileListl3t.add(new Tuile(1,new Coordonnee(0,0)));
        polyminoList.add(new Polymino(tuileListl3t,2,3, TypePolymino.L,3,true));

        List<Tuile> tuileListl0f=new ArrayList<>();
        tuileListl0f.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListl0f.add(new Tuile(1,new Coordonnee(1,0)));
        tuileListl0f.add(new Tuile(1,new Coordonnee(0,1)));
        tuileListl0f.add(new Tuile(2,new Coordonnee(0,2)));
        polyminoList.add(new Polymino(tuileListl0f,3,2, TypePolymino.L,0,false));

        List<Tuile> tuileListl1f=new ArrayList<>();
        tuileListl1f.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListl1f.add(new Tuile(1,new Coordonnee(1,1)));
        tuileListl1f.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListl1f.add(new Tuile(2,new Coordonnee(2,1)));
        polyminoList.add(new Polymino(tuileListl1f,2,3, TypePolymino.L,1,false));

        List<Tuile> tuileListl2f=new ArrayList<>();
        tuileListl2f.add(new Tuile(2,new Coordonnee(1,2)));
        tuileListl2f.add(new Tuile(1,new Coordonnee(0,2)));
        tuileListl2f.add(new Tuile(1,new Coordonnee(1,1)));
        tuileListl2f.add(new Tuile(2,new Coordonnee(1,0)));
        polyminoList.add(new Polymino(tuileListl2f,3,2, TypePolymino.L,2,false));

        List<Tuile> tuileListl3f=new ArrayList<>();
        tuileListl3f.add(new Tuile(3,new Coordonnee(2,0)));
        tuileListl3f.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListl3f.add(new Tuile(2,new Coordonnee(2,1)));
        tuileListl3f.add(new Tuile(1,new Coordonnee(0,0)));
        polyminoList.add(new Polymino(tuileListl3f,2,3, TypePolymino.L,3,false));

        return new Piece(polyminoList);
    }

    private static Piece getO() {
        List<Polymino> polyminoList =new ArrayList<>();
        List<Tuile> tuileListO0t=new ArrayList<>();
        tuileListO0t.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListO0t.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListO0t.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListO0t.add(new Tuile(1,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO0t,2,2, TypePolymino.O,0,true));

        List<Tuile> tuileListO1t=new ArrayList<>();
        tuileListO1t.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListO1t.add(new Tuile(1,new Coordonnee(1,0)));
        tuileListO1t.add(new Tuile(1,new Coordonnee(0,1)));
        tuileListO1t.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO1t,2,2, TypePolymino.O,1,true));

        List<Tuile> tuileListO0f=new ArrayList<>();
        tuileListO0f.add(new Tuile(1,new Coordonnee(0,0)));
        tuileListO0f.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListO0f.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListO0f.add(new Tuile(3,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO0f,2,2, TypePolymino.O,0,false));

        List<Tuile> tuileListO1f=new ArrayList<>();
        tuileListO1f.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListO1f.add(new Tuile(1,new Coordonnee(1,0)));
        tuileListO1f.add(new Tuile(3,new Coordonnee(0,1)));
        tuileListO1f.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO1f,2,2, TypePolymino.O,1,false));

        List<Tuile> tuileListO2f=new ArrayList<>();
        tuileListO2f.add(new Tuile(3,new Coordonnee(0,0)));
        tuileListO2f.add(new Tuile(2,new Coordonnee(1,0)));
        tuileListO2f.add(new Tuile(2,new Coordonnee(0,1)));
        tuileListO2f.add(new Tuile(1,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO2f,2,2, TypePolymino.O,2,false));

        List<Tuile> tuileListO3f=new ArrayList<>();
        tuileListO3f.add(new Tuile(2,new Coordonnee(0,0)));
        tuileListO3f.add(new Tuile(3,new Coordonnee(1,0)));
        tuileListO3f.add(new Tuile(1,new Coordonnee(0,1)));
        tuileListO3f.add(new Tuile(2,new Coordonnee(1,1)));
        polyminoList.add(new Polymino(tuileListO3f,2,2, TypePolymino.O,3,false));

        return new Piece(polyminoList);

    }
}
