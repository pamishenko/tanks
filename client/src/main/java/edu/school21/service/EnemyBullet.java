package edu.school21.service;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyBullet {

    private Image enemyBullet = new Image(getClass().getResourceAsStream("/images/enemyBullet.png"));
    private ImageView imageViewBulletEn = new ImageView(enemyBullet);



    public EnemyBullet(ImageView player) {
        imageViewBulletEn.setX(player.getX() + player.getImage().getWidth() / 2  - enemyBullet.getWidth()/2);
        imageViewBulletEn.setY(player.getY() + 12);
    }

    public ImageView getImageViewBulletEn() {
        return imageViewBulletEn;
    }
}

