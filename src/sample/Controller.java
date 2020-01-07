package sample;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private ArrayList<Carnivore> listeCarnivore;
    private ArrayList<Herbivore> listeHerbivore;
    private ArrayList<Charognard> listeCharognard;
    private ArrayList<Point_eau> listePoint_eau;

    public ArrayList<Carnivore> get_list_carnivore(){ return (ArrayList<Carnivore>) this.listeCarnivore; }

    public ArrayList<Herbivore> get_list_herbivore(){
        return (ArrayList<Herbivore>) this.listeHerbivore;
    }

    public ArrayList<Point_eau> get_list_Point_eau(){
        return (ArrayList<Point_eau>) this.listePoint_eau;
    }

    public Controller(int nb_carnivore, int nb_herbivore, int nb_charognard, int nb_eau, int taille_eau_max,int taille_ter){
        Random rd = new Random();
        listeCarnivore = new ArrayList<Carnivore>();
        listeHerbivore = new ArrayList<Herbivore>();
        listeCharognard = new ArrayList<Charognard>();
        listePoint_eau = new ArrayList<Point_eau>();

        for( int i =0; i< nb_carnivore; i++){
            this.listeCarnivore.add(new Carnivore(rd.nextInt(taille_ter),rd.nextInt(taille_ter), taille_ter));
        }
        for( int i =0; i< nb_herbivore; i++){
            this.listeHerbivore.add(new Herbivore(rd.nextInt(taille_ter),rd.nextInt(taille_ter), taille_ter));
        }
        //a rajouter une initiallisation de charognard

        for( int i =0; i< nb_eau; i++){
            this.listePoint_eau.add(new Point_eau(rd.nextInt(taille_ter),rd.nextInt(taille_ter),rd.nextInt(taille_eau_max)));
        }
    }

    public void reinitialiser(){
        this.listeCarnivore.clear();
        this.listeHerbivore.clear();
        this.listeCharognard.clear();
        this.listePoint_eau.clear();
    }

    public void unTour(){
        for (int counter = 0 ; counter < this.listeCarnivore.size() ; counter++) {
            this.listeCarnivore.get(counter).vivre(this.listePoint_eau, this.listeHerbivore);
        }
        for (int counter = 0 ; counter < this.listeHerbivore.size() ; counter++) {
            this.listeHerbivore.get(counter).vivre(this.listePoint_eau, this.listeCarnivore);
        }
    }
}
