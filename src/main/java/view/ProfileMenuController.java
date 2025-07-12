package view;

import controller.ApplicationController;
import controller.InfoValidation;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.User;

public class ProfileMenuController {
    public TextField newUsername;
    public PasswordField newPassword;
    public ImageView avatar;

    public void initialize(){
        avatar.setImage(User.getLoggedInUser().getAvatar());
    }
    public void changeUsername(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        if (newUsername.getText().isEmpty()){
            alert.setHeaderText("username not entered");
            alert.setContentText("please enter a valid username");
            alert.show();
        }
        else if (InfoValidation.userNameExists(newUsername)){
            alert.setHeaderText("username already exists");
            alert.setContentText("please enter a different username");
            alert.show();
        }
        else{
            User.getLoggedInUser().setUsername(newUsername.getText());
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("congrats!");
            alert.setContentText("username changed successfully!");
        }
    }

    public void deleteAccount(MouseEvent mouseEvent) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You sure?");
        alert.setContentText("Are you sure you want to delete this account?");
        alert.showAndWait();
        if (alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE){
            User.getLoggedInUser().remove();
            goToLoginMenu();
        }
    }

    public void goToLoginMenu() {
        try {
            ApplicationController.loginMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToAvatarMenu(MouseEvent mouseEvent) {
        try {
            if (ApplicationController.avatarMenu == null)
                ApplicationController.avatarMenu = new AvatarMenu();
            ApplicationController.avatarMenu.start(ApplicationController.primaryStage);
        }catch (Exception ignored){

        }
    }

    public void changePassword(MouseEvent mouseEvent) {
        if (!InfoValidation.isPasswordStrong(newPassword)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("your password must be at least 8 characters long, have at least" +
                    "one special character (@#$%^&+=), at least one digit, and at least one uppercase and" +
                    "a lowercase letter");
            alert.show();
        }
        else User.getLoggedInUser().setPassword(newPassword.getText());
    }

    public void goToMainMenu(MouseEvent mouseEvent) {

        try {
            ApplicationController.mainMenu.start(ApplicationController.primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
