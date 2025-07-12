package view.animations;

import controller.ApplicationController;
import model.*;

public class EnemyAnimation extends ObjectAnimation{
    public EnemyAnimation(Game game, GameObject enemy, double hSpeed, double vSpeed) {
        super(enemy);
        this.game = game;
        this.enemy = enemy;
        this.hSpeed = hSpeed;
        this.vSpeed = vSpeed;
    }

    @Override
    protected void interpolate(double v) {
        double x = enemy.getX() + hSpeed;
        if (x > game.getGameLauncher().getWIDTH() + enemy.getWIDTH()){
            x = -enemy.getWIDTH();
            if (enemy instanceof ShootingTank)
                ((ShootingTank) enemy).setShot(false);
        }
        else if (x < -enemy.getWIDTH()){
            if (enemy instanceof Mig){
                game.getGameLauncher().getPane().getChildren().remove(enemy);
                this.stop();
            }
            else
                x = game.getGameLauncher().getWIDTH() + enemy.getWIDTH();
        }
        enemy.setX(x);
        if (enemy instanceof ShootingEnemy){
            ShootingEnemy enemy = (ShootingEnemy) this.enemy;
            double y = enemy.getY();
            double planeX = game.getGameLauncher().getPlane().getX();
            double planeY = game.getGameLauncher().getPlane().getY();
            double distance = Math.sqrt((x - planeX) * (x - planeX) + (planeY - y) * (planeY - y));
            double r = User.getLoggedInUser().getGameSettings().getDifficulty() * 125;
            if (distance <= r && !enemy.hasShot())
                enemy.shoot(planeX, planeY);
        }
    }
}
