package sample;

public class Poin_eau {
    /**Le nombre de point d'eau**/
    private static  int nb_point_eau = 0;

    /**les variables de classe**/
    /**coordone du centre du point d'eau et son rayon**/
    private int abscisse;
    private int ordonnee;
    private float rayon;
    private int id_point_eau = -1;

    /*constructeur**/
    public Poin_eau(int x, int y, float rayon){
        this.abscisse = x;
        this.ordonnee = y;
        this.rayon = rayon;
        id_point_eau += nb_point_eau;
        nb_point_eau ++;
    }

    /**definition des geters **/
    public float getRayon() {   return  this.rayon;  }
    public int get_abscisse(){  return  this.abscisse;  }
    public int get_ordonnee(){ return  this.ordonnee; }
    public int get_id_point_eau(){ return  this.id_point_eau; }
}
