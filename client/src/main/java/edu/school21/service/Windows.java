package edu.school21.service;

import javafx.animation.AnimationTimer;
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
import java.util.ArrayList;
import java.util.HashMap;

public class Windows extends Application {

    private int winWidth = 1200;
    private int winHeight = 1200;
    private Bullet bul;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private Group root = new Group();
    Scene scene = new Scene(root, winWidth, winHeight);//??????

    int score = 100;


    private Image player = new Image(getClass().getResourceAsStream("/images/player.png"));
    ImageView imageViewPlayer = new ImageView(player);


    public Windows() {}

    public void launch() {
        javafx.application.Application.launch();
    }

    @Override
    public void init() throws Exception {

//        System.out.println("Application inits");
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // чтобы окно нельзя было мышкой растянуть

// перехват нажатий клавиатуры

        scene.setOnKeyPressed(event->keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event->  keys.put(event.getCode(), false));

// фон
        Image field = new Image(getClass().getResourceAsStream("/images/field.png"));
        ImageView imageViewField = new ImageView(field);
        imageViewField.setFitHeight(winHeight);
        imageViewField.setFitWidth(winWidth);
// игрок 1

        imageViewPlayer.setX(winWidth/2 - player.getWidth()/2);
        imageViewPlayer.setY(primaryStage.getScene().getHeight() - 200);

//  враг

        Image enemy = new Image(getClass().getResourceAsStream("/images/enemy.png"));
        ImageView imageViewEnemy = new ImageView(enemy);

        imageViewEnemy.setX(winWidth/2 - enemy.getWidth()/2);
        imageViewEnemy.setY(primaryStage.getScene().getHeight() - (winHeight - 80));

// бордюр и жизнь
        Image border = new Image(getClass().getResourceAsStream("/images/border.png"));
        Image life = new Image(getClass().getResourceAsStream("/images/life.png"));

//  бордюр игрока
        ImageView imageViewLifePl = new ImageView(life);
        imageViewLifePl.setFitHeight(winHeight/30);
        imageViewLifePl.setFitWidth(winWidth/4);

        ImageView imageViewBorderPl = new ImageView(border);
        imageViewBorderPl.setFitHeight(winHeight/30);
        imageViewBorderPl.setFitWidth(winWidth/4);

        imageViewLifePl.setX(20);
        imageViewLifePl.setY(primaryStage.getScene().getHeight() - 60);
        imageViewBorderPl.setX(20);
        imageViewBorderPl.setY(primaryStage.getScene().getHeight() - 60);

// бордюр противника
        ImageView imageViewLifeEn = new ImageView(life);
        imageViewLifeEn.setFitHeight(winHeight/30);
        imageViewLifeEn.setFitWidth(winWidth/4);

        ImageView imageViewBorderEn = new ImageView(border);
        imageViewBorderEn.setFitHeight(winHeight/30);
        imageViewBorderEn.setFitWidth(winWidth/4);

        imageViewLifeEn.setX(scene.getWidth() - imageViewBorderEn.getFitWidth() - 20);
        imageViewLifeEn.setY(20);
        imageViewBorderEn.setX(scene.getWidth() - imageViewBorderEn.getFitWidth() - 20);
        imageViewBorderEn.setY(20);

// к руту

        root.getChildren().add(imageViewField);
        root.getChildren().add(imageViewPlayer);
        root.getChildren().add(imageViewEnemy);

        root.getChildren().add(imageViewLifePl);
        root.getChildren().add(imageViewBorderPl);

        root.getChildren().add(imageViewLifeEn);
        root.getChildren().add(imageViewBorderEn);

// название и иконка

        primaryStage.setTitle("Tanks!");

        InputStream iconStream = getClass().getResourceAsStream("/images/player.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                bulletAnimation();
            }
        };
        timer.start();

        // вывод сцены
        primaryStage.show();

    }

    public void bulletAnimation() {
        bullets.forEach((bon) -> {
            bon.imageViewBullet.setY(bon.imageViewBullet.getY() - 10);
            if(imageViewPlayer.getBoundsInParent().intersects(bon.imageViewBullet.getBoundsInParent())) {
                bon = null;
//                score -= 5;
//                System.out.println(score);
//                if (score <= 0) {
//                    System.out.println("THE END");
//                    System.exit(0);
//                }
            }
            if (bon.imageViewBullet.getY() <= 10) { //bon.imageViewBullet.getY() ==
                root.getChildren().remove(bon.imageViewBullet);
            }
        });
    }

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    public void update () {
        if (isPressed(KeyCode.RIGHT) && imageViewPlayer.getX() < scene.getWidth() - player.getWidth() - 10) {
//            motionRight ('?');
            imageViewPlayer.setX(imageViewPlayer.getX() + 5);
        }
        else if (isPressed(KeyCode.LEFT) && imageViewPlayer.getX() >= 10) {
//            motionLeft('!');
            imageViewPlayer.setX(imageViewPlayer.getX() - 5);
        }
        else if (isPressed(KeyCode.SPACE)) {
            keys.replace(KeyCode.SPACE, false);
            bul = new Bullet(imageViewPlayer);
            bullets.add(bul);
            root.getChildren().add(bul.imageViewBullet);

        }
    }

//    public void motionRight (char ch) {
//        imageViewPlayer.setX(imageViewPlayer.getX() + 5);
//        System.out.println(ch);
//    }
//
//    public void motionLeft(char ch) {
//        imageViewPlayer.setX(imageViewPlayer.getX() - 5);
//        System.out.println(ch);
//    }

}