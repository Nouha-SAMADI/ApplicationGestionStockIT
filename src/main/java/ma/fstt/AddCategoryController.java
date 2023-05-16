package ma.fstt;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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


    private AddTypeController addTypeController; // Reference to the AddTypeController

    // ...

    // Method to set the AddTypeController reference
    public void setAddTypeController(AddTypeController controller) {
        this.addTypeController = controller;
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
            // Fetch the selected Type object from the typeComboBox
            Type selectedType = addTypeController.getNewType();

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
                // Check if the category already exists
                Category existingCategory = categoryDAO.getByName(categoryName);
                System.out.println("Existing Category: " + existingCategory);
                System.out.println("Selected Type: " + selectedType);
                if (existingCategory != null && existingCategory.getType().equals(selectedType)) {
                    // Display an error alert for category existence
                    showAlert(Alert.AlertType.ERROR, "Category Error", "Category already exists: " + categoryName);
                    continue; // Skip adding this category and proceed to the next one
                }


                Category newCategory = new Category(0L, categoryName, selectedType);
                categoryDAO.save(newCategory);
                categoryComboBox.getItems().add(newCategory);
            }

            category.setText("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
