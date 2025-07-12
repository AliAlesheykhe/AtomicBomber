package controller;

import javafx.stage.Stage;
import view.*;

public class ApplicationController {
    public static Stage primaryStage;
    public static LoginMenu loginMenu;
    public static SignUpMenu signUpMenu;
    public static MainMenu mainMenu;
    public static ProfileMenu profileMenu;
    public static AvatarMenu avatarMenu;
    public static DragAndDropAvatarMenu dragAndDropAvatarMenu;
    public static ScoreBoard scoreBoard;
    public static PauseMenu pauseMenu;
    public static SettingsMenu settingsMenu;
    public static GameLauncher gameLauncher;
    public static ChangeControlButtonsMenu changeControlButtonsMenu;
    public static ChangeTrackMenu changeTrackMenu;

    public static void run(String[] args){
        LoginMenu.run(args);
    }
}
