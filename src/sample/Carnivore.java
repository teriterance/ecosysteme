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
    protected int ciblex = 0;
    protected int cibley = 0;

    public Carnivore(int nbFaim, int nbSoif, int x, int y, int attqu, int endur,int vitMax, int fMax, int sMax, int prcptn, int decom) {

        super(nbFaim, nbSoif, x, y, attqu, endur, vitMax, fMax, sMax, prcptn, decom, 1);
    }

    public void chercheProie(ArrayList <Animal> listeAnimaux) {
        /** Fonction a appeler des que le carnivore cherche une prois à manger:
         * Il choisit sa cible en prenant la proie avec le produit endurance*distance le plus faible
         * Entrees : liste des animaux
         * Sorties : void
         *
         */

        /** ID de la cible en cours */
        Animal cible;

        /** enduranceCible*distance min */
        double min = 100000;
        /** valeur produit  enduranceCible*distance A DEFINIR */
        double valProduit = 0;

        /** distance cible-proie */
        double dist = 0;

        /** l'id de la cible choisie */
        Animal ciblef = null;

        /** au cas où on ne trouve pas de cible **/
        int test_cible = 0;

        // Parcours de la liste des animaux
        for (int counter = 0 ; counter < listeAnimaux.size() ; counter++){
            if (listeAnimaux.get(counter).getEspece() == 3) {
                cible = listeAnimaux.get(counter);
                dist = calcule_distance(cible.abscisse, cible.ordonnee);
                if (dist < this.perception) {
                    valProduit = dist * cible.endurance;
                    if (valProduit < min) {
                        test_cible = 1;
                        ciblef = cible;
                        min = valProduit;
                    }
                }
            }
        }
        if (test_cible == 1) {
            this.cible = ciblef.getId(); // erreur sur le ciblef pas définie ???  -> j'ai rajouté null
            this.ciblex = ciblef.abscisse;
            this.cibley = ciblef.ordonnee;
        }
    }

    public void attaquer(ArrayList <Animal> listeAnimaux) {
        /** fonction pour attaquer la cible définie par l'id **/
        Animal ciblei = null;
        int test = 0; // au cas où la cible est déjà morte
        for (int counter = 0 ; counter < listeAnimaux.size() ; counter++) {
            if (listeAnimaux.get(counter).getId() == this.cible) {
                ciblei = listeAnimaux.get(counter);
                test = 1;
            }
        }
        if (test == 1) {
            ciblei.meurt();
            ciblei.transformerEnCadavre(); // A DEFINIR : on supprime l'animal et on créé un cadavre aux coords x y
            manger(this.faim_max);
            this.cible = -1;
            this.ciblex = 0;
            this.cibley = 0;
        }
        else {
            this.cible = -1;
            this.ciblex = 0;
            this.cibley = 0;

        }
    }

    public void vivre(ArrayList<Point_eau> list_eaux, ArrayList<Animal> listeAnimaux) {
        /** fonction à laquel on fait appel à chaque tour pour que le carnivore vive **/
        int ptvie_perdu_par_tour = 1;
        int ptend_perdu_par_tour = 1;

        /** L'animal à de plus en plus faim chaque tour **/
        plusfaimplussoif();

        /**SI MEURT DE faim ou soif **/
        if ((this.soif==0) || (this.faim==0)) {
            meurt_de_faimsoif(ptvie_perdu_par_tour, ptend_perdu_par_tour);
        }

        /** on défini la priorité faim ou soif **/
        /** si l'animal à faim et soif il doit décider ce qu'il préfère : manger ou boire **/
        int prio = 0; // = 1 si prio est de manger, 2 si prio est de boire, 0 si ni faim ni soif
        if ((this.soif < this.valcrit) && (this.faim < this.valcrit)) {
            if (this.soif <= this.faim){
                prio = 2;
            }
            if (this.soif > this.faim){
                prio = 1;
            }
        }

        /** SI SOIF : **/
        if (prio == 2) {
            /** initialisation du point d'eau à viser s'il n'existe pas **/
            if (this.id_point_eau_vise==-1){
                chercher_a_boire(list_eaux); // A DEFINIR : initialisation du point d'eau visé
            }
            deplace_vers_point_eau();
        }

        /** SI FAIM : **/
        if (prio == 1) {
            if (this.cible == -1) {
                chercheProie(listeAnimaux);
            }
            if (this.cible == -1){
                chercherAleatoirement(); //A DEFINIR si pas de cible trouvée chercher aléatoirement
            }
            else {
                if (check_rayonDaction(this.ciblex, this.cibley) == true) {
                    attaquer(listeAnimaux);
                }
                else {
                    poursuivre(); //A DEFINIR
                }
            }
        }


        /** SI NI FAIM NI SOIF : **/
        if (prio == 0){
            bougerAleatoirement(); //A DEFINIR
        }

    }



}
