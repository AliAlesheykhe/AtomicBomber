package view;

import controller.ApplicationController;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.User;

import java.util.HashMap;

public class KeyManualMenuController {
    public VBox labels;

    public void initialize(){
        setTexts();
    }

    private void setTexts() {
        HashMap<String, KeyCode> keys = User.getLoggedInUser().getGameSettings().getKeys();
        for (Node child: labels.getChildren()){
            try {
                Label label = (Label) child;
                label.setText(label.getText() + ":   " + keys.get(label.getText()));
            }catch (Exception ignored){}
        }
    }

    public void goToPauseMenu(MouseEvent mouseEvent) {
        try{
            ApplicationController.pauseMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
