package sample;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class PorteurImg extends Parent {
    /**Cette classe permet de porter les images des animaux en corespondance avec leur type**/
    private String image_vivant;
    private String image_mort;

    public PorteurImg(int x, int y, String nom_image){
        /**Constructeur**/
        ImageView fond_img = new ImageView(new Image(PorteurImg.class.getResourceAsStream(nom_image)));
        fond_img.setFitHeight(20);
        fond_img.setPreserveRatio(true);
        this.resize(20,20);

        this.getChildren().add(fond_img);
        this.setTranslateY(y);
        this.setTranslateX(x);
    }

    void setImage_vivant(String image){
        this.image_vivant = image;
    }

    void setImage_mort(String image){
        this.image_mort = image;
    }

    void update(int x, int y){
        setTranslateX(x);
        setTranslateY(y);
    }
}
