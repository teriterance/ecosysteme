package sample;

public class Point_eau {
    /**Le nombre de point d'eau**/
    protected static int nb_point_eau = 0;

    /**les variables de classe**/
    /**coordone du centre du point d'eau et son rayon**/
    private int abscisse;
    private int ordonnee;
    private float rayon;
    private int id_point_eau = -1;

    /*constructeur**/
    public Point_eau(int x, int y, float rayon){
        this.abscisse = x;
        this.ordonnee = y;
        this.rayon = rayon;
        nb_point_eau ++;
        id_point_eau += nb_point_eau;
        System.out.println("cretation d'un nouveau point d'eau en position " +String.valueOf(this.abscisse) +", "+ String.valueOf(this.ordonnee) +" et de rayon "+String.valueOf(this.rayon));
    }

    /**definition des geters **/
    public float getRayon() {
        //System.out.println("Le point d'eau " + String.valueOf(this.id_point_eau) + " donne son Rayon");
        return  this.rayon;
    }
    public int get_abscisse(){
        //System.out.println("Le point d'eau " + String.valueOf(this.id_point_eau) + " donne son abscisse");
        return  this.abscisse;
    }
    public int get_ordonnee(){
        //System.out.println("Le point d'eau " + String.valueOf(this.id_point_eau) + " donne son ordonnee");
        return  this.ordonnee;
    }
    public int get_id_point_eau(){
        //System.out.println("Je te donne mon id de point d'eau " + String.valueOf(this.id_point_eau));
        return  this.id_point_eau; }
}
