public class Plateau {
    Case[][] tableau;

    public Plateau() {
        tableau= new Case[ConfigPartie.largeur_plateau][ConfigPartie.largeur_plateau];
        //la case du tableau contient sa propre coordonee
        for (int x=0; x<ConfigPartie.largeur_plateau; x++){
            for (int y=0; y<ConfigPartie.hauteur_plateau; y++) {
                tableau[x][y]= new Case(new Coordonnee(x,y));
            }
        }

    }
}
