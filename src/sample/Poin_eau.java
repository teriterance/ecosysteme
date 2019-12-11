package sample;

public class Poin_eau {
    private int abscisse;
    private  int ordonnee;
    private  float rayon;

    public  Poin_eau(int x, int y, float rayon){
        this.abscisse = x;
        this.ordonnee = y;
        this.rayon = rayon;
    }

    //pas encore de constructeur
    public float getRayon() {return  this.rayon;}
    public int get_abscisse(){
        return  this.abscisse;
    }
    public int get_ordonnee(){
        return  this.ordonnee;
    }
}
