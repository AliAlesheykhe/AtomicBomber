package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class EndGameMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(ChangeControlButtonsMenu.class.
                getResource("/fxml files/EndGameMenu.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        EndGameMenuController controller = loader.getController();
        scene.setOnKeyPressed(event -> {
            controller.continueGame(event.getCode());
        });
        primaryStage.show();
    }
}
