package model;

import controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import view.GameLauncher;
import view.animations.CannonAnimation;
import view.animations.EnemyAnimation;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Mig extends ShootingEnemy{
    public Mig(GameLauncher gameLauncher) {
        super(gameLauncher.getWIDTH() + 50,
                ThreadLocalRandom.current()
                .nextDouble(10, gameLauncher.getHEIGHT() - 150),
                50, 50);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource
                ("/images/normal/game objects/mig.png")).toExternalForm())));
        objectAnimation = new EnemyAnimation(gameLauncher.getGame(),
                this, -3, 0);
    }
    @Override
    public void shoot(double planeX, double planeY) {
        double migX = getX();
        double migY = getY();
        double speed = 15;
        double hSpeed = 0;
        double vSpeed = 0;
        double hDistance = planeX - migX;
        double vDistance = planeY - migY;
        if (vDistance != 0){
            double angle = Math.atan(hDistance / vDistance);
            hSpeed = Math.sin(angle) * speed;
            vSpeed = Math.cos(angle) * speed;
        }
        else hSpeed = speed;
        if (hSpeed * hDistance < 0)
            hSpeed *= -1;
        if (vSpeed * vDistance < 0)
            vSpeed *= -1;
        Cannon cannon = new Cannon(migX, migY);
        ApplicationController.gameLauncher.getPane().getChildren().add(cannon);
        CannonAnimation cannonAnimation = new CannonAnimation(hSpeed, vSpeed,
                ApplicationController.gameLauncher.getPlane(),
                ApplicationController.gameLauncher.getGame(), cannon);
        ApplicationController.gameLauncher.getAnimations().add(cannonAnimation);
        cannonAnimation.play();
        shot = true;
    }
}
