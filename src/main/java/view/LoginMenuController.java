package view;

import controller.ApplicationController;
import controller.GameSettings;
import controller.InfoValidation;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;

public class LoginMenuController{
    public TextField username;
    public TextField password;
    public void login(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        if (username.getText().isEmpty()){
            alert.setHeaderText("username not entered");
            alert.setContentText("please enter a valid username");
            alert.show();
        }
        else if (!InfoValidation.userNameExists(username)){
            alert.setHeaderText("username not found");
            alert.setContentText("do you want to sign up?");
            alert.show();
        }
        else if (!InfoValidation.isPasswordCorrect(username, password)){
            alert.setHeaderText("wrong password");
            alert.setContentText("please check your password");
            alert.show();
        }
        else{
            try {
                if (ApplicationController.mainMenu == null)
                    ApplicationController.mainMenu = new MainMenu();
                User.setLoggedInUser(User.getUserByUsername(username.getText()));
                ApplicationController.mainMenu.start(ApplicationController.primaryStage);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void goToSignUpMenu(MouseEvent mouseEvent){
        try{
            if (ApplicationController.signUpMenu == null)
                ApplicationController.signUpMenu = new SignUpMenu();
            ApplicationController.signUpMenu.start(ApplicationController.primaryStage);
        }
        catch (Exception ignored){
        }
    }

    public void playGame(MouseEvent mouseEvent) {
        User.setLoggedInUser(new User("", "", new GameSettings()));
        try{
            new GameLauncher().start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
