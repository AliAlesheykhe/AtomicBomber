package view.animations;

import model.Cannon;
import model.Game;
import model.Plane;
import view.GameLauncher;

public class CannonAnimation extends ObjectAnimation{
    private final double vAcceleration = 0.02;
    private final Cannon cannon;
    public CannonAnimation(double hSpeed, double vSpeed, Plane plane, Game game, Cannon cannon){
        super(cannon);
        this.hSpeed = hSpeed;
        this.vSpeed = vSpeed;
        this.plane = plane;
        this.game = game;
        this.cannon = cannon;
    }
    @Override
    protected void interpolate(double v) {
        double x = cannon.getX() + hSpeed;
        double y = cannon.getY() + vSpeed;
        vSpeed += vAcceleration;
        if (x > game.getGameLauncher().getWIDTH() + 50)
            x = -10;
        else if (x < -10)
            x = game.getGameLauncher().getWIDTH() + 10;
        if (y >= game.getGameLauncher().getHEIGHT()){
            y = game.getGameLauncher().getHEIGHT() - 40;
            this.stop();
            cannon.explode();
            game.getGameLauncher().getAnimations().remove(this);
        }
        else if (y < -10){
            this.stop();
            game.getGameLauncher().getAnimations().remove(this);
            game.getGameLauncher().getPane().getChildren().remove(cannon);
        }
        if (cannon.getBoundsInParent().intersects(plane.getBoundsInParent()) && !game.hasEnded()){
            this.stop();
            game.getGameLauncher().getAnimations().remove(this);
            game.getGameLauncher().getPane().getChildren().remove(cannon);
            plane.getObjectAnimation().stop();
            plane.explode();
        }
        cannon.setX(x);
        cannon.setY(y);
    }
}
