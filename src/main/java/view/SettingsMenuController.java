package view;

import controller.ApplicationController;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.User;

import java.net.URL;
import java.util.Objects;

public class SettingsMenuController {
    public RadioButton easy;
    public RadioButton medium;
    public RadioButton hard;
    public RadioButton mute;
    public RadioButton blackAndWhite;
    public BorderPane mainPane;
    private ToggleGroup toggleGroup = new ToggleGroup();

    public void initialize(){
        setToggleGroups();
        setRadioButtonValues();
        setDifficultyValuesForGame();
        setToggleListenerForMuteAndBlackAndWhite();
    }

    private void setToggleListenerForMuteAndBlackAndWhite() {
        mute.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            User.getLoggedInUser().getGameSettings().setMuted(isNowSelected);
        });
        blackAndWhite.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            User.getLoggedInUser().getGameSettings().setBlackAndWhite(isNowSelected);
            changeMenuColor(isNowSelected);
        });
    }

    private void changeMenuColor(Boolean monochrome) {
        Image image = getMonochromaticImage(Objects.requireNonNull(SettingsMenuController.class
                .getResource("/images/normal/backgrounds/LoginAndSignUpBackground.jpg")));
        BackgroundImage backgroundImage =
                new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        mainPane.setBackground(new Background(backgroundImage));
    }

    private javafx.scene.image.Image getMonochromaticImage(URL url) {
        Image originalImage = new Image(url.toString());

        int width = (int) originalImage.getWidth();
        int height = (int) originalImage.getHeight();
        javafx.scene.image.WritableImage monochromeImage =
                new javafx.scene.image.WritableImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                javafx.scene.paint.Color originalColor = originalImage.getPixelReader().getColor(x, y);
                double grayscaleValue = (originalColor.getRed() + originalColor.getGreen() + originalColor.getBlue()) / 3.0;
                javafx.scene.paint.Color grayscaleColor = javafx.scene.paint.Color.gray(grayscaleValue);
                monochromeImage.getPixelWriter().setColor(x, y, grayscaleColor);
            }
        }
        return monochromeImage;
    }

    private void setDifficultyValuesForGame() {
        toggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            RadioButton selectedRadioButton = (RadioButton) newToggle;
            switch (selectedRadioButton.getId()){
                case "easy":
                    User.getLoggedInUser().getGameSettings().setDifficulty(1);
                    break;
                case "medium":
                    User.getLoggedInUser().getGameSettings().setDifficulty(2);
                    break;
                case "hard":
                    User.getLoggedInUser().getGameSettings().setDifficulty(3);
                    break;
            }
        });
    }

    private void setRadioButtonValues() {
        switch (User.getLoggedInUser().getGameSettings().getDifficulty()){
            case 1:
                easy.setSelected(true);
                break;
            case 2:
                medium.setSelected(true);
                break;
            case 3:
                hard.setSelected(true);
        }
        mute.setSelected(User.getLoggedInUser().getGameSettings().isMuted());
        blackAndWhite.setSelected(User.getLoggedInUser().getGameSettings().isBlackAndWhite());
    }

    private void setToggleGroups() {
        easy.setToggleGroup(toggleGroup);
        medium.setToggleGroup(toggleGroup);
        hard.setToggleGroup(toggleGroup);
    }

    public void goToMainMenu(MouseEvent mouseEvent) {
        try{
            ApplicationController.mainMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToChangeControlsMenu(MouseEvent mouseEvent) {
        try{
            if (ApplicationController.changeControlButtonsMenu == null)
                ApplicationController.changeControlButtonsMenu = new ChangeControlButtonsMenu();
            ApplicationController.changeControlButtonsMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
