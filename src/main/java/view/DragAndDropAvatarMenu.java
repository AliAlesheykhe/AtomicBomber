package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;

import java.util.Objects;

public class DragAndDropAvatarMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        ApplicationController.primaryStage = primaryStage;
        ImageView imageView = new ImageView(User.getLoggedInUser().getAvatar());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);
        imageView.setFitHeight(500);

        Pane dragAndDropArea = new Pane(imageView);

        dragAndDropArea.setOnDragOver(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        dragAndDropArea.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                String imagePath = db.getFiles().get(0).toURI().toString();
                imageView.setImage(new javafx.scene.image.Image(imagePath));
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        Button chooseButton = new Button("Choose");
        chooseButton.setOnAction(event -> choose(imageView));

        VBox root = new VBox(10, dragAndDropArea, chooseButton);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 400);

        primaryStage.setTitle("Image Drag and Drop Example");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void choose(ImageView imageView) {
        ApplicationController.primaryStage.setResizable(true);
        User.getLoggedInUser().setAvatar(imageView.getImage());
        try {
          ApplicationController.avatarMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
