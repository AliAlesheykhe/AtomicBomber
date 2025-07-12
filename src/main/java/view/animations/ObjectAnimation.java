package view.animations;


import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.GameObject;
import model.Game;
import model.Plane;

public abstract class ObjectAnimation extends Transition {
    protected final int duration = 50;
    private Pane pane;
    protected Plane plane;
    protected GameObject enemy;
    protected double hSpeed;
    protected double vSpeed;
    protected Game game;
    protected GameObject gameObject;
   public ObjectAnimation(GameObject gameObject){
       this.setCycleCount(-1);
       this.setCycleDuration(Duration.millis(500));
       this.gameObject = gameObject;
   }

    @Override
    protected abstract void interpolate(double v);

    public void sethSpeed(double hSpeed) {
        this.hSpeed = hSpeed;
    }

    public void setvSpeed(double vSpeed) {
        this.vSpeed = vSpeed;
    }

    public double gethSpeed() {
        return hSpeed;
    }

    public double getvSpeed() {
        return this.vSpeed;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
