package view;

import controller.ApplicationController;
import controller.GameSettings;
import controller.InfoValidation;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;

import java.io.IOException;

public class SignUpMenuController {
    public TextField username;
    public TextField password;

    public void signUp(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        if (username.getText().isEmpty()){
            alert.setHeaderText("username not valid");
            alert.setContentText("please enter a valid username");
            alert.show();
        }
        else if (InfoValidation.userNameExists(username)){
            alert.setHeaderText("username already exists!");
            alert.setContentText("please enter a different username");
            alert.show();
        }
        else if (!InfoValidation.isPasswordStrong(password)){
            alert.setContentText("your password must be at least 8 characters long, have at least" +
                    "one special character (@#$%^&+=), at least one digit, and at least one uppercase and" +
                    "a lowercase letter");
            alert.show();
        }
        else{
            new User(username.getText(), password.getText(), new GameSettings());
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("account created successfully!");
            alert.setContentText("go to login menu to log in");
            alert.show();
        }
    }

    public void goToLoginMenu() {
        LoginMenu loginMenu = new LoginMenu();
        try {
            if (ApplicationController.loginMenu == null)
                ApplicationController.loginMenu = new LoginMenu();
            loginMenu.start(ApplicationController.primaryStage);
        }catch (Exception ignored){

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
