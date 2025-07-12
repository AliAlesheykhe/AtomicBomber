package view;

import controller.ApplicationController;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import model.User;

public class EndGameMenuController {
    public Label result;
    public Label accuracy;
    public Label endWave;
    public Label kills;
    public Label endWaveAccuracy;

    public void initialize(){
        result.setText("you " + ApplicationController.gameLauncher.getGame().getResult());
        accuracy.setText("total accuracy: " + ApplicationController.gameLauncher.getGame().getTotalAccuracy());
        kills.setText("kills: " + ApplicationController.gameLauncher.getGame().getKills());
        endWave.setText("last wave: " + ApplicationController.gameLauncher.getGame().getWaves().size());
        endWaveAccuracy.setText("wave " + ApplicationController.gameLauncher.getGame().getWaves().size()
        + " accuracy: " + ApplicationController.gameLauncher.getGame().getCurrentWave().getAccuracy());
    }

    public void continueGame(KeyCode code){
        if (code == KeyCode.H){
            ApplicationController.gameLauncher.getGame().setEnded(false);
            ApplicationController.gameLauncher.getGame().setResult(null);
            try {
                ApplicationController.gameLauncher.start(ApplicationController.primaryStage);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void exitMenu() throws Exception {
        MediaPlayer mediaPlayer = ApplicationController.gameLauncher.getMediaPlayer();
        if (mediaPlayer != null)
            mediaPlayer.stop();
        if (User.getLoggedInUser().getSavedGame() == ApplicationController.gameLauncher)
            User.getLoggedInUser().setSavedGame(null);
        if (User.getLoggedInUser().getUsername().isEmpty())
            ApplicationController.loginMenu.start(ApplicationController.primaryStage);
        else ApplicationController.mainMenu.start(ApplicationController.primaryStage);
    }
}
