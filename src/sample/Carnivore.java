package sample;
import java.util.*;
import java.util.ListIterator;
import java.util.LinkedList;
import java.lang.Math;


/** ATTENTION : valeurs à modifier par leur valeurs numériques :
 * ValnbFaim
 * ValnbSoif
 */


public class Carnivore extends Animal {

    /** id de la cible (différent de la valeur par défaut x) **/
    protected int cible = -1;

    int ptvie_perdu_par_tour = 1;
    int ptend_perdu_par_tour = 1;

    public Carnivore(int x, int y) {
        /**definition des valeurs
         * nbFaim: 100,  nbSoif: 100, fMax: 100, sMax: 100
         * position x, y
         * prcptn: 18, decomp 100
         * espece : 1
         **/
        super(100, 10, x, y, 20, 50, 5, 100, 100, 100, 100, 1);
        System.out.println("Creation d'un nouveau carnivore d'id: " + String.valueOf(this.id) + " en position: ("+ String.valueOf(this.abscisse) +" "+ String.valueOf(this.ordonnee) +") ");
    }

    public boolean est_en_poursuite(){
        /**permet de savoir si le predateur a deja une cible dans sa ligne de mire**/
        if( this.cible != -1){
            return true;
        }
        return  false;
    }

    public void chercheProie(ArrayList <Herbivore> listeAnimaux) {
        /** Fonction a appeler des que le carnivore cherche une prois à manger:
         * Il choisit sa cible en prenant la proie avec le produit endurance*distance le plus faible
         * Entrees : liste des animaux
         * Sorties : void
         */
        System.out.println("je recherche au tour de moi une cible");
        /** enduranceCible*distance min on peut jouer sur ceci pour augmenter le rayon d'action */
        double min = 10000;

        /** distance cible-proie */
        double dist = 0;

        // Parcours de la liste des animaux
        for (int counter = 0 ; counter < listeAnimaux.size() ; counter++){
            if (listeAnimaux.get(counter).getEspece() == 2) {
                dist = calcule_distance(listeAnimaux.get(counter).get_x(), listeAnimaux.get(counter).get_y());
                if (dist < this.perception) {
                    double valProduit = dist * listeAnimaux.get(counter).get_endurance();
                    if (valProduit < min) {
                        this.cible = listeAnimaux.get(counter).getId();
                        min = valProduit;
                    }
                }
            }
        }

        if(this.cible != -1){
            System.out.println("j'en ai trouve une");
        }else{
            System.out.println("je n'en ai pas trouve");
        }
    }

    public void attaquer(ArrayList <Herbivore> listeAnimaux) {
        /** fonction pour attaquer la cible définie par l'id **/
        for (int counter = 0 ; counter < listeAnimaux.size() ; counter++) {
            if (listeAnimaux.get(counter).getId() == this.cible) {
                if(listeAnimaux.get(counter).est_mort()){
                    //on reinitiallise sa recherche de cible
                    this.cible = -1;
                }else{
                    this.cible  = listeAnimaux.get(counter).getId();
                    if(check_rayonDaction(listeAnimaux.get(counter).get_x(), listeAnimaux.get(counter).get_y())) {
                        System.out.println("je suis un carnivore et j'attaque un herbivore");
                        this.manger(this.faim_max);
                        listeAnimaux.get(counter).meurt();
                        this.cible = -1;
                    }
                }
            }
        }
    }

    public void vivre(ArrayList<Point_eau> list_eaux, ArrayList<Herbivore> listeAnimaux) {
        /** fonction à laquel on fait appel à chaque tour pour que le carnivore vive **/
        /** L'animal à de plus en plus faim chaque tour si il le peut **/
        if ((this.soif >= 0) || (this.faim >= 0)) {
            plusfaimplussoif(ptend_perdu_par_tour, ptend_perdu_par_tour);
        }

        /**SI MEURT DE faim ou soif **/
        if ((this.soif==0) || (this.faim==0)) {
            meurt_de_faimsoif(ptvie_perdu_par_tour, ptend_perdu_par_tour);
        }

        /** on défini la priorité faim ou soif **/
        /** si l'animal à faim et soif il doit décider ce qu'il préfère : manger ou boire **/
        int prio = 0; // = 1 si prio est de manger, 2 si prio est de boire, 0 si ni faim ni soif
        if ((this.soif < this.valcrit) || (this.faim < this.valcrit)) {
            if (this.soif <= this.faim){
                prio = 2;
            }else {
                prio = 1;
            }
        }


        /** si la soif est prioritaire : **/
        if (prio == 2) {
            /** initialisation du point d'eau à viser s'il n'existe pas **/
            if (this.position_eau_x != -1 && this.position_eau_y != -1){
                // A DEFINIR : initialisation du point d'eau visé
                if(deplace_vers_point_eau()){
                    this.boire(100);
                    System.out.println("je bois");
                }
            }else{
                chercher_a_boire(list_eaux);
            }
        }
        /** SI FAIM : **/
        if (prio == 1) {
            if (this.cible == -1) {
                this.chercheProie(listeAnimaux);
            }else {
                this.attaquer(listeAnimaux);
                this.poursuivre(listeAnimaux); //A DEFINIR
            }
        }

        /** SI NI FAIM NI SOIF : **/
        if (prio == 0){
            this.bougerAleatoirement(); //A DEFINIR
        }
    }

    public void poursuivre( ArrayList<Herbivore> listeAnimaux) {
        /**Denifnit comment un Carnivore poursuit une proie
         * Sa position s'incremente d'une composante de vitesse
         * **/
        if (this.cible != -1) {
            for (int counter = 0; counter < listeAnimaux.size(); counter++) {
                if (listeAnimaux.get(counter).getId() == this.cible) {
                    double a = Math.sqrt(listeAnimaux.get(counter).get_x() * listeAnimaux.get(counter).get_x() + listeAnimaux.get(counter).get_y() * listeAnimaux.get(counter).get_y());
                    this.abscisse += (int) (this.abscisse - listeAnimaux.get(counter).get_x()) * this.vitesse / a;
                    this.ordonnee += (int) (this.ordonnee - listeAnimaux.get(counter).get_y()) * this.vitesse / a;
                    System.out.println("je ne poursuit une cible");
                }
            }
        }
    }

}
