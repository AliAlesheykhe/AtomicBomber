package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import view.animations.NuclearBombAnimation;
import view.animations.NuclearExplosion;

import java.util.Objects;

public class NuclearBomb extends GameObject{
    public NuclearBomb(double x, double y, int width, int height, Game game) {
        super(x, y, 25, 25);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(NuclearBomb.class.getResource(
                "/images/normal/game objects/nuclear-bomb.png"
        )).toExternalForm())));
        this.explosionAnimation = new NuclearExplosion(this, game);
    }
}
