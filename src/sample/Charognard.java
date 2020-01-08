package sample;

import java.util.ArrayList;

public class Charognard extends Animal {

    /** id de la cible -cadavre- (différent de la valeur par défaut -1) **/
    protected int cible = -1;

    //A MODIFIER AVEC LES VALEURS PAR DEFAUT :
    public Charognard(int x, int y, int taille_ter) {
        super(100, 100, 100, 100, x, y, 5, 100, 100, 100, 100, 10, 100, 1, 5, taille_ter);
    }
    public boolean est_en_poursuite(){
        /**permet de savoir si le charognard a deja une cible (cadavre) dans sa ligne de mire**/
        return  this.cible != -1;
    }

    public void chercheCadavre(ArrayList <Carnivore> listeCarnivore, ArrayList <Herbivore> listeHerbivore) {
        /** Fonction a appeler des que le charognard cherche un cadavre à manger:
         * Il choisit sa cible en prenant la proie avec le produit endurance*distance le plus faible sensiblement egale a une valeur seuil
         * Entrees : liste des animaux
         * Sorties : void
         */

        if (this.est_mort()){

            if(!this.est_en_poursuite()) {
                System.out.println("Moi l'animal d'ID " + String.valueOf(this.id) + " je recherche au tour de moi une cible");
                /** enduranceCible*distance min on peut jouer sur ceci pour augmenter le rayon d'action **/
                double seuil = 10;

                // Parcours de la liste des animaux
                for (int count = 0; count < listeCarnivore.size(); count++) {
                    if (listeCarnivore.get(count).getEspece() == 2) {
                        double dist = calcule_distance(listeCarnivore.get(count).get_x(), listeCarnivore.get(count).get_y());
                        if (dist < this.perception) {
                            double valProduit = dist * listeCarnivore.get(count).get_endurance();
                            if (valProduit < seuil) {
                                this.cible = listeCarnivore.get(count).getId();
                                seuil = valProduit;
                            }
                        }
                    }
                }
                if (this.cible == -1) {
                    // Parcours de la liste des animaux
                    for (int count = 0; count < listeCarnivore.size(); count++) {
                        if (listeCarnivore.get(count).getEspece() == 2) {
                            double dist = calcule_distance(listeCarnivore.get(count).get_x(), listeCarnivore.get(count).get_y());
                            if (dist < this.perception) {
                                double valProduit = dist * listeCarnivore.get(count).get_endurance();
                                if (valProduit < seuil) {
                                    this.cible = listeCarnivore.get(count).getId();
                                    seuil = valProduit;
                                }
                            }
                        }
                    }
                }
                if (this.cible != -1) {
                    System.out.println("j'en ai trouve une");
                } else {
                    System.out.println("je n'en ai pas trouve");
                }
            }
        }else {
            System.out.println("Moi l'animal d'ID " + String.valueOf(this.id) +" je suis deja en chasse");
        }
    }

    public void attaquer_cible(ArrayList <Animal> listeAnimaux) {
        /** fonction pour attaquer la cible définie par l'id **/
        for (int count = 0 ; count < listeAnimaux.size() ; count++) {

            if (listeAnimaux.get(count).getId() == this.cible) {
                /**on verifie d'abord l etat de la cible */
                if(listeAnimaux.get(count).est_mort()){
                    this.cible = -1;
                    System.out.println ("J'ai trouve une cible je peux manger");
                    double a = Math.sqrt( Math.pow(this.ordonnee - listeAnimaux.get(count).get_y(),2) + Math.pow(this.abscisse - listeAnimaux.get(count).get_x(),2));
                    if( a < this.rayon_action) {
                        System.out.println("Moi l'animal d'ID " + String.valueOf(this.id) + "je suis un charognard et je mange les cadavres");
                        this.manger(this.faim_max);
                        listeAnimaux.get(count).meurt();
                        this.cible = -1;
                    }
                }
            }
        }
    }

    public void vivre_charo(ArrayList<Point_eau> list_eaux, ArrayList<Carnivore> listeCarnivore, ArrayList<Herbivore> listeHerbivore) {
        /** fonction a laquel on fait appel a chaque tour pour que le charognard vive **/
        /** L'animal a de plus en plus faim chaque tour si il le peut **/
        if ((this.soif >= 0) || (this.faim >= 0)) {
            plusfaimplussoif(2, 1);
        }

        /**SI MEURT DE faim ou soif **/
        if ((this.soif==0) || (this.faim==0)) {
            meurt_de_faimsoif(2, 1);
        }

        /** on défini la priorite faim ou soif **/
        /** si l'animal a faim et soif il doit décider ce qu'il prefere : manger ou boire **/
        int prio = 0; // = 1 si prio est de manger, 2 si prio est de boire, 0 si ni faim ni soif
        if ((this.soif < this.valcrit) || (this.faim < this.valcrit)) {
            if (this.soif <= this.faim){
                prio = 2;
                /** le charognard a besoin de boire */
            }else {
                prio = 1; /**le charognard a besoin de manger */
            }
        }

        /** si la soif est prioritaire : **/
        if (prio == 2) {
            /** initialisation du point d'eau à viser s'il n'existe pas **/
            if (this.position_eau_x != -1 && this.position_eau_y != -1){
                // A DEFINIR : initialisation du point d'eau visé
                if(deplace_vers_point_eau()){
                    this.boire(100);
                    System.out.println("je bois de l'eau");
                }
            }else{
                chercher_a_boire(list_eaux);
                this.bougerAleatoirement();
            }
        }
        /** SI FAIM : **/
        if (prio == 1) {
            if (this.cible == -1) {
                System.out.println("je cherche  " + String.valueOf(this.cible));
                this.chercheCadavre(listeCarnivore, listeHerbivore);
            }
            else {
                this.attaquer_cible(listeCarnivore, listeHerbivore);
                if(this.cible != -1) {
                    //si la cible n'a pas ete attaque
                    this.poursuivre(listeCarnivore, listeHerbivore); //A DEFINIR
                }
            }
        }
        /** SI NI FAIM NI SOIF : **/
        if (prio == 0){
            this.bougerAleatoirement(); //A DEFINIR
        }
    }

}