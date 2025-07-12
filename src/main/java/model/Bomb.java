package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.GameLauncher;
import view.animations.BombAnimation;
import view.animations.SmallExplosionGroundAnimation;

import java.util.Objects;

public class Bomb extends GameObject {
    private final double WIDTH = 10;
    private final double HEIGHT = 10;

    private BombAnimation bombAnimation;

    public Bomb(Plane plane, GameLauncher gameLauncher){
        super(10, 10, 10, 10);
        this.setX(plane.getX());
        this.setY(plane.getY());
        this.setFill(new ImagePattern(new Image(Objects.requireNonNull
                (Bomb.class.getResource("/images/normal/game objects/bomb.png")).toExternalForm())));
        this.explosionAnimation = new SmallExplosionGroundAnimation(this, gameLauncher.getPane());
    }

    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public BombAnimation getBombAnimation() {
        return bombAnimation;
    }

    public void setBombAnimation(BombAnimation bombAnimation) {
        this.bombAnimation = bombAnimation;
    }
}
