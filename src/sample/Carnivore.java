package sample;
import java.util.*;
import java.util.ListIterator;
import java.util.LinkedList;
import math.h;


/** ATTENTION : valeurs à modifier par leur valeurs numériques :
 * ValnbFaim
 * ValnbSoif
 */


public class Carnivore extends Animal {

    /** id de la cible (différent de la valeur par défaut x) **/
    protected char cible = 'x';

    public Carnivore(int x, int y) {

        super(ValnbFaim, ValnbSoif, x, y);
    }

    public char chercheProie(ArrayList <Animal> listeAnimaux ) {
        /** Fonction a appeler des que le carnivore cherche une prois à manger:
         * Il choisit sa cible en prenant la proie avec le produit endurance*distance le plus faible
         * Entrees : liste des animaux
         * Sorties : True si cible et l'ID de la cible/ False sinon et 0
         *
         */

        /** ID de la cible en cours */
        Animal cible;

        /** enduranceCible*distance min */
        int min = 100000;
        /** valeur produit  enduranceCible*distance A DEFINIR */
        int valProduit = 0;

        /** distance cible-proie */
        int dist = 0;

        /** l'id de la cible choisie */
        char ID = 'x';

        // Parcours de la liste des animaux
        for (int counter = 0 ; counter < listeAnimaux.size() ; counter++){
            cible = listeAnimaux.get(counter);
            dist = calcule_distance(cible.abscisse, cible.ordonnee);
            if (dist < this.perception) {
                valProduit = dist * cible.endurance;
                if (valProduit < min) {
                    test_cible = 1;
                    ID = cible.getid();
                    min = valProduit;
                }
            }
        }
        return ID;
    }


    public void vivre() {
        /** fonction à laquel on fait appel à chaque tour pour que le carnivore vive **/
        int ptvie_perdu_par_tour = 1;
        int ptend_perdu_par_tour = 1;

        /** si l'animal à faim et soif il doit décider ce qu'il préfère : manger ou boire **/
        int prio = 0;

        /** L'animal à de plus en plus faim chaque tour **/
        plusfaimplussoif();

        /**SI MEURT DE faim ou soif **/
        if ((this.soif==0) || (this.faim==0) {
            meurt_de_faimsoif(ptvie_perdu_par_tour, ptend_perdu_par_tour);
        }

        /** on défini la priorité faim ou soif **/


        /** SI SOIF : **/
        if (this.soif < this.valcrit) {
            if (this.id_point_eau_vise==-1){
                chercher_a_boire(list_eaux); // A DEFINIR : initialisation du point d'eau visé
            }
            deplace_vers_point_eau();
        }

        /** SI FAIM : **/
        if ()
    }



}
