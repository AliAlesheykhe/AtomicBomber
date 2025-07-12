package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChangeControlButtonsMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(ChangeControlButtonsMenu.class.
                getResource("/fxml files/ChangeControlButtonsMenu.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        ChangeControlButtonsMenuController controller = loader.getController();

        scene.setOnKeyPressed(event -> {
            controller.setKeySettings(event.getCode());
        });

        primaryStage.show();

    }
}
