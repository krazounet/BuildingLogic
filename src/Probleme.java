import java.util.ArrayList;
import java.util.List;

public class Probleme {
    Plateau plateau;
    List <Polymino> polyminoList;
    EnsembleIndices ensembleIndices;

    public Probleme() {
        //generation plateau
        plateau = new Plateau();

        //choix Polymino
        polyminoList =new ArrayList<>();

        //Creation des indices
    }

    public void export() {
    }
}
