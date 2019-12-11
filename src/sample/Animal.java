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
    protected int endurance;
    /** vitesse de l'animal */
    private int vitesse;
    /**Pour savoir son etat de recherche d'eau **/
    private  boolean eau_oui_non = true;// vrais si il cherche de l'eau, faux si il a deja une direction
    /**la le point d'eau que l'on cherche **/
    private int position_eau_x = -1;
    private  int position_eau_y = -1;
    private float rayon_eau = -1;
    /**on presente l'adresse du point d'eau qui est visee**/
    private int id_point_eau_vise = -1;

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

    public void manger(int val_nouriture){
        //nourriture est une valeur a modifier selon l'heritier de la fonction
        this.faim += val_nouriture;
        if (this.faim > this.faim_max){
            this.faim = this.faim_max;
        }
    }

    public void deplacer(int x, int y){
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
    public void boire(int val_eau){
        //valeur d'eau a boire
        this.soif += val_eau;
        if(this.soif > this.soif_max){
            this.soif = this.soif_max;
        }
    }
    public void courir(int x, int y){
        //ici on definira un deplacement avec une vitesse hautes
        if ((x <= this.vitesseMax) && (y <= this.vitesseMax)) {
            this.abscisse += x;
            this.ordonnee += y;
        }
        else {
            //on leve une exception
            throw new IllegalArgumentException("Les valeurs de deplacement sont plus grandes que les valeurs vitesse");
        }
    }
    public boolean est_mort(){
        //si le nombre de points de vie est inferieur a zero tu es mort
        return (point_de_vie <= 0 );
    }
    public void recois_attque(int val_attaque){
        // il donne la valeur de son attaque a l'adversaire
        if (!this.est_mort()){
            this.point_de_vie = val_attaque;
            //on verifie qu'il n'est pas mort avant l'attaque
            if (this.est_mort()){
                this.meurt();
            }
        }
    }

    public void meurt(){
        //fonction de mort
        this.point_de_vie = 0;
    }

    public boolean deplace_vers_point_eau(){
        /**cas de la direction definit, l'animal ne cherche plus de point d'eau**/
        double a = Math.sqrt(this.position_eau_y*this.position_eau_y + this.position_eau_x*this.position_eau_x);
        this.abscisse += (int)(this.abscisse - this.position_eau_x)*this.vitesse/a;
        this.ordonnee += (int)(this.ordonnee - this.position_eau_y)*this.vitesse/a;
        if (Math.pow((this.abscisse - this.position_eau_x),2) + Math.pow((this.abscisse - this.position_eau_x),2) < Math.pow(this.rayon_eau,2)){
            return false;
        }
        return true;
    }

    public boolean chercher_a_boire(ArrayList<Poin_eau> eaus) {
        /**Cherche le point d'eau le plus proche, si pas de point d'eau dans le champ de perception,
         * choisit une direction au hasard et s'avance dans cette direction **/
        if ((this.position_eau_x != -1) && (this.position_eau_y != -1)){
            return false;
        }
        if(!((this.position_eau_x == -1 ) ^ (this.position_eau_y == -1 ))){
            throw new IllegalArgumentException("erreur de signe, la position du point d'eau ne peut pas etre negative");
        }
        /**cas ou on initialise les choses **/
        float dist_min = 100000;
        for (int counter = 0 ; counter < eaus.size() ; counter++){
            double a = Math.pow((this.abscisse - this.position_eau_x),2) + Math.pow((this.abscisse - this.position_eau_x),2);
            if ( a < dist_min){
                this.position_eau_x = eaus.get(counter).get_abscisse();
                this.position_eau_y = eaus.get(counter).get_ordonnee();
                this.rayon_eau = eaus.get(counter).getRayon();
            }
        }
        return true;
    }

    public void meurt_de_faimsoif(int viem, int endm) {
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
    public int attaque(){
        /**avec quelle puissance il attaque**/
        return  this.point_attaque;
    }
}