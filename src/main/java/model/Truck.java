package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import view.GameLauncher;
import view.animations.SmallExplosionGroundAnimation;

import java.util.Objects;

public class Truck extends GameObject {
    public Truck(Pane pane, GameLauncher gameLauncher) {
        super(-40, pane.getHeight() - 35, 40, 40);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Truck.class.getResource(
                "/images/normal/game objects/truck.png")).toExternalForm())));
        this.kills = 3;
        this.attacksFromAir = false;
        this.explosionAnimation = new SmallExplosionGroundAnimation(this,
                pane, gameLauncher.getGame().getTargets(), gameLauncher.getGame());
    }
}
