public class ValeurTuile {
    int valeur_recto;
    int valeur_verso;

    public ValeurTuile(int valeur_recto, int valeur_verso) {
        this.valeur_recto = valeur_recto;
        this.valeur_verso = valeur_verso;
    }
    public int getValeur(boolean recto){
        if (recto)return valeur_recto;
        return valeur_verso;
    }
}
