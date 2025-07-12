package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.net.URL;

public class SignUpMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationController.primaryStage = primaryStage;
        URL url = SignUpMenu.class.getResource("/fxml files/SignUpMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}