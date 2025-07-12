package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class SettingsMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = SettingsMenu.class.getResource("/fxml files/SettingsMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
