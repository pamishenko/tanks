package edu.school21.service;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyBullet {

    private Image bullet = new Image(getClass().getResourceAsStream("/images/enemyBullet.png"));
    ImageView imageViewBulletEn = new ImageView(bullet);



    public EnemyBullet(ImageView player) {
        imageViewBulletEn.setX(player.getX() + player.getImage().getWidth() / 2  - bullet.getWidth()/2);
        imageViewBulletEn.setY(player.getY() - 12);
    }

}

