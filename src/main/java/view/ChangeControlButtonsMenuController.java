package view;

import controller.ApplicationController;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import model.User;

public class ChangeControlButtonsMenuController {
    public RadioButton radio1;
    public RadioButton radio2;
    public RadioButton radio3;
    public RadioButton radio4;
    public RadioButton radio6;
    public RadioButton radio5;
    private ToggleGroup toggleGroup = new ToggleGroup();

    public void initialize(){
        putButtonsInToggleGroup();
    }

    public void setKeySettings(KeyCode code) {
        RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();
        User.getLoggedInUser().getGameSettings().putInKeys(selected.getText(), code);
    }

    private void putButtonsInToggleGroup() {
        radio1.setToggleGroup(toggleGroup);
        radio2.setToggleGroup(toggleGroup);
        radio3.setToggleGroup(toggleGroup);
        radio4.setToggleGroup(toggleGroup);
        radio5.setToggleGroup(toggleGroup);
        radio6.setToggleGroup(toggleGroup);
    }

    public void goToSettingsMenu(MouseEvent mouseEvent) {
        try {
            ApplicationController.settingsMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
