import Enum.*;

public class IndiceCase extends Indice{
    Coordonnee coord;
    TypeIndiceCase typeIndiceCase;

    public IndiceCase(Coordonnee coord, TypeIndiceCase typeIndiceCase) {
        this.coord = coord;
        this.typeIndiceCase = typeIndiceCase;
    }
}
