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

    public Carnivore(int x, int y) {
        super(ValnbFaim, ValnbSoif, x, y);
    }

    public int chercheProie(ArrayList <Animal> a ) {
        /** Fonction a appeler des que le carnivore cherche une prois à manger:
         * Il choisit sa cible en prenant la proie avec le produit endurance*distance le plus faible
         * Entrees : liste des animaux
         * Sorties : True si cible et l'ID de la cible/ False sinon et 0
         *
         */

        /** ID de la cible en cours */
        Herbivore cible;

        /** booleen pour savoir si on trouve une cible */
        int test_cible = 0;

        /** enduranceCible*distance min */
        int min = 100000;
        /** valeur produit  enduranceCible*distance A DEFINIR */
        int valProduit = 0;

        /** distance cible-proie */
        int dist = 0;

        /** l'id de la cible choisie */
        int ID = 0;

        // Parcours de la liste des animaux
        lIterator = listeAnimaux.listIterator();
        while (lIterator.hasNext()) {
            cible = lIterator.next();
            dist = pow(pow(this.abscisse - cible.abscisse, 2) + pow(this.ordonnee - cible.ordonnee, 2), 0.5);
            if (dist < this.perception) {
                valProduit = dist * cible.endurance;
                if (valProduit < min) {
                    test_cible = 1;
                    ID = cible.getid();
                    min = valProduit;
                }
            }
        }
        return test_cible, ID;
    }



}
