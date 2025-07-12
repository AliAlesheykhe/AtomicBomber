package model;

import controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import view.GameLauncher;
import view.animations.CannonAnimation;
import view.animations.SmallExplosionGroundAnimation;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class ShootingTank extends ShootingEnemy{
    private final ImagePattern leftMovingTank = new ImagePattern(new Image
            (Objects.requireNonNull(ShootingTank.class.getResource
            ("/images/normal/game objects/shootingTankLeft.png")).toExternalForm()));

    private final ImagePattern rightMovingTank = new ImagePattern(new Image
            (Objects.requireNonNull(ShootingTank.class.getResource
                    ("/images/normal/game objects/shootingTankRight.png")).toExternalForm()));

    public ShootingTank(Pane pane, GameLauncher gameLauncher, boolean randomLocation) {
        super(-60, pane.getHeight() - 50, 60, 60);
        if (randomLocation)
            setX(ThreadLocalRandom.current().nextDouble
                    (0, gameLauncher.getWIDTH() - 60));
        this.setFill(rightMovingTank);
        this.kills = 7;
        this.attacksFromAir = false;
        this.explosionAnimation = new SmallExplosionGroundAnimation(this,
                pane, gameLauncher.getGame().getTargets(), gameLauncher.getGame());
    }
    public void shoot(double planeX, double planeY) {
        double tankX = getX();
        boolean rightMoving = true;
        if (tankX < planeX)
            setFill(rightMovingTank);
        else if (tankX > planeX){
            setFill(leftMovingTank);
            rightMoving = false;
        }
        double cannonX = getX();
        double cannonY = getY();
        double hSpeed = -5;
        if (rightMoving){
            cannonX += 60;
            hSpeed = 5;
        }
        Cannon cannon = new Cannon(cannonX, cannonY);
        ApplicationController.gameLauncher.getPane().getChildren().add(cannon);
        CannonAnimation cannonAnimation = new CannonAnimation(hSpeed, -4,
                ApplicationController.gameLauncher.getPlane(),
                ApplicationController.gameLauncher.getGame(), cannon);
        ApplicationController.gameLauncher.getAnimations().add(cannonAnimation);
        cannonAnimation.play();
        shot = true;
    }
}
