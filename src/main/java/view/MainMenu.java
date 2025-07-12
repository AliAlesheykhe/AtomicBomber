package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationController.primaryStage = primaryStage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("/fxml files/MainMenu.fxml")));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
