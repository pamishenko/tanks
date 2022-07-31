package edu.school21.service;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

public class Windows extends Application {

    public void launch() {
        javafx.application.Application.launch();
    }

    //private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    @Override
    public void init() throws Exception {

        System.out.println("Application inits");
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
// установка окна
        primaryStage.setScene(new Scene(root, 1000, 600));
        // фон
        Image field = new Image(getClass().getResourceAsStream("/images/field.png"));
        ImageView imageViewField = new ImageView(field);
        imageViewField.setFitHeight(600);
        imageViewField.setFitWidth(1000);
// игрок 1
        Image player = new Image(getClass().getResourceAsStream("/images/player.png"));
        ImageView imageViewPlayer = new ImageView(player);

        imageViewPlayer.setX(450);
        imageViewPlayer.setY(primaryStage.getScene().getHeight() - 130);

        //  враг

        Image enemy = new Image(getClass().getResourceAsStream("/images/enemy.png"));
        ImageView imageViewEnemy = new ImageView(enemy);

        imageViewEnemy.setX(450);
        imageViewEnemy.setY(primaryStage.getScene().getHeight() - 570);

        // к руту

        root.getChildren().add(imageViewField);
        root.getChildren().add(imageViewPlayer);
        root.getChildren().add(imageViewEnemy);

        // название и иконка

        primaryStage.setTitle("Tanks!");

        InputStream iconStream = getClass().getResourceAsStream("/images/player.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);

    // вывод сцены
        primaryStage.show();






       // primaryStage.show();

//        StackPane stackPane = new StackPane();
//        Image field = new Image(getClass().getResourceAsStream("/images/field.png"));
//        ImageView imageView = new ImageView(field);
//        Button btnPlay = new Button("Play!");
//        stackPane.getChildren().addAll(imageView, btnPlay);

//        Group root = new Group();
////        Scene scene = new Scene(root, 600, 800);
//
//        Image enemy = new Image(getClass().getResourceAsStream("/images/enemy.png"));
//        ImageView imageView = new ImageView(enemy);
//        root.getChildren().add(imageView);
//        primaryStage.setScene();


    }
}