package view;

import controller.ApplicationController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.User;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Objects;

public class MainMenuController {
    public Label username;
    public ImageView avatar;

    public void initialize(){
        username.setText("username: " + User.getLoggedInUser().getUsername());
        setAvatarImage();
    }

    public void setAvatarImage(){
        Image avatarImage = User.getLoggedInUser().getAvatar();
        if (avatarImage == null){
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int randInt = random.nextInt(1, 5);
            User.getLoggedInUser().setAvatar(new Image(Objects.requireNonNull
                    (MainMenuController.class.getResource
                    ("/images/normal/avatars/avatar" + randInt + ".jpg"))
                    .toExternalForm()));
        }
        avatar.setImage(User.getLoggedInUser().getAvatar());
    }

    public void goToProfileMenu(MouseEvent mouseEvent) {
        try{
            if (ApplicationController.profileMenu == null)
                ApplicationController.profileMenu = new ProfileMenu();
            ApplicationController.profileMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void exitGame(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void goToScoreBoard(MouseEvent mouseEvent) {
        try{
            if (ApplicationController.scoreBoard == null)
                ApplicationController.scoreBoard = new ScoreBoard();
            ApplicationController.scoreBoard.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();;
        }
    }

    public void goToSettingsMenu(){
        try {
            if (ApplicationController.settingsMenu == null)
                ApplicationController.settingsMenu = new SettingsMenu();
            ApplicationController.settingsMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startGame(MouseEvent mouseEvent) {
        try {
            new GameLauncher().start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void playExistingGame(){
       if (User.getLoggedInUser().getSavedGame() == null){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("no saved game");
           alert.setContentText("no saved game exists!");
       }
       else{
           try {
               User.getLoggedInUser().getSavedGame().start(ApplicationController.primaryStage);
           }catch (Exception e){
               e.printStackTrace();
           }
       }
    }
}
