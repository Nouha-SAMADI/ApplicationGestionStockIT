package ma.fstt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.LoginDAO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class ProfilePictureTest extends Application {

    private LoginDAO loginDAO;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize the LoginDAO
        try {
            loginDAO = new LoginDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Create ImageView to display the selected profile picture
        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        // Create Export button to choose a profile picture
        Button exportButton = new Button("Export");
        exportButton.setOnAction(event -> {
            // Create a FileChooser dialog to let the user choose a profile picture
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Profile Picture");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );

            // Show the FileChooser dialog
            File profilePictureFile = fileChooser.showOpenDialog(primaryStage);
            if (profilePictureFile != null) {
                try {
                    // Get the absolute file path
                    String profilePicturePath = profilePictureFile.getAbsolutePath();

                    // Create a new Login object with the profile picture path
                    Login login = new Login(0L, "username", "password", "userType", "emailAddress");
                    login.setProfilePicturePath(profilePicturePath);

                    // Save the Login object in the database
                    loginDAO.save(login);

                    System.out.println("Profile picture path saved successfully.");

                    // Set the selected profile picture in the ImageView
                    Image profilePicture = new Image(profilePictureFile.toURI().toString());
                    imageView.setImage(profilePicture);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create a VBox to hold the ImageView and Export button
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(imageView, exportButton);

        // Create the scene and set it to the primary stage
        Scene scene = new Scene(vbox, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Profile Picture Test");
        primaryStage.show();
    }
}
