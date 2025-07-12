package view.animations;

import controller.ApplicationController;
import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.util.Duration;
import model.Plane;
import model.Rocket;
import view.EndGameMenu;
import view.GameLauncher;
import view.animations.ObjectAnimation;

import java.util.Objects;

public class RocketAnimation extends ObjectAnimation {
    private final Rocket rocket;
    private final Plane plane;
    private final GameLauncher gameLauncher;

    public RocketAnimation(Rocket rocket, Plane plane, GameLauncher gameLauncher) {
        super(rocket);
        this.rocket = rocket;
        this.plane = plane;
        this.gameLauncher = gameLauncher;
        this.setCycleDuration(Duration.seconds(5));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        double y = rocket.getY() - 4;
        if (y < -rocket.getHEIGHT())
            gameLauncher.getPane().getChildren().remove(rocket);
        rocket.setY(y);
        if (rocket.getBoundsInParent().intersects(plane.getBoundsInParent()) && !gameLauncher.getGame().hasEnded()){
            plane.getObjectAnimation().stop();
            rocket.getObjectAnimation().stop();
            rocket.explode();
            plane.explode();
        }
    }
}
