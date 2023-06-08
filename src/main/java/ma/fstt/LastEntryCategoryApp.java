package ma.fstt;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.fstt.Entry;
import model.EntryDAO;

import model.ParametrageDeReferenceDAO;

import java.sql.SQLException;

public class LastEntryCategoryApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Last Entry ");

        // Create a label to display the category
        Label referenceLabel = new Label();
        Label quantityLabel = new Label();
        Label categoryLabel = new Label();

        // Create a VBox layout to hold the label
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(referenceLabel, quantityLabel,categoryLabel);

        // Create the scene
        Scene scene = new Scene(vbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            // Retrieve the last entry
            EntryDAO entryDAO = new EntryDAO();
            Entry lastEntry = entryDAO.getLastEntry();

            if (lastEntry != null) {
                // Get the product reference of the last entry

                int quantity = lastEntry.getQuantity();
                String productReference = lastEntry.getProductReference();

                // Retrieve the corresponding parameterization reference
                ParametrageDeReferenceDAO paramDAO = new ParametrageDeReferenceDAO();
                ParametrageDeReference param = paramDAO.getByReference(productReference);

                if (param != null) {
                    // Get the category of the parameterization reference
                    String category = param.getCategory().getName();

                    // Update the category label with the retrieved category
                    referenceLabel.setText("Reference: " + productReference);
                    quantityLabel.setText("Quantity: " + quantity);
                    categoryLabel.setText("Category: " + category);
                } else {
                    categoryLabel.setText("Category not found");
                }
            } else {
                categoryLabel.setText("No entries found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
