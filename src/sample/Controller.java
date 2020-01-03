package sample;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    public static void main(String[] args) {
        /* étapes de fonctionnment
        - on creait une liste d'annimal de type Carnivore charognard et herbivore
        - On les fait évoluer en appelant les differentes fonctions qui sont contenues dans ces derniers
        - On affiche le resultat
         */

        //on génere une liste de carnivore en fonction sur le terain
        ArrayList<Carnivore> Carnivores = new ArrayList<Carnivore>();
        Random ran = new Random();

        //on rempli la liste
        for( int i =0; i< 1; i++){
            Carnivores.add(new Carnivore(52,52));
        }

        ArrayList<Herbivore> Herbivores = new ArrayList<Herbivore>();
        for( int i =0; i< 1; i++){
            Herbivores.add(new Herbivore(54,54));
        }

        ArrayList<Point_eau> eaus = new ArrayList<Point_eau>();

        for( int i =0; i< 1; i++){
            eaus.add(new Point_eau(50,50, 2));
        }
        int i = 0;
        while (i < 5){
            i++;
            for (int counter = 0 ; counter < Carnivores.size() ; counter++) {
                Carnivores.get(counter).vivre(eaus, Herbivores);
            }
            for (int counter = 0 ; counter < Herbivores.size() ; counter++) {
                Herbivores.get(counter).vivre(eaus, Carnivores);
            }
        }
    }
}
