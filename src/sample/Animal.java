package sample;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

/**Valeurs des differentes classes :
 * Herbivore : enduranceMax = x, perception = x, point_de_vie départ = x, vitesseMax = x,
 * Carnivore : enduranceMax = x, perception = x, point_de_vie départ = x, vitesseMax = x,
 * Charognard : enduranceMax = x, perception = x, point_de_vie départ = x, vitesseMax = x,
 *
 */


public abstract class Animal {
    protected static  int IDD = 0;
    /** valeur critique avant d'avoir faim ou soif **/
    protected int valcrit = 10;
    /** nombre de tours avant d'avoir faim */
    protected int faim;
    /** nombre de tours avant d'avoir soif */
    protected int soif;
    /** nombre de point de vie, si inf à 0, animal meurt*/
    protected int point_de_vie;
    /** nombre de point de decomposition, si inf à n, animal trop decompose pour etre mange*/
    protected int point_de_decomposition = 100;
    /**abscisse */
    protected int abscisse;
    /**ordonnee */
    protected int ordonnee;
    /** endurance actuelle */
    protected int endurance;
    /** vitesse de l'animal */
    protected int vitesse;
    /**Pour savoir son etat de recherche d'eau **/
    protected  boolean eau_oui_non = true;// vrais si il cherche de l'eau, faux si il a deja une direction
    /**la le point d'eau que l'on cherche **/
    protected int position_eau_x = -1;
    protected  int position_eau_y = -1;
    protected float rayon_eau = -1;
    /**on presente l'adresse du point d'eau qui est visee**/
    protected int id_point_eau_vise = -1;

    /**definition des constantes de classe,  leurs valeurs seront redefinies pour les differents heritiers de la classe animal**/

    /**le nombre de points d'attaque de l'animal**/
    protected int point_attaque;
    /** endurance max **/
    protected int enduranceMax;
    /** vitesseMax de l'animal **/
    protected int vitesseMax;
    //pour les valeurs faim et soif on par du principe que le min est 0
    /**la valeur max de la faim **/
    protected int faim_max;
    /**la valeur max de la soif**/
    protected int soif_max;
    /** perception : champ de vision de l'animal */
    protected int perception;

    /**declaration de l'id**/
    protected int id;
    /**ON veut eviter du canibalisme donc**/
    /** 0 par défaut, 1 : Carnivore, 2 : Charognard, 3 : Herbivore, 4 : Cadavre **/
    protected int espece; //pour avoir une facon de reconnaitre l'espece et d'eviter du canibalisme

    protected int rayon_action;

    public Animal(int nbFaim, int nbSoif, int x, int y, int attqu, int endur,int vitMax, int fMax, int sMax, int prcptn, int decom, int espece){
        /**constructeur de la classe**/
        this.faim = nbFaim;
        this.abscisse = x;
        this.ordonnee = y;
        this.soif = nbSoif;
        this.point_attaque = attqu;
        this.enduranceMax = endur;
        this.vitesseMax = vitMax;
        this.vitesse = vitMax;
        this.faim_max = fMax;
        this.soif_max = sMax;
        this.perception = prcptn;
        this.espece = espece;
        this.id = IDD;
        IDD++;
    }

    public int getEspece(){
        /**permet de savoir si la cible est de la meme espece ou pas (pas de canibalisme)**/
        return this.espece;
    }

    public  int getId() {
        /**retourne l'id de l'objet courant**/
        return this.id;
    }

    public int getAttaque(){
        /**avec quelle puissance il attaque**/
        return  this.point_attaque;
    }

    public int get_x(){
        return this.abscisse;
    }

    public int get_y(){
        return this.ordonnee;
    }

    public int get_endurance(){
        return  this.endurance;
    }

    public void manger(int val_nouriture){
        //nourriture est une valeur a modifier selon l'heritier de la fonction
        this.faim += val_nouriture;
        if (this.faim > this.faim_max){
            this.faim = this.faim_max;
        }
        System.out.println("je mange " + String.valueOf(this.faim_max));
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

    public void courrir(int x, int y){
        //ici on definira un deplacement avec une vitesse hautes
        if ((x <= this.vitesseMax) && (y <= this.vitesseMax)) {
            this.abscisse += x*this.vitesseMax;
            this.ordonnee += y*this.vitesseMax;
        }
        else {
            //on leve une exception
            throw new IllegalArgumentException("Les valeurs de deplacement sont plus grandes que les valeurs vitesse");
        }
    }

    public boolean est_mort(){
        //si le nombre de points de vie est inferieur a zero tu es mort
        if (point_de_vie <= 0 ){
            return true;
        }
        return false;
    }

    public void recoit_attaque(int val_attaque){
        // il donne la valeur de son attaque a l'adversaire
        if (!this.est_mort()){
            this.point_de_vie -= val_attaque;
            //on verifie qu'il n'est pas mort avant l'attaque
            if (this.est_mort()){
                this.meurt();
            }
        }
    }

    public void meurt(){
        /**fonction de mort**/
        this.point_de_vie = 0;
    }

    public boolean deplace_vers_point_eau(){
        /**cas de la direction definit, l'animal ne cherche plus de point d'eau**/
        double a = Math.sqrt( Math.pow(this.ordonnee - this.position_eau_y,2) + Math.pow(this.abscisse - this.position_eau_x,2));
        this.abscisse += (int)(this.abscisse - this.position_eau_x)*this.vitesse/a;
        this.ordonnee += (int)(this.ordonnee - this.position_eau_y)*this.vitesse/a;
        System.out.println("rposition" +String.valueOf(this.ordonnee) +" "+ String.valueOf(this.abscisse));

        return true;
    }

    public boolean chercher_a_boire(ArrayList<Point_eau> eaux) {
        /**Cherche le point d'eau le plus proche, si pas de point d'eau dans le champ de perception,
         * choisit une direction au hasard et s'avance dans cette direction **/
        if ((this.position_eau_x != -1) && (this.position_eau_y != -1)){
            /**pas de point d'eau trouve**/
            return false;
        }
        /**cas ou on initialise les choses **/
        System.out.println("recherche de point d'eau");
        float dist_min = 100000;
        for (int counter = 0 ; counter < eaux.size() ; counter++){
            int c = eaux.get(counter).get_abscisse();
            int d = eaux.get(counter).get_ordonnee();
            double a = Math.pow((this.abscisse - c),2) + Math.pow((this.abscisse - d),2);
            if ( a < dist_min && a < this.perception*this.perception){
                this.position_eau_x = c;
                this.position_eau_y = d;
                System.out.println("de l'e eau");
                this.id_point_eau_vise = eaux.get(counter).get_id_point_eau();
                this.rayon_eau = eaux.get(counter).getRayon();
            }
        }
        return position_eau_x != -1 && position_eau_y != -1;
    }

    public void meurt_de_faimsoif(int viem, int endm) {
        /** Si l'animal a faim ou soif il perd de la vie et de l'endurance jusqu'a mourir
         * on prend en compte les cas où ls points de vie ou l'ndurance peuvent être inferieurs a 0**/
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

    public boolean check_position(int x, int y, ArrayList <Animal> listeAnimaux) {
        /** retourne un booléen pour savoir si un animal est déjà présent aux coord données
         * true s'il n'y en a pas, false si un animal est déjà présent aux coordonées données
         * **/
        int bool = 1;
        Animal animal;
        for (int counter = 0 ; counter < listeAnimaux.size() ; counter++) {
            animal = listeAnimaux.get(counter);
            if ((animal.abscisse == x) && (animal.ordonnee == y)) {
                bool = 0;
            }
        }
        if (bool ==1) {
            return true;
        }
        else {
            return false;
        }
    }

    public double calcule_distance(int x, int y) {
        /** outil de calcul de distance de l'animal aux coordonnées (x, y) **/

        /** Distance **/
        double dist;
        dist = Math.pow(Math.pow(this.abscisse - x, 2) + Math.pow(this.ordonnee - y, 2), 0.5);
        return dist;
    }

    public void plusfaimplussoif(int ptfaim ,int ptsoif) {
        /** l'animal à plus faim et plus soif chaque tour **/
        this.faim = this.faim - ptfaim;
        this.soif = this.soif - ptsoif;

        if (this.faim < 0) {
            this.faim = 0;
        }

        if (this.soif < 0) {
            this.soif = 0;
        }
    }

    public boolean check_rayonDaction(int x, int y) {
        /** outil permettant de savoir si l'objet ciblé est dans le rayon d'action (pour boire ou manger par exemple)
        * retourne true si dans le rayon d'action; false sinon **/
        double dist = calcule_distance(x, y);
        if (dist <= this.rayon_action) {
            return true;
        }
        else {
            return false;
        }
    }

    public void bougerAleatoirement(){
        Random r = new Random();
        if( Math.random() < 0.5) {
            this.abscisse += r.nextInt(this.vitesseMax + 1);
            this.ordonnee += r.nextInt(this.vitesseMax + 1);
        }else{
            this.abscisse -= r.nextInt(this.vitesseMax + 1);
            this.ordonnee -= r.nextInt(this.vitesseMax + 1);
        }
        System.out.println("je bouge aleatoirement" + String.valueOf(this.abscisse) + " "+ String.valueOf(this.ordonnee));
    }
}