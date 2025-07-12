package view;

import controller.ApplicationController;
import javafx.animation.Transition;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.User;

import javax.jws.soap.SOAPBinding;

public class PauseMenuController {

    public static void setGameLauncher(GameLauncher gameLauncher) {
        ApplicationController.gameLauncher = gameLauncher;
    }

    public void exit(MouseEvent mouseEvent) {
        ApplicationController.gameLauncher.getMediaPlayer().stop();
        User.getLoggedInUser().setSavedGame(null);
        try {
            if (User.getLoggedInUser().getUsername().isEmpty())
                ApplicationController.loginMenu.start(ApplicationController.primaryStage);
            else{
                if (ApplicationController.mainMenu == null)
                    ApplicationController.mainMenu = new MainMenu();
                ApplicationController.mainMenu.start(ApplicationController.primaryStage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveAndExit() throws Exception {
        if (User.getLoggedInUser().getUsername().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have entered as a guest!!");
            alert.show();
        }
        else{
            if (ApplicationController.gameLauncher.getMediaPlayer() != null)
                ApplicationController.gameLauncher.getMediaPlayer().pause();
            User.getLoggedInUser().setSavedGame(ApplicationController.gameLauncher);
            ApplicationController.mainMenu.start(ApplicationController.primaryStage);
        }
    }
    public void stopMusic(MouseEvent mouseEvent) {
        ApplicationController.gameLauncher.getMediaPlayer().stop();
    }

    public void goToManual(MouseEvent mouseEvent) {
        try{
            new KeyManualMenu().start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void goToChangeTrackMenu(){
        try{
            if (ApplicationController.changeTrackMenu == null)
                ApplicationController.changeTrackMenu = new ChangeTrackMenu();
            ApplicationController.changeTrackMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resumeGame(MouseEvent mouseEvent) {
        try{
            ApplicationController.gameLauncher.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
