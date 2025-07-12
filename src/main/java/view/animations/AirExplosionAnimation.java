package view.animations;


import controller.ApplicationController;
import controller.GameController;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import model.Game;
import model.GameObject;
import model.Plane;
import view.EndGameMenu;
import view.GameLauncher;

import java.util.Objects;

public class AirExplosionAnimation extends ExplosionAnimation {

    private final Game game;
    private final GameObject gameObject;

    public AirExplosionAnimation(GameObject gameObject, Pane pane, Group targets, Game game) {
       this.gameObject = gameObject;
       this.pane = pane;
       this.targets = targets;
       this.game = game;
    }


    @Override
    protected void interpolate(double v) {
        int blastNum = 1;
        if (0.25 < v && v <= 0.5) blastNum = 2;
        else if (0.5 < v && v <= 0.75) blastNum = 3;
        else if (0.75 < v && v <= 1) blastNum = 4;

        gameObject.setFill(new ImagePattern(new Image(Objects.
                requireNonNull(SmallExplosionGroundAnimation.class
                        .getResource("/images/normal/blasts/air blast/airblast" + blastNum
                                + ".png")).toExternalForm())));
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (targets.getChildren().contains(gameObject))
                    targets.getChildren().remove(gameObject);
                else pane.getChildren().remove(gameObject);
                if (gameObject instanceof Plane){
                    game.setEnded(true);
                    game.setResult("lost");
                    game.getGameLauncher().getPane().getChildren().remove(gameObject);
                    GameController.endOfGame(game.getGameLauncher());
                }
                else game.getGameLauncher().getAnimations().remove(gameObject.getObjectAnimation());
            }
        });

    }
}
