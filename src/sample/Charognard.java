package sample;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

public class Charognard extends Animal {

    /** id de la cible -cadavre- (différent de la valeur par défaut -1) **/
    protected int cible = -1;
    protected int ciblex = 0;
    protected int cibley = 0;

        //A MODIFIER AVEC LES VALEURS PAR DEFAUT :
    public Charognard(int nbFaim, int nbSoif, int x, int y, int attqu, int endur,int vitMax, int fMax, int sMax, int prcptn, int decom, String espece) {

        super(100, 100, 100, 100, x, y, 5, 100, 100, 100, 100, 10, 100, 1, 5, 100);    }

}
