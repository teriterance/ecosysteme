package sample;

import java.util.ArrayList;

public class Herbivore extends Animal{

    /** Variable utile pour savoir si l'animal se sent menacé : dans ce cas il fuit et ne s'arrête pas pour manger/boire **/
    protected boolean effroi = false;
    
    /** cible : si attaqué par un/plusieurs carnivores **/
    protected int cible = -1;
    protected int ciblex = 0;
    protected int cibley = 0;

    public Herbivore(int x, int y){
        super(100, 100, x, y, 0, 0, 7, 100, 100, 4, 7, 2);
        System.out.println("Creation d'un nouveau herbivore d'id: " + String.valueOf(this.id) + " en position: ("+ String.valueOf(this.abscisse) +" "+ String.valueOf(this.ordonnee) +") ");
    }

    public void manger(){
        /**un herbivre mange 10 a la fois**/
        if (this.effroi == false) {
            super.manger(10);
        }
    }
    public void vivre(ArrayList<Point_eau> list_eaux, ArrayList<Carnivore> listeAnimaux) {
        if(this.est_mort() ==false) {
            /** fonction à laquel on fait appel à chaque tour pour que le carnivore vive **/
            /** L'animal à de plus en plus faim chaque tour si il le peut **/
            if ((this.soif >= 0) || (this.faim >= 0)) {
                plusfaimplussoif(1, 1);
            }

            /**SI MEURT DE faim ou soif **/
            if ((this.soif == 0) || (this.faim == 0)) {
                meurt_de_faimsoif(1, 1);
            }

            /** on défini la priorité faim ou soif **/
            /** si l'animal à faim et soif il doit décider ce qu'il préfère : manger ou boire **/
            int prio = 0; // = 1 si prio est de manger, 2 si prio est de boire, 0 si ni faim ni soif
            if ((this.soif < this.valcrit) || (this.faim < this.valcrit)) {
                if (this.soif <= this.faim) {
                    prio = 2;
                } else {
                    prio = 1;
                }
            }

            /** si la soif est prioritaire : **/
            if (prio == 2) {
                /** initialisation du point d'eau à viser s'il n'existe pas **/
                if (this.position_eau_x != -1 && this.position_eau_y != -1) {
                    // A DEFINIR : initialisation du point d'eau visé
                    if (deplace_vers_point_eau()) {
                        this.boire(100);
                        System.out.println("je bois");
                    }
                } else {
                    chercher_a_boire(list_eaux);
                    this.bougerAleatoirement();
                }
            }
            /** SI FAIM : **/
            if (prio == 1) {
                this.manger();// il peut manger partout "meme sur l'eau
            }

            /** SI NI FAIM NI SOIF : **/
            if (prio == 0) {
                this.bougerAleatoirement(); //A DEFINIR
            }
        }else{
            System.out.println("je suis mort");
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
