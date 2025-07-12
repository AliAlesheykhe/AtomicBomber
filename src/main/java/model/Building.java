package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import view.GameLauncher;
import view.animations.SmallExplosionGroundAnimation;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Building extends GameObject {
    public Building(GameLauncher gameLauncher){
        super(ThreadLocalRandom.current().nextDouble(5, gameLauncher.getWIDTH() - 150),
                gameLauncher.getHEIGHT() - 150, 150, 150);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Building.class.
                getResource("/images/normal/game objects/building.png")).toExternalForm())));
        this.WIDTH = 150;
        this.HEIGHT = 150;
        this.kills = 10;
        this.attacksFromAir = false;
        this.objectAnimation = null;
        this.explosionAnimation = new SmallExplosionGroundAnimation(this,
                gameLauncher.getPane(), gameLauncher.getGame().getTargets(), gameLauncher.getGame());
    }
}
