package sample;

public class Herbivore extends Animal{
    public Herbivore(int nbFaim, int nbSoif, int x, int y){
        super(nbFaim, nbSoif, x, y, 0, 0, 0, 0, 0, 0, 0, "Herbivore");
    }

    public void manger(){
        /**un herbivre mage 10 a la fois**/
        super.manger(10);
    }

    public void recois_attaque(Carnivore a){
        /**ce qui se passe quand on attaque l'herbivore**/
        if (!this.est_mort()){
            super.recois_attaque(a.getattaque());
            if(!this.est_mort()){
                /**si il n'est pas mort il riposte**/
                a.recois_attaque(this);
            }
        }
    }

    //public -+
}
