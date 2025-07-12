package view;

import controller.ApplicationController;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.User;

import java.io.File;
import java.util.Objects;

public class AvatarMenuController {
    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView image4;

    public void initialize(){
        setImages();
    }

    private void setImages() {
        String imagePath = Objects.requireNonNull(LoginMenuController.class.getResource("/images/normal/avatars/avatar1.jpg")).toString();
        image1.setImage(new Image(imagePath));
        imagePath = Objects.requireNonNull(LoginMenuController.class.getResource("/images/normal/avatars/avatar2.jpg")).toString();
        image2.setImage(new Image(imagePath));
        imagePath = Objects.requireNonNull(LoginMenuController.class.getResource("/images/normal/avatars/avatar3.jpg")).toString();
        image3.setImage(new Image(imagePath));
        imagePath = Objects.requireNonNull(LoginMenuController.class.getResource("/images/normal/avatars/avatar4.jpg")).toString();
        image4.setImage(new Image(imagePath));
    }

    public void chooseImage(MouseEvent mouseEvent) {
        ImageView chosenImageView = (ImageView) mouseEvent.getSource();
        User.getLoggedInUser().setAvatar(chosenImageView.getImage());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congrats!");
        alert.setContentText("Your avatar changed successfully!");
        alert.show();
    }

    public void goToDragAndDropAvatarMenu(MouseEvent mouseEvent) {
        if (ApplicationController.dragAndDropAvatarMenu == null)
            ApplicationController.dragAndDropAvatarMenu = new DragAndDropAvatarMenu();
        ApplicationController.dragAndDropAvatarMenu.start(ApplicationController.primaryStage);
    }

    public void chooseImageFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            User.getLoggedInUser().setAvatar(image);
        }
    }

    public void goToProfileMenu(MouseEvent mouseEvent) {
        try {
            ApplicationController.profileMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
