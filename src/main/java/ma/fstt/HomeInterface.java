package ma.fstt;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ma.fstt.ParametrageDeReference;
import model.ParametrageDeReferenceDAO;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.List;

public class HomeInterface extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a StackPane as the root node
        StackPane root = new StackPane();

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
        Notifications.create()
                .title("Notification")
                .text(message)
                .position(Pos.BOTTOM_RIGHT)
                .hideAfter(Duration.seconds(60))
                .showInformation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
