package view.animations;

import controller.ApplicationController;
import controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import model.Bomb;
import model.Game;
import model.GameObject;
import view.EndGameMenu;

import java.util.Objects;

public class SmallExplosionGroundAnimation extends ExplosionAnimation {
    private Game game;
    private GameObject bomb;
    public SmallExplosionGroundAnimation(GameObject enemyObject, Pane pane, Group targets, model.Game game) {
       this.enemyObject = enemyObject;
       this.pane = pane;
       this.targets = targets;
       this.game = game;
    }

    public SmallExplosionGroundAnimation(GameObject bomb, Pane pane){
        this.pane = pane;
        this.bomb = bomb;
    }

    @Override
    protected void interpolate(double v) {
        if (targets == null){
            int blastNum = 1;
            if (0.33 < v && v <= 0.66) blastNum = 2;
            else if (0.66 < v && v <= 1) blastNum = 3;
            bomb.setFill(new ImagePattern(new Image(Objects.
                    requireNonNull(SmallExplosionGroundAnimation.class
                            .getResource("/images/normal/blasts/small blast/blast" + blastNum
                                    + ".png")).toExternalForm())));
            this.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    pane.getChildren().remove(bomb);
                }
            });
        }
        else{
            int blastNum = 1;
            if (0.33 < v && v <= 0.66) blastNum = 2;
            else if (0.66 < v && v <= 1) blastNum = 3;

            enemyObject.setFill(new ImagePattern(new Image(Objects.
                    requireNonNull(SmallExplosionGroundAnimation.class
                            .getResource("/images/normal/blasts/small blast/blast" + blastNum
                                    + ".png")).toExternalForm())));
            this.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    targets.getChildren().remove(enemyObject);
                    if (GameController.isWaveFinished(game.getCurrentWave()))
                        GameController.goToNextWave(game);
                    game.getGameLauncher().getAnimations().remove(enemyObject.getObjectAnimation());
                    if (enemyObject.getObjectAnimation() != null)
                        enemyObject.getObjectAnimation().stop();
                    enemyObject = null;
                }
            });
        }

    }
}
