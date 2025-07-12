package model;

import controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import view.animations.SmallExplosionGroundAnimation;

import java.util.Objects;

public class Cannon extends GameObject{
    public Cannon(double x, double y) {
        super(x, y, 20, 20);
        this.setFill(new ImagePattern(new Image(Objects.requireNonNull
                (Bomb.class.getResource("/images/normal/game objects/cannon.png")).toExternalForm())));
        this.explosionAnimation = new SmallExplosionGroundAnimation(this, ApplicationController.gameLauncher.getPane());
    }
}
