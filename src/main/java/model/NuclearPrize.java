package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Objects;

public class NuclearPrize extends GameObject{
    public NuclearPrize(double x, double y, int width, int height) {
        super(x, y, width, height);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(NuclearPrize.class.getResource
                ("/images/normal/game objects/nuclearPrize.png")).toExternalForm())));
    }

}
