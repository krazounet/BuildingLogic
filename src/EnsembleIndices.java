import java.util.List;

public class EnsembleIndices<Indice> {
    List<IndicePicross> indicePicrossList;
    List<IndiceCase> indiceCaseList;

    public EnsembleIndices(Plateau plateau) {
        //les indices sont generes a partir du plateau
    }

    public void add(Indice indice) {
        switch (indice.getClass().getName())
        {
            case "IndicePicross":
                indicePicrossList.add((IndicePicross) indice);
                break;
            case "IndiceCase":
                indiceCaseList.add((IndiceCase) indice);
                break;
        }
    }
}
