package sample;
import java.util.*;
import java.util.LinkedList;

/** ATTENTION : valeurs à modifier par leur valeurs numériques :
 * ValnbFaim
 * ValnbSoif
 */


public class Carnivore extends Animal {

    public Carnivore(int x, int y) {
        super(ValnbFaim, ValnbSoif, x, y);
    }

    public int chercheProie(int listeAnimaux) {
        /** Fonction a appeler des que le carnivore cherche une prois à manger:
         * Il choisit sa cible en prenant la proie avec le produit endurance*distance le plus faible
         * Entrees : liste des animaux
         * Sorties : l'ID de la cible
         *
         */

        /** ID de la cible en cours */
        int cible;

        /** enduranceCible*distance min */
        int min;

        // Parcours de la liste des animaux
        java.util.ListIterator lIterator = listeAnimaux.listIterator();
        while (lIterator.hasNext()) {
            Animal cible = lIterator.next();
            if cible
        }


    }
}