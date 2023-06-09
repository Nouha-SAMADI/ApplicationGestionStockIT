package ma.fstt;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.CategoryDAO;
import model.TypeDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddCategoryController {

    @FXML
    private Button add_button;

    @FXML
    private TextField category;

    private TypeDAO typeDAO;
    public AddCategoryController() {
        try {
            typeDAO = new TypeDAO();
        } catch (SQLException e) {
            // Handle the exception
        }
    }



    @FXML
    private ComboBox<Type> typeComboBox; // Reference to the ComboBox

    // Method to set the ComboBox reference
    public void setTypeComboBox(ComboBox<Type> comboBox) {
        this.typeComboBox = comboBox;
    }

    @FXML
    private ComboBox<Category> categoryComboBox; // Reference to the ComboBox

    // Method to set the ComboBox reference
    public void setComboBox(ComboBox<Category> comboBox) {
        this.categoryComboBox = comboBox;
    }



    @FXML
    protected void onAddButtonClick() {
        try {
            // Fetch the selected type name from the typeComboBox
            String selectedTypeName = typeComboBox.getSelectionModel().getSelectedItem().getName();

            // Retrieve the selected type from the database
            TypeDAO typeDAO = new TypeDAO();
            Type selectedType = typeDAO.getByName(selectedTypeName);

            // Check if a Type is selected
            if (selectedType == null) {
                // Display an error alert for no selected type
                showAlert(Alert.AlertType.ERROR, "Type Error", "No type selected!");
                return;
            }

            // Retrieve the category names (assuming they are entered in a comma-separated format)
            String[] categoryNames = category.getText().split(",");

            // Create the Category objects with the retrieved Type object
            CategoryDAO categoryDAO = new CategoryDAO();
            for (String categoryName : categoryNames) {
                categoryName = categoryName.trim();

                // Check if the category already exists for the selected type
                Category existingCategory = categoryDAO.getByNameAndType(categoryName, selectedType);
                if (existingCategory != null) {
                    // Display an error alert for category existence
                    showAlert(Alert.AlertType.ERROR, "Category Error", "Category already exists: " + categoryName);
                    continue; // Skip adding this category and proceed to the next one
                }

                Category newCategory = new Category(0L, categoryName, selectedType);
                categoryDAO.save(newCategory);
                categoryComboBox.getItems().add(newCategory);
            }

            category.setText("");
            closeScene();

            // Print the selected type
            System.out.println("Selected Type: " + selectedType);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    protected void closeScene() {
        Stage stage = (Stage) add_button.getScene().getWindow();
        stage.close();
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            // Handle the Enter key press event here
            onAddButtonClick();
        }


    }
}
