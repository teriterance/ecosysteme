package sample;
import java.util.*;
import java.lang.Math;




/** ATTENTION : valeurs à modifier par leur valeurs numériques :
 * ValnbFaim
 * ValnbSoif
 */


public class Carnivore extends Animal {

    public Carnivore(int x, int y) {
        super(ValnbFaim, ValnbSoif, x, y);
    }

    public int chercheProie(ArrayList <Animal> animaux ) {
        /** Fonction a appeler des que le carnivore cherche une prois à manger:
         * Il choisit sa cible en prenant la proie avec le produit endurance*distance le plus faible
         * Entrees : liste des animaux
         * Sorties : True si cible et l'ID de la cible/ False sinon et 0
         *
         */

        /** ID de la cible en cours */
        Herbivore cible;

        /** enduranceCible*distance min */
        double min = 100000;
        /** valeur produit  enduranceCible*distance A DEFINIR */
        double valProduit = 0;

        /** distance cible-proie */
        double dist = 0;

        /** l'id de la cible choisie */
        char ID = '-1';

        // Parcours de la liste des animaux
        for (int counter = 0 ; counter < animaux.size() ; counter++) {
            cible = animaux.get(counter);
            dist = Math.pow(Math.pow(this.abscisse - cible.abscisse, 2) + Math.pow(this.ordonnee - cible.ordonnee, 2), 0.5);
            if (dist < this.perception) {
                valProduit = dist * cible.endurance;
                if (valProduit < min) {
                    ID = cible.getid();
                    min = valProduit;
                }
            }
        }
        return ID;
    }

    public void poursuivre_cible(Herbivore cible) {
        /** Cas de l'animal idiot qui court simplement vers la position de la cible */
        double dist = Math.sqrt(Math.pow(cible.abscisse - this.abscisse), 2)
        double x = (cible.abscisse - this.abscisse) * this.vitesse / dist;
        this.deplacer(x,y);

    }



}
