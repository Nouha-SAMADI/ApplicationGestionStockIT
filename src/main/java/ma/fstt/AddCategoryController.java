package ma.fstt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.CategoryDAO;
import model.TypeDAO;

import java.sql.SQLException;

public class AddCategoryController {

    @FXML
    private Button add_button;

    @FXML
    private TextField category;

    @FXML
    private TextField type;

    @FXML
    private ComboBox<Category> categoryComboBox; // Reference to the ComboBox

    // Method to set the ComboBox reference
    public void setComboBox(ComboBox<Category> comboBox) {
        this.categoryComboBox = comboBox;
    }


    @FXML
    protected void onAddButtonClick(){
        try{
            // Fetch the Type object by name
            TypeDAO typeDAO = new TypeDAO();
            Type categoryType = typeDAO.getByName(type.getText());

            // Create the Category object with the retrieved Type object
            Category newCategory = new Category(0l, category.getText(), categoryType);

            // Save the category to the database
            CategoryDAO categoryDAO = new CategoryDAO();
            categoryDAO.save(newCategory);



            // Add the new type to the ComboBox
            categoryComboBox.getItems().add(newCategory);

            type.setText(" ");
            category.setText(" ");



        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
