package ma.fstt;

import javafx.fxml.FXML;
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


    @FXML
    private ComboBox<Type> typeComboBox; // Reference to the ComboBox

    // Method to set the ComboBox reference
    public void setComboBox(ComboBox<Type> comboBox) {
        this.typeComboBox = comboBox;
    }


   @FXML
    protected void onAddButtonClick(){
       try{
           TypeDAO typeDao = new TypeDAO();
           Type newType;
           newType = new Type(0l,type.getText());

           typeDao.save(newType);


           // Add the new type to the ComboBox
           typeComboBox.getItems().add(newType);

           type.setText(" ");



       }catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

}
