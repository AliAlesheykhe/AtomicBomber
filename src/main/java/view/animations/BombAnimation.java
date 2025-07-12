package view.animations;


import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.*;
import view.GameLauncher;

import javax.jws.soap.SOAPBinding;
import java.util.Objects;

public class BombAnimation extends ObjectAnimation {
    private Bomb bomb;
    private GameLauncher gameLauncher;
    private final double vAcceleration = 0.02;
    private Pane pane;
    public BombAnimation(Game game, Plane plane, Bomb bomb, Pane pane, GameLauncher gameLauncher){
        super(bomb);
        this.game = game;
        this.bomb = bomb;
        this.hSpeed = plane.getObjectAnimation().gethSpeed();
        this.vSpeed = plane.getObjectAnimation().getvSpeed();
        this.pane = pane;
        this.gameLauncher = gameLauncher;
    }

    @Override
    protected void interpolate(double v) {
        vSpeed += vAcceleration;

        double x = bomb.getX() + hSpeed;
        double y = bomb.getY() + vSpeed;

        if (x > game.getGameLauncher().getWIDTH() + 10)
            x = -10;
        else if (x < -10)
            x = game.getGameLauncher().getWIDTH() + 10;
        if (y >= game.getGameLauncher().getHEIGHT()){
            y = game.getGameLauncher().getHEIGHT() - 40;
            this.stop();
            gameLauncher.getAnimations().remove(this);
            bomb.explode();
            gameLauncher.getAnimations().remove(this);
        }

        bomb.setX(x);
        bomb.setY(y);

        for (Node child: game.getTargets().getChildren()){
            if (bomb.getBoundsInParent().intersects(child.getBoundsInParent())){
                GameObject enemyObject = (GameObject) child;
                if (enemyObject.isHit()) continue;
                enemyObject.setHit(true);

                game.getCurrentWave().addSuccessfulShoots();
                game.getCurrentWave().addKills(enemyObject.getKills());
                gameLauncher.updateInfoScreen();
                User loggedInUser = User.getLoggedInUser();
                loggedInUser.addKills(enemyObject.getKills());
                loggedInUser.addDifficulty(enemyObject.getKills() *
                        loggedInUser.getGameSettings().getDifficulty());
                loggedInUser.addSuccessfulShoot();

                pane.getChildren().remove(bomb);
                if (enemyObject.getObjectAnimation() != null)
                    enemyObject.getObjectAnimation().stop();
                else if (enemyObject instanceof Building){
                    NuclearPrize nuclearPrize = new NuclearPrize(enemyObject.getX(), gameLauncher.getHEIGHT() - 200, 50, 50);
                    gameLauncher.getPane().getChildren().add(nuclearPrize);
                    NuclearPrizeAnimation nuclearPrizeAnimation;
                    nuclearPrizeAnimation = new NuclearPrizeAnimation(nuclearPrize, gameLauncher);
                    gameLauncher.getAnimations().add(nuclearPrizeAnimation);
                    nuclearPrizeAnimation.play();
                }
                gameLauncher.getAnimations().remove(this);
                enemyObject.explode();
                this.stop();
                break;
            }
        }
    }
}
