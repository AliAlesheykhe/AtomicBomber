package view.animations;

import javafx.util.Duration;
import model.Game;
import model.Plane;

public class PlaneAnimation extends ObjectAnimation {
    public PlaneAnimation(Plane plane, Game game){
       super(plane);
       this.plane = plane;
       this.game = game;
       this.hSpeed = 2;
       this.vSpeed = 0;
    }
    @Override
    protected void interpolate(double v) {
        double x = plane.getX() + hSpeed;
        double y = plane.getY() + vSpeed;
        if (x > game.getGameLauncher().getWIDTH() + 20)
            x = -20;
        else if (x < -20)
            x = game.getGameLauncher().getWIDTH() + 20;
        if (y >= game.getGameLauncher().getHEIGHT() - 60){
            y = game.getGameLauncher().getHEIGHT() - 60;
            vSpeed = 0;
        }
        else if (y <= 0){
            y = 0;
            vSpeed = 0;
        }
        plane.setX(x);
        plane.setY(y);
    }
}
