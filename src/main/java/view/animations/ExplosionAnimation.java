package view.animations;

import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.GameObject;
import model.Plane;
import model.Rocket;
import view.GameLauncher;

public abstract class ExplosionAnimation extends Transition {
    protected GameObject enemyObject;
    protected Pane pane;
    protected Group targets;
    protected GameLauncher gameLauncher;

    public ExplosionAnimation() {
        setCycleDuration(Duration.millis(500));
        setCycleCount(1);
    }

    @Override
    protected abstract void interpolate(double v);
}
