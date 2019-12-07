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

    public Animal(int nbFaim, int nbSoif, int x, int y, int vitm, int perc, int end) {
        faim = nbFaim;
        abscisse = x;
        ordonnee = y;
        soif = nbSoif;
        vitesseMax = vitm;
        perception = perc;
        enduranceMax = end;
    }

    void manger(int nouriture){
        //nourituere est une valeur a modifier selon l'heritier de la fonction
    }
    void deplacer(int x, int y){
        //x et y sont les valeur de deplacement selon x et y

        if ((x <= this.vitesse) && (x <= this.vitesse)) {
            this.abscisse += x;
            this.ordonnee += y;
        }
        else {
            //on leve une exception
            throw new IllegalArgumentException("Les valeurs des deplacement sont plus grandesque les valeur vitesse");
        }
    }
    void boire(int eau){
        //valeur d'eau a boire
    }
    void fuite(int x, int y){
        //ici on definira un deplacement avec une vitesse haute

        if ((x <= this.vitesse) && (x <= this.vitesse)) {
            this.abscisse += x;
            this.ordonnee += y;
        }
        else {
            //on leve une exception
            throw new IllegalArgumentException("Les valeurs des deplacement sont plus grandesque les valeur vitesse");
        }
    }

    boolean est_mort(){
        //si le nombre de points de vie est inferieru a zero tu est mort
        return (point_de_vie > 0 );
    }
    int attaquer(int val_attque){
        return val_attque;
    }

}