package ma.fstt;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.TypeDAO;

import java.sql.SQLException;

public class AddTypeController {

    @FXML
    private Button add_button;

    @FXML
    private TextField type;

    private Type newType;

    @FXML
    private ComboBox<Type> typeComboBox; // Reference to the ComboBox

    // Method to set the ComboBox referenceDatabase Engine: Verify that you are using the appropriate database engine in MariaDB 
    public void setComboBox(ComboBox<Type> comboBox) {
        this.typeComboBox = comboBox;
    }
    // Method to set the ComboBox reference



//checké l'existance du  type dans la base de données???????

    @FXML
    protected void onAddButtonClick() {
        try {
            TypeDAO typeDao = new TypeDAO();
            String typeName = type.getText().trim();

            // Check if the type already exists in the database
            Type existingType = typeDao.getByName(typeName);
            if (existingType != null) {
                // Handle the case where the type already exists
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Type Already Exists");
                alert.setHeaderText(null);
                alert.setContentText("A type with the name '" + typeName + "' already exists.");
                alert.showAndWait();
                return;
            }

            newType = new Type(0L, typeName);
            typeDao.save(newType);

            // Add the new type to the ComboBox
            typeComboBox.getItems().add(newType);
            type.setText("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Type getNewType() {
        return newType;
    }

}
