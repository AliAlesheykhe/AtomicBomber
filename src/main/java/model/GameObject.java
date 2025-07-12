package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import view.animations.ExplosionAnimation;
import view.animations.ObjectAnimation;

import java.util.Objects;

public class GameObject extends Rectangle {
    protected double WIDTH;
    protected double HEIGHT;
    private boolean exploded = false;
    protected boolean hit;
    protected int kills;
    protected ObjectAnimation objectAnimation;
    protected ExplosionAnimation explosionAnimation;
    protected boolean attacksFromAir;
    public GameObject(double x, double y, int width, int height) {
        super(width, height);
        this.WIDTH = width;
        this.HEIGHT = height;
        this.hit = false;
        setX(x);
        setY(y);
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }


    public void explode(){
        this.exploded = true;
        if (!User.getLoggedInUser().getGameSettings().isMuted()){
            Media explosionSound = new Media(Objects.requireNonNull(GameObject.class.getResource("/tracks/explosion.wav")).toString());
            MediaPlayer explosionPlayer = new MediaPlayer(explosionSound);
            explosionPlayer.setAutoPlay(true);
        }
        this.explosionAnimation.play();
    }

    public int getKills() {
        return kills;
    }

    public boolean isAttacksFromAir() {
        return attacksFromAir;
    }

    public ObjectAnimation getObjectAnimation() {
        return objectAnimation;
    }

    public void setObjectAnimation(ObjectAnimation objectAnimation) {
        this.objectAnimation = objectAnimation;
    }

    public ExplosionAnimation getExplosionAnimation() {
        return explosionAnimation;
    }

    public boolean hasExploded(){
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }
}
