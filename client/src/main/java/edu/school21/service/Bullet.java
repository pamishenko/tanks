package edu.school21.service;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Bullet {

    private Image bullet = new Image(getClass().getResourceAsStream("/images/playerBullet.png"));
    private ImageView imageViewBullet = new ImageView(bullet);



    public Bullet(ImageView player) {
        imageViewBullet.setX(player.getX() + player.getImage().getWidth() / 2  - bullet.getWidth()/2);
        imageViewBullet.setY(player.getY() - 12);
    }

    public ImageView getImageViewBullet() {
        return imageViewBullet;
    }
}
