package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;


public class KeyManualMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = KeyManualMenu.class.getResource("/fxml files/KeyManualMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        ApplicationController.primaryStage = primaryStage;
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
