package sample;

import java.util.ArrayList;

public class Herbivore extends Animal{

    /** Variable utile pour savoir si l'animal se sent menacé : dans ce cas il fuit et ne s'arrête pas pour manger/boire **/
    protected boolean effroi = false;
    /** cible : si attaqué par un/plusieurs carnivores **/
    protected int cible = -1;
    protected int ciblex = 0;
    protected int cibley = 0;


    public Herbivore(int nbFaim, int nbSoif, int x, int y){
        super(nbFaim, nbSoif, x, y, 0, 0, 0, 0, 0, 0, 0, 2);
    }

    public void manger(){
        /**un herbivre mange 10 a la fois**/
        if (this.effroi == false) {
            super.manger(10);
        }
    }

    public void cherchePredateur(ArrayList<Animal> listeAnimaux) {
        /** Fonction a appeler à tous les tours : l'herbivore reste aux aguets
         * la cible est le predateur le plus proche (si il y en a plusieurs)
         * Entrees : liste des animaux
         * Sorties : void
         *
         */

        /** ID de la cible en cours */
        Carnivore cible;

        /** distance cadavre-charognard */
        double dist = 0;

        /** l'id de la cible choisie */
        Carnivore ciblef = null;

        /** au cas où on ne trouve pas de cible **/
        int test_cible = 0;

        /** distance min */
        double min = 100000;

        // Parcours de la liste des animaux
        for (int counter = 0 ; counter < listeAnimaux.size() ; counter++){
            if (listeAnimaux.get(counter).getEspece() == 1) {
                cible = (Carnivore) listeAnimaux.get(counter);
                dist = calcule_distance(cible.abscisse, cible.ordonnee);
                if ((dist < this.perception) && (dist < min)) {
                    test_cible = 1;
                    ciblef = cible;
                    min = dist;
                    this.effroi = true;
                }
            }
        }
        if (test_cible == 1) {
            this.cible = ciblef.getId(); // erreur sur le ciblef pas définie ??? -> j'ai rajouté null
            this.ciblex = ciblef.abscisse;
            this.cibley = ciblef.ordonnee;
        }
    }

}
