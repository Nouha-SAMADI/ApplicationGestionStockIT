package ma.fstt;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ParametrageDeReferenceDAO;

import java.sql.SQLException;
import java.util.List;

public class HomeInterface extends Application {

    private Label notificationLabel;

    @Override
    public void start(Stage primaryStage) {
        // Create a StackPane as the root node
        StackPane root = new StackPane();

        // Create a label to display the notification
        notificationLabel = new Label();
        notificationLabel.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF; -fx-padding: 10px;");
        notificationLabel.setVisible(false);

        // Add the notification label to the root node
        root.getChildren().add(notificationLabel);

        // Set the alignment of the notification label to bottom-right
        StackPane.setAlignment(notificationLabel, Pos.BOTTOM_RIGHT);

        // Create a scene with the root node
        Scene scene = new Scene(root, 400, 300);

        // Set the scene on the primary stage
        primaryStage.setScene(scene);

        // Show the primary stage
        primaryStage.show();

        // Call the checkStockAndShowNotification method periodically
        checkStockAndShowNotification();
    }

    private void checkStockAndShowNotification() {
        // Create an instance of ParametrageDeReferenceDAO
        ParametrageDeReferenceDAO dao;
        try {
            dao = new ParametrageDeReferenceDAO();

            // Get the list of ParametrageDeReference objects
            List<ParametrageDeReference> paramList = dao.getAll();

            // Iterate over the list and check for low stock
            for (ParametrageDeReference param : paramList) {
                if (param.getQuantity() <= param.getStockMin()) {
                    String notificationMessage = "Product " + param.getReference() + " (" + param.getBrand() + ") is almost out of stock.";
                    showNotification(notificationMessage);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showNotification(String message) {
        notificationLabel.setText(message);
        notificationLabel.setVisible(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
