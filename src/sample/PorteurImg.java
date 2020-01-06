package sample;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class PorteurImg extends Parent {
    private int id;
    private String image_vivant;
    private String image_mort;

    public PorteurImg(int x, int y, FileInputStream stream){
        ImageView fond_img = new ImageView(new Image(PorteurImg.class.getResourceAsStream("buffalo.png")));
        fond_img.setFitHeight(40);
        fond_img.setPreserveRatio(true);

        this.getChildren().add(fond_img);
        this.setTranslateY(y);
        this.setTranslateX(x);
    }

    int get_id(){
        return this.id;
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
