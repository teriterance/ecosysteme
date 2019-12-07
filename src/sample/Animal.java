package sample;

import java.util.ArrayList;
import java.lang.Math;

/**Valeurs des differentes classes :
 * Herbivore : enduranceMax = x, perception = x, point_de_vie départ = x, vitesseMax = x,
 * Carnivore : enduranceMax = x, perception = x, point_de_vie départ = x, vitesseMax = x,
 * Charognard : enduranceMax = x, perception = x, point_de_vie départ = x, vitesseMax = x,
 *
 */


public abstract class Animal {
    /** nombre de tours avant d'avoir faim */
    private int faim;
    /** nombre de tours avant d'avoir soif */
    private int soif;
    /** nombre de point de vie, si inf à 0, animal meurt*/
    private int point_de_vie;
    /** nombre de point de decomposition, si inf à n, animal trop decompose pour etre mange*/
    private int point_de_decomposition;
    /**abscisse */
    private int abscisse;
    /**ordonnee */
    private int ordonnee;
    /** endurance actuelle */
    private int endurance;
    /** vitesse de l'animal */
    private int vitesse;
    /**Pour savoir son etat de recherche d'eau **/
    private  boolean eau_oui_non = true;// vrais si il cherche de l'eau, faux si il a deja une direction
    /**vecteur de direction de recherche **/
    private int direction_x;
    private  int direction_y;

    /**definition des constantes statiques leurs valeur seront redefinies pour les different heritiers de la classe animal**/

    /**le nombre de points d'attaque de l'animal**/
    private static int point_attaque = 20;
    /** endurance max */
    private static final int enduranceMax = 20;
    /** vitesseMax de l'animal */
    private static final int vitesseMax = 20;
    //pour les valeurs faim et soif on par du principe que le min est 0
    /**la valeur max de la faim **/
    private static final int faim_max = 20;
    /**la valeur max de la soif**/
    private static final int soif_max = 20;
    /** perception : champ de vision de l'animal */
    private static final int perception = 20;

    public Animal(int nbFaim, int nbSoif, int x, int y) {
        this.faim = nbFaim;
        this.abscisse = x;
        this.ordonnee = y;
        this.soif = nbSoif;
    }

    void manger(int val_nouriture){
        //nourriture est une valeur a modifier selon l'heritier de la fonction
        this.faim += val_nouriture;
        if (this.faim > this.faim_max){
            this.faim = this.faim_max;
        }
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
    void boire(int val_eau){
        //valeur d'eau a boire
        this.soif += val_eau;
        if(this.soif > this.soif_max){
            this.soif = this.soif_max;
        }
    }
    void courir(int x, int y){
        //ici on definira un deplacement avec une vitesse hautes
        if ((x <= this.vitesseMax) && (y <= this.vitesseMax)) {
            this.abscisse += x;
            this.ordonnee += y;
        }
        else {
            //on leve une exception
            throw new IllegalArgumentException("Les valeurs des deplacement sont plus grandes que les valeur vitesse");
        }
    }

    boolean est_mort(){
        //si le nombre de points de vie est inferieur a zero tu es mort
        return (point_de_vie <= 0 );
    }
    void recois_attque(int val_attaque){
        // il donne la valeur de son attaque a l'adversaire
        if (!this.est_mort()){
            this.point_de_vie = val_attaque;
            //on verifie qu'il n'est pas mort avant l'attaque
            if (this.est_mort()){
                this.meurt();
            }
        }
    }
    void meurt(){
        this.point_de_vie = 0;
    }
    double dist(Poin_eau e){
        return Math.sqrt(Math.pow(this.abscisse - e.get_abscisse(), 2) + Math.pow(this.ordonnee - e.get_ordonnee(), 2));
    }
    void chercher_a_boire(ArrayList<Poin_eau> eaus) {
        /**Cherche le point d'eau le plus proche, si pas de point d'eau dans le champ de perception,
         * choisit une direction au hasard et s'avance dans cette direction **/

    }

    void meurt_de_faimsoif(int viem, int endm) {
        /** Si l'animal a faim ou soif il perd de la vie et de l'endurance jusqu'a mourir
         * on prend en compte les cas où ls points de vie ou l'ndurance peuvent être inferieurs a 0*/
        int diffpdv = this.point_de_vie - viem;
        int diffend = this.endurance - endm;

        if ((diffpdv > 0 ) && (diffend > 0)) {
            this.point_de_vie -= viem;
            this.endurance -= endm;
        }

        if ((diffpdv <= 0 ) && (diffend > 0)) {
            this.point_de_vie = 0;
            this.endurance -= endm;
        }

        if ((diffpdv > 0 ) && (diffend <= 0)) {
            this.point_de_vie -= viem;
            this.endurance = 0;
        }

        else {
            this.point_de_vie = 0;
            this.endurance = 0;
        }
    }
    int attaque(){
        /**avec quelle puissance il attaque**/
        return  this.point_attaque;
    }
}