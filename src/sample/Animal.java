package sample;

public class Animal {
    /** nombre de tours avant d'avoir faim */
    private int faim;

    /** nombre de tours avant d'avoir soif */
    private int soif;

    /** nombre de point de vie, si inf à 0, animal meurt*/
    private int point_de_vie;

    /** nombre de point de decomposition, si inf à n, animal trop décompose pour etre mange*/
    private int point_de_decomposition;

    /**abscisse */
    private int abscisse;

    /**ordonnee */
    private int ordonnee;

    /** endurance max */
    private int enduranceMax;

    /** endurance actuelle */
    private int endurance;

    /** perception : champ de vision de l'animal */
    private int perception;

    /** vitesseMax de l'animal */
    private int vitesseMax;

    /** vitesse de l'animal */
    private int vitesse;

    public Animal(int nbFaim, int nbSoif, int x, int y) {
        faim = nbFaim;
        abscisse = x;
        ordonnee = y;
        soif = nbSoif;



    }
}
