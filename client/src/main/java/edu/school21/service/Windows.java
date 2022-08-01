package edu.school21.service;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;

public class Windows extends Application implements KeyListener {

    public char getKey() {
        return key;
    }

    char key;
    public void launch() {
        javafx.application.Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        key = '*';
        primaryStage.setTitle("Tanks!");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);

        InputStream iconStream = getClass().getResourceAsStream("/images/player.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);

        Label helloWorldLabel = new Label("Hello world!");
        helloWorldLabel.setAlignment(Pos.CENTER);
        Scene primaryScene = new Scene(helloWorldLabel);
        primaryStage.setScene(primaryScene);

        primaryStage.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        key = e.getKeyChar();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key = e.getKeyChar();
    }
}
