package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import view.GameLauncher;
import view.animations.SmallExplosionGroundAnimation;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Tree extends GameObject {
    public Tree(GameLauncher gameLauncher){
        super(ThreadLocalRandom.current().nextDouble(5, gameLauncher.getWIDTH() - 10),
                gameLauncher.getHEIGHT() - 200, 40, 80);
        setFill(new ImagePattern(new Image
                (Objects.requireNonNull(Tree.class.getResource("/images/normal/game objects/" +
                        "tree.png")).toExternalForm())));
        this.kills = 0;
        this.explosionAnimation = new SmallExplosionGroundAnimation(this,
                gameLauncher.getPane(), gameLauncher.getGame().getTargets(), gameLauncher.getGame());
    }
}
