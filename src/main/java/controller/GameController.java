package controller;

import com.sun.org.apache.bcel.internal.generic.RET;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.*;
import view.EndGameMenu;
import view.GameLauncher;
import view.animations.BombAnimation;
import view.animations.NuclearBombAnimation;

public class GameController {
    public static boolean isWaveFinished(Wave currentWave){
        return (currentWave.getKills() == currentWave.getTotalPossibleKills());
    }
    public static void goToNextWave(Game game){
            int waveNum = game.getWaves().size() + 1;
            int numberOfShootingTanks = 0;
            if (waveNum <= 3){
                if (waveNum == 2)
                    numberOfShootingTanks = 10;
                else if (waveNum == 3)
                    numberOfShootingTanks = 20;
                game.addWave(4 * waveNum, numberOfShootingTanks, waveNum * 7);
                try {
                    ApplicationController.gameLauncher.start(ApplicationController.primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } else {
            game.setResult("won");
            game.setEnded(true);
            endOfGame(game.getGameLauncher());
        }
    }
    public static void endOfGame(GameLauncher gameLauncher){
        gameLauncher.stopAnimations();
        gameLauncher.stopTimelines();
        try {
            new EndGameMenu().start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
