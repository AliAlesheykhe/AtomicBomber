package view;

import controller.ApplicationController;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ChangeTrackMenuController {
    public ToggleGroup toggleGroup = new ToggleGroup();
    public HBox options;

    public void initialize(){
        RadioButton radioButton = (RadioButton) options.lookup("#" +
                ApplicationController.gameLauncher.getTrackName());
        radioButton.setSelected(true);

       for (Node child: options.getChildren()){
           radioButton = (RadioButton) child;
           radioButton.setToggleGroup(toggleGroup);
       }

        toggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            RadioButton selectedRadioButton = (RadioButton) newToggle;
            ApplicationController.gameLauncher.getMediaPlayer().stop();
            ApplicationController.gameLauncher.setTrackName(selectedRadioButton.getText());
            ApplicationController.gameLauncher.playTrack();
        });
    }

    public void goToPauseMenu(MouseEvent mouseEvent) {
        try {
            ApplicationController.pauseMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
