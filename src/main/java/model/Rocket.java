package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import view.GameLauncher;
import view.animations.AirExplosionAnimation;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Rocket extends GameObject {
    public Rocket(GameLauncher gameLauncher){
        super(ThreadLocalRandom.current().nextDouble(5, gameLauncher.getWIDTH() - 20),
                gameLauncher.getHEIGHT() - 80, 30, 100);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Rocket.class.
                getResource("/images/normal/game objects/rocket.png")).toExternalForm())));
        this.WIDTH = 30;
        this.HEIGHT = 100;
        this.attacksFromAir = false;
        this.explosionAnimation = new AirExplosionAnimation(this,
                gameLauncher.getPane(), gameLauncher.getGame().getTargets(), gameLauncher.getGame());
    }
}
