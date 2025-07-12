package controller;

import model.User;

import java.awt.*;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;

public class InfoValidation {
    public static boolean userNameExists(TextField username){
        return (User.getUserByUsername(username.getText()) != null);
    }

    public static boolean isPasswordCorrect(TextField username ,TextField password){
        return (User.getUserByUsername(username.getText()).getPassword().equals(password.getText()));
    }

    public static boolean isPasswordStrong(TextField password){
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
        return Pattern.compile(passwordRegex).matcher(password.getText()).find();
    }
}
