package sample;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {
    Controller controller;
    Background background;
    PorteurImg list_porteur_img;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {

            // set title for the stage
            primaryStage.setTitle("creating Background");

            // add the label, text field and button
            HBox hbox = new HBox();

            // set spacing
            hbox.setSpacing(10);

            // set alignment for the HBox
            hbox.setAlignment(Pos.CENTER);

            // create a scene
            Scene scene = new Scene(hbox, 500, 500);

            // create a input stream
            FileInputStream input = new FileInputStream("src/background.png");
            FileInputStream carnivore = new FileInputStream("src/buffalo.png");
            FileInputStream herbivore = new FileInputStream("src/background.png");
            FileInputStream charognard = new FileInputStream("src/background.png");

            // create a image
            Image image = new Image(input);

            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);

            // create Background
            this.background = new Background(backgroundimage);

            // set background
            hbox.setBackground(background);

            // set the scene
            primaryStage.setScene(scene);

            primaryStage.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent keyEvent) {
                    // add the label, text field and button
                    Label l1 = new Label("nombre de carnivore");
                    Spinner<Integer> spin1 = new Spinner<>();
                    spin1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
                    spin1.setEditable(true);

                    Label l2 = new Label("nombre d'herbivore");
                    Spinner<Integer> spin2 = new Spinner<>();
                    spin2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
                    spin2.setEditable(true);

                    Label l3 = new Label("nombre de charognard");
                    Spinner<Integer> spin3 = new Spinner<>();
                    spin3.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
                    spin3.setEditable(true);

                    Label l4 = new Label("nombre de point d'eau");
                    Spinner<Integer> spin4 = new Spinner<>();
                    spin4.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
                    spin4.setEditable(true);

                    PorteurImg p = new PorteurImg(100, 100, carnivore);

                    Button bouton_validation = new Button("valider");

                    VBox vbox = new VBox(l1, spin1, l2, spin2, l3, spin3, bouton_validation,p);

                    // set spacing
                    vbox.setSpacing(10);

                    // set alignment for the HBox
                    vbox.setAlignment(Pos.CENTER);
                    background = new Background(backgroundimage);

                    // set background
                    vbox.setBackground(background);

                    // create a scene
                    Scene scene2 = new Scene(vbox, 500, 500);
                    primaryStage.setScene(scene2);
                    primaryStage.show();
                }
            });

            primaryStage.show();
        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void gen_controleur(int x, int y, int z, int t){
        /**generer les annimaux**/
        this.controller.gen_ecosyst(x,y,z,t,7, 500);

    }

    public void un_tour(){
        /**faire un tour de boucle d'evolution des animaux**/
        this.controller.unTour();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
