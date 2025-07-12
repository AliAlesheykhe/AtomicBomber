package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import view.GameLauncher;
import view.animations.SmallExplosionGroundAnimation;

import java.util.Objects;

public class Tank extends GameObject {
    public Tank(Pane pane, GameLauncher gameLauncher){
        super(pane.getWidth(), pane.getHeight() - 30, 60, 30);
        this.setFill(new ImagePattern(new Image
                (Objects.requireNonNull(Tank.class
                        .getResource("/images/normal/game objects/tank.png"))
                        .toExternalForm())));
        this.kills = 5;
        this.attacksFromAir = false;
        this.explosionAnimation = new SmallExplosionGroundAnimation(this,
                pane, gameLauncher.getGame().getTargets(), gameLauncher.getGame());
    }
}
