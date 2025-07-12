package view.animations;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import model.GameObject;
import model.NuclearBomb;
import model.User;

import java.util.Objects;

public class NuclearExplosion extends ExplosionAnimation{
    private final NuclearBomb nuclearBomb;
    private final Game game;
    private boolean addedSuccessfulShoot = false;
    public NuclearExplosion(NuclearBomb nuclearBomb, Game game) {
        this.nuclearBomb = nuclearBomb;
        this.game = game;
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        nuclearBomb.setWidth(30 * nuclearBomb.getWIDTH());
        nuclearBomb.setHeight(10 * nuclearBomb.getHEIGHT());
        int stage = 1;
        if (0.25 < v && v <= 0.5){
            stage = 2;
        }
        else if (0.5 < v && v <= 0.75){
            stage = 3;
        }
        else if (0.75 < v && v <= 1){
            stage = 4;
        }
        nuclearBomb.setFill(new ImagePattern(new Image(Objects.requireNonNull
                (NuclearExplosion.class.getResource(
                "/images/normal/blasts/big blast/bigblast" + stage + ".png"
                )).toExternalForm())));

        for (Node child: game.getTargets().getChildren()){
            GameObject enemy = (GameObject) child;
            if (enemy.isHit()) continue;
            if (enemy.getBoundsInParent().intersects(nuclearBomb.getBoundsInParent())){
                enemy.setHit(true);
                User loggedInUser = User.getLoggedInUser();
                if (!addedSuccessfulShoot){
                    addedSuccessfulShoot = true;
                    game.getCurrentWave().addSuccessfulShoots();
                    loggedInUser.addSuccessfulShoot();
                }
                enemy.explode();
                game.getGameLauncher().updateInfoScreen();
                game.getCurrentWave().addKills(enemy.getKills());
                loggedInUser.addKills(enemy.getKills());
                loggedInUser.addDifficulty(enemy.getKills()
                        * loggedInUser.getGameSettings().getDifficulty());
            }
        }
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.getGameLauncher().getPane().getChildren().remove(nuclearBomb);
            }
        });
    }
}
