package sample;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

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
                    ecran_selection(primaryStage);
                }
            });

            primaryStage.show();
        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    public void ecran_selection(Stage primaryStage){
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

        Button bouton_validation = new Button("valider");
        bouton_validation.setOnAction(e -> gen_controleur(primaryStage, spin1.getValue(),spin2.getValue(), spin3.getValue(), spin4.getValue()));

        VBox vbox = new VBox(l1, spin1, l2, spin2, l3, spin3, l4, spin4, bouton_validation);

        // set spacing
        vbox.setSpacing(10);

        // create a image
        Image image = new Image(PorteurImg.class.getResourceAsStream("background.png"));

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

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

    public void gen_controleur(Stage primaryStage,int x, int y, int z, int t){
        /**generer les annimaux**/
        this.controller = new Controller(x,y,z,t,50, 500);
        GridPane vbox = new GridPane();
        Button b = new Button("suite");
        b.setTranslateX(250);
        b.setTranslateY(250);
        b.setOnAction(e -> un_tour(primaryStage));
        vbox.getChildren().add(b);

        ArrayList<Carnivore> c = this.controller.get_list_carnivore();

        for (int i =0 ; i < c.size(); i++)
            vbox.getChildren().add(new PorteurImg(c.get(i).get_x(), c.get(i).get_y(), "lion.png"));
        ArrayList<Herbivore> h = this.controller.get_list_herbivore();
        for (int i =0 ; i < h.size(); i++)
            vbox.getChildren().add(new PorteurImg(h.get(i).get_x(), h.get(i).get_y(), "buffalo.png"));

        ArrayList<Point_eau> p = this.controller.get_list_Point_eau();
        for (int i =0 ; i < p.size(); i++)
            vbox.getChildren().add(new Porteur_img_eau(p.get(i).get_abscisse(), p.get(i).get_ordonnee(), (int)p.get(i).getRayon()));

        Scene scene2 = new Scene(vbox, 500, 500);

        // create a image
        Image image = new Image(PorteurImg.class.getResourceAsStream("background.png"));

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        background = new Background(backgroundimage);

        // set background
        vbox.setBackground(background);

        primaryStage.setScene(scene2);
        primaryStage.show();
    }

    public void un_tour(Stage primaryStage){
        /**faire un tour de boucle d'evolution des animaux**/
        this.controller.unTour();
        GridPane vbox = new GridPane();
        
        ArrayList<Point_eau> p = this.controller.get_list_Point_eau();
        for (int i =0 ; i < p.size(); i++)
            vbox.getChildren().add(new Porteur_img_eau(p.get(i).get_abscisse(), p.get(i).get_ordonnee(), (int)p.get(i).getRayon()));

        ArrayList<Carnivore> c = this.controller.get_list_carnivore();
        for (int i =0 ; i < c.size(); i++) {
            if(!c.get(i).est_mort())
            vbox.getChildren().add(new PorteurImg(c.get(i).get_x(), c.get(i).get_y(), "lion.png"));
        }

        ArrayList<Herbivore> h = this.controller.get_list_herbivore();
        for (int i =0 ; i < h.size(); i++) {
            if(!h.get(i).est_mort())
            vbox.getChildren().add(new PorteurImg(h.get(i).get_x(), h.get(i).get_y(), "buffalo.png"));
        }

        Button b = new Button("suite");
        b.setOnAction(e -> un_tour(primaryStage));
        vbox.getChildren().add(b);

        Scene scene2 = new Scene(vbox, 500, 500);

        // create a image
        Image image = new Image(PorteurImg.class.getResourceAsStream("background.png"));

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        background = new Background(backgroundimage);
        // set background
        vbox.setBackground(background);

        primaryStage.setScene(scene2);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
