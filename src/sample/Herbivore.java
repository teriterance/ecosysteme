package sample;

import java.util.ArrayList;
import java.util.Random;

public class Herbivore extends Animal{

    /** Variable utile pour savoir si l'animal se sent menacé : dans ce cas il fuit et ne s'arrête pas pour manger/boire **/
    protected boolean effroi = false;
    
    /** cible : si attaqué par un/plusieurs carnivores **/
    protected int cible = -1;
    public Herbivore(int x, int y, int taille_ter){
        super(100, 100, 100, 100, x, y, 5, 100, 100, 100, 100, 10, 100, 1, 5, taille_ter);
        Random rd = new Random();
        this.soif = rd.nextInt(this.soif_max);
        this.faim = rd.nextInt(this.faim_max);
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
            System.out.println("je suis un herbivore mort je ne vit plus mort");
        }
    }

    public void cherchePredateur(ArrayList<Carnivore> listeAnimaux) {
        /** Fonction a appeler à tous les tours : l'herbivore reste aux aguets
         * la cible est le predateur le plus proche (si il y en a plusieurs)
         * Entrees : liste des animaux
         * Sorties : void
         *
         */
        // Parcours de la liste des animaux
        for (int counter = 0 ; counter < listeAnimaux.size() ; counter++){
            if (listeAnimaux.get(counter).getEspece() == 1) {
                int dist = (int)calcule_distance(listeAnimaux.get(counter).get_x(), listeAnimaux.get(counter).get_y());
                if ((dist < this.perception)) {
                    this.effroi = true;
                    this.cible = listeAnimaux.get(counter).getId();
                }
            }
        }
    }

    public void fuir_predateur(ArrayList<Carnivore> listeAnimaux){
        /**Denifnit comment un Carnivore poursuit une proie
         * Sa position s'incremente d'une composante de vitesse
         * **/
        if (this.cible != -1) {
            for (int counter = 0; counter < listeAnimaux.size(); counter++) {
                if (listeAnimaux.get(counter).getId() == this.cible) {
                    double a = Math.sqrt(listeAnimaux.get(counter).get_x() * listeAnimaux.get(counter).get_x() + listeAnimaux.get(counter).get_y() * listeAnimaux.get(counter).get_y());
                    this.abscisse -= (int) (this.abscisse - listeAnimaux.get(counter).get_x()) * this.vitesse / a;
                    this.ordonnee -= (int) (this.ordonnee - listeAnimaux.get(counter).get_y()) * this.vitesse / a;
                    System.out.println("Moi l'animal d'ID " + String.valueOf(this.id) + "je ne poursuit une cible");
                }
            }
        }
        if(this.abscisse < 0)
            this.abscisse = 0;
        if(this.abscisse > this.taille_terrain)
            this.abscisse = this.taille_terrain - 1;

        if(this.ordonnee < 0)
            this.ordonnee = 0;
        if(this.ordonnee > this.taille_terrain)
            this.ordonnee = this.taille_terrain - 1;
    }
}
