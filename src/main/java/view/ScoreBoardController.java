package view;
import controller.ApplicationController;
import javafx.scene.input.MouseEvent;
import model.User;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;


import java.util.ArrayList;

public class ScoreBoardController {
    public ToggleGroup toggleGroup = new ToggleGroup();
    public RadioButton radioButton1;
    public RadioButton radioButton2;
    public RadioButton radioButton3;
    public RadioButton radioButton4;
    public VBox mainBox;

    public void initialize(){
        setToggleGroups();
        show(User.getSortedByScore(), "score");
        setMetric();
    }

    private void setMetric() {
        toggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            RadioButton selectedRadioButton = (RadioButton) newToggle;
            switch (selectedRadioButton.getText()){
                case "sort by score":
                    changeMetricTextTo("score: ");
                    show(User.getSortedByScore(), "score");
                    break;
                case "sort by kills":
                    changeMetricTextTo("kills: ");
                    show(User.getSortedByKills(), "kills");
                    break;
                case "sort by difficulty":
                    changeMetricTextTo("difficulty: ");
                    show(User.getSortedByDifficulty(), "difficulty");
                    break;
                case "sort by accuracy":
                    changeMetricTextTo("accuracy: ");
                    show(User.getSortedByAccuracy(), "accuracy");
            }
        });
    }

    private void show(ArrayList<User> topList, String metric){
        int i = 0;
        for (javafx.scene.Node hBox : mainBox.getChildren()) {
            if (hBox.getId() != null && i < topList.size() && i < 10) {
                String userNameId = "#username" + (i + 1);
                String scoreId = "#score" + (i + 1);
                Label username = (Label) hBox.lookup(userNameId);
                Label score = (Label) hBox.lookup(scoreId);
                username.setText(topList.get(i).getUsername());
                score.setText(topList.get(i).getRelevantMetric(metric));
                i++;
            }
        }
    }

    private void changeMetricTextTo(String metricString) {
        int i = 1;
        for (javafx.scene.Node child : mainBox.getChildren()) {
            if (child.getId() != null && i <= 10) {
                String id = "#metric" + i;
                Label metric = (Label) child.lookup(id);
                metric.setText(metricString);
                i++;
            }
        }
    }
    private void setToggleGroups() {
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        radioButton4.setToggleGroup(toggleGroup);
    }

    public void goToMainMenu(MouseEvent mouseEvent) {
        try {
            ApplicationController.mainMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
