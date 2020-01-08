package sample;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Porteur_img_eau extends Parent {
    /**Cette classe permet de positionner un point d'eau**/
    public Porteur_img_eau(int x, int y, int rayon){
        Circle fond_eau = new Circle(x,y, rayon);
        fond_eau.setFill(Color.BLUE);
        this.getChildren().add(fond_eau);
        this.setLayoutX(x);
        this.setLayoutY(y);

    }
}
