package sample;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    public static void main(String[] args) {
        /* etapes de fonctionnmen
        - on creait une liste d'annimal de type Cernivore charognard et herbivore
        - On les fait evoluer en appelant les differente fonction qui sont contenue dans ces derniers
        - On affiche le resultat
         */

        //on genere une liste de carnivore en fonction sur le terain
        ArrayList<Carnivore> Carnivores = new ArrayList<Carnivore>();
        Random ran = new Random();

        //on rempli la liste
        for( int i =0; i< 1; i++){
            Carnivores.add(new Carnivore(ran.nextInt(100),ran.nextInt(100)));
        }

        ArrayList<Herbivore> Herbivores = new ArrayList<Herbivore>();
        for( int i =0; i< 10; i++){
            Herbivores.add(new Herbivore(ran.nextInt(100),ran.nextInt(100)));
        }

        ArrayList<Point_eau> eaus = new ArrayList<Point_eau>();

        for( int i =0; i< 3; i++){
            eaus.add(new Point_eau(ran.nextInt(100),ran.nextInt(100), ran.nextInt(5)));
        }

        while (true){
            for (int counter = 0 ; counter < Carnivores.size() ; counter++) {
                Carnivores.get(counter).vivre(eaus, Herbivores);
            }
        }
    }
}
