public class Tuile extends Case{
    int hauteur;

    public Tuile(int hauteur,Coordonnee coord) {
        super.isVide=false;
        super.coordonnee=coord;
        this.hauteur = hauteur;
    }

}
