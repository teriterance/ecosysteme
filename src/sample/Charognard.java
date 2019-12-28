package sample;

import java.util.ArrayList;

public class Charognard extends Animal {

    /** id de la cible -cadavre- (différent de la valeur par défaut -1) **/
    protected int cible = -1;
    protected int ciblex = 0;
    protected int cibley = 0;

        //A MODIFIER AVEC LES VALEURS PAR DEFAUT :
    public Charognard(int nbFaim, int nbSoif, int x, int y, int attqu, int endur,int vitMax, int fMax, int sMax, int prcptn, int decom, String espece) {

        super(nbFaim, nbSoif, x, y, attqu, endur, vitMax, fMax, sMax, prcptn, decom, espece);
    }

    public void chercheCadavre(ArrayList<Animal> listeAnimaux) {
        /** Fonction a appeler des que le charognard cherche un cadavre à manger:
         * Il choisit sa cible en prenant la proie avec le produit endurance*distance le plus faible
         * Entrees : liste des animaux
         * Sorties : void
         *
         */

        /** ID de la cible en cours */
        Cadavre cible;

        /** distance cadavre-charognard */
        double dist = 0;

        /** l'id de la cible choisie */
        Cadavre ciblef = null;

        /** au cas où on ne trouve pas de cible **/
        int test_cible = 0;

        /** distance min */
        double min = 100000;

        // Parcours de la liste des animaux
        for (int counter = 0 ; counter < listeAnimaux.size() ; counter++){
            if (listeAnimaux.get(counter).getEspece() == 4) {
                cible = (Cadavre) listeAnimaux.get(counter);
                dist = calcule_distance(cible.abscisse, cible.ordonnee);
                if ((dist < this.perception) && (dist < min)) {
                    test_cible = 1;
                    ciblef = cible;
                    min = dist;
                }
            }
        }
        if (test_cible == 1) {
            this.cible = ciblef.getId(); // erreur sur le ciblef pas définie ??? -> j'ai rajouté null
            this.ciblex = ciblef.abscisse;
            this.cibley = ciblef.ordonnee;
        }
    }

    public boolean deplace_vers_cadavre(){
        /**cas de la direction definit, l'animal ne cherche plus de cadavre**/
        double a = Math.sqrt(this.cibley*this.cibley + this.ciblex*this.ciblex);
        this.abscisse += (int)(this.abscisse - this.ciblex)*this.vitesse/a;
        this.ordonnee += (int)(this.ordonnee - this.cibley)*this.vitesse/a;
        if (check_rayonDaction(this.ciblex, this.cibley)){
            /**Sur ou dans le point d'eau**/
            return false;
        }
        return true;
    }

    public void mangerCadavre() {
        /**mange le cadavre**/
        manger(this.faim_max);
    }

}
