package ma.fstt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ParametrageDeReferenceDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class AdminController implements Initializable {


    @FXML
    private Button addType_btn;

    @FXML
    private TableColumn<ParametrageDeReference, String> category_col;

    @FXML
    private ComboBox<String> category_comboBox;

    @FXML
    private Button log;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField marque;

    @FXML
    private TableColumn<ParametrageDeReference, String> marque_col;

    @FXML
    private Button paramButton;

    @FXML
    private AnchorPane parametrageRef_form;
    @FXML
    private AnchorPane historique_form;

    @FXML
    private TableView<ParametrageDeReference> parametrageRef_table;

    @FXML
    private TextField quantity;

    @FXML
    private TableColumn<ParametrageDeReference, Integer> quantity_col;

    @FXML
    private TextField reference;


    @FXML
    private TextField num_serie;

    @FXML
    private TableColumn<ParametrageDeReference, String> reference_col;
    @FXML
    private TableColumn<ParametrageDeReference, Long> id_col;

    @FXML
    private TextField stock_max;

    @FXML
    private TextField stock_min;

    @FXML
    private TableColumn<ParametrageDeReference, String> type_col;

    @FXML
    private ComboBox<String> type_comboBox;

    @FXML
    private Button historyButton;

    @FXML
    private TableColumn<ParametrageDeReference, Integer> serialNumber_col;

    @FXML
    private Button add_button;


    @FXML
    private Button delete_button;

    @FXML
    private Button reset_button;


    @FXML
    private Button update_button;

    private double x =0 ;
    private double y=0;


    public ObservableList<ParametrageDeReference> addMaterialsListData(){


        ParametrageDeReferenceDAO paramDAO = null;

        ObservableList<ParametrageDeReference> materialList  = FXCollections.observableArrayList();

        try {
            paramDAO = new ParametrageDeReferenceDAO();
            for (ParametrageDeReference ettemp : paramDAO.getAll())
                materialList.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return materialList;
    }

    public void UpdateTable(){
        id_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,Long>("id"));
        type_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,String>("type"));

        category_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,String>("categorie"));
        serialNumber_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,Integer>("serialNumber"));
        marque_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,String>("brand"));
        reference_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,String>("reference"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,Integer>("quantity"));




        parametrageRef_table.setItems(this.addMaterialsListData());
    }



    @FXML
    private Button resetButton;

    @FXML
    void onResetButtonClick() {
        // Clear the text fields
        num_serie.setText("");
        type_comboBox.getSelectionModel().clearSelection();
        category_comboBox.getSelectionModel().clearSelection();
        quantity.setText("");
        stock_max.setText("");
        stock_min.setText("");
        reference.setText("");
        marque.setText("");

        // Clear the table selection
        parametrageRef_table.getSelectionModel().clearSelection();
    }



    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            ParametrageDeReferenceDAO paramDAO = new ParametrageDeReferenceDAO();

            int serialNumberValue = 0;
            if (num_serie.getText() != null && !num_serie.getText().isEmpty()) {
                serialNumberValue = Integer.parseInt(num_serie.getText());
            }

            ParametrageDeReference ref;
            if (serialNumberValue != 0) {
                ref = new ParametrageDeReference(0l,
                        (String) type_comboBox.getSelectionModel().getSelectedItem(),
                        (String) category_comboBox.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(quantity.getText()),
                        Integer.parseInt(stock_max.getText()),
                        Integer.parseInt(stock_min.getText()),
                        reference.getText(),
                        marque.getText(),
                        serialNumberValue);

            } else {
                ref = new ParametrageDeReference(0l,
                        (String) type_comboBox.getSelectionModel().getSelectedItem(),
                        (String) category_comboBox.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(quantity.getText()),
                        Integer.parseInt(stock_max.getText()),
                        Integer.parseInt(stock_min.getText()),
                        reference.getText(),
                        marque.getText());
            }
            paramDAO.save(ref);

            UpdateTable();

            num_serie.setText("");
            type_comboBox.getSelectionModel().clearSelection();
            category_comboBox.getSelectionModel().clearSelection();
            quantity.setText("");
            stock_max.setText("");
            stock_min.setText("");
            reference.setText("");
            marque.setText("");




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    protected void onUpdateButtonClick() {
        // get the selected item from the table
        ParametrageDeReference selectedRef = parametrageRef_table.getSelectionModel().getSelectedItem();


            try {
                // update the record in the database with the new values from the text fields
                selectedRef.setType(type_comboBox.getValue());
                selectedRef.setCategorie(category_comboBox.getValue());
                selectedRef.setQuantity(Integer.parseInt(quantity.getText()));
                selectedRef.setStockMax(Integer.parseInt(stock_max.getText()));
                selectedRef.setStockMin(Integer.parseInt(stock_min.getText()));
                selectedRef.setReference(reference.getText());
                selectedRef.setBrand(marque.getText());

                if (type_comboBox.getValue().equals("Matériel")) {
                    
                    selectedRef.setSerialNumber(0);
                } else {
                    // otherwise, update the serial number from the text field
                    if (num_serie.getText() != null && !num_serie.getText().isEmpty()) {
                        selectedRef.setSerialNumber(Integer.parseInt(num_serie.getText()));
                    } else {
                        selectedRef.setSerialNumber(0);
                    }
                }


                ParametrageDeReferenceDAO paramDAO = new ParametrageDeReferenceDAO();
                paramDAO.update(selectedRef);

                // update the table view
                UpdateTable();


                num_serie.setText("");
                type_comboBox.getSelectionModel().clearSelection();
                category_comboBox.getSelectionModel().clearSelection();
                quantity.setText("");
                stock_max.setText("");
                stock_min.setText("");
                reference.setText("");
                marque.setText("");



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }



    @FXML
    protected void onDeleteButtonClick() {

        ParametrageDeReference selectedItem = parametrageRef_table.getSelectionModel().getSelectedItem();



        try {
            ParametrageDeReferenceDAO paramDAO = new ParametrageDeReferenceDAO();

            paramDAO.delete(selectedItem);
            UpdateTable();

            num_serie.setText("");
            type_comboBox.getSelectionModel().clearSelection();
            category_comboBox.getSelectionModel().clearSelection();
            quantity.setText("");
            stock_max.setText("");
            stock_min.setText("");
            reference.setText("");
            marque.setText("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String[] listType = {"Consommable","Matériel"};
    public void addRefListType(){
        List<String> listT = new ArrayList<>();

        for(String data: listType){
            listT.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listT);
        type_comboBox.setItems(listData);

        Map<String, List<String>> categories = new HashMap<>();
        categories.put("Consommable", Arrays.asList("Ordinateur", "Imprimante", "Cable"));
        categories.put("Matériel", Arrays.asList("Clavier", "Souris", "Toner"));

        // Add an event listener to the type combo box to update the categories combo box
        type_comboBox.setOnAction(event -> {
            String selectedType = type_comboBox.getSelectionModel().getSelectedItem();
            List<String> selectedCategories = categories.get(selectedType);
            category_comboBox.getItems().clear();
            category_comboBox.getItems().addAll(selectedCategories);
        });

        type_comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals("Consommable")) {
                num_serie.setDisable(false);
            } else {
                num_serie.setDisable(true);
            }
        });
    }

    public void addRefListCategorie(){
        // This method is not needed anymore since the categories are being populated dynamically based on the selected type
    }

    public void switchForm(ActionEvent event){

        if(event.getSource() == paramButton){
            parametrageRef_form.setVisible(true);
            historique_form.setVisible(false);

            addRefListType();
            addRefListCategorie();
            UpdateTable();
        } else if (event.getSource() == historyButton) {
            parametrageRef_form.setVisible(false);
            historique_form.setVisible(true);

        }


    }

    public void logout(){
        try{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you ant to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){

                log.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();

                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX());
                    stage.setY(event.getScreenY());

                    stage.setOpacity(0.8);

                });

                root.setOnMouseReleased((MouseEvent event)->{
                    stage.setOpacity(1);
                });
                stage.setScene(scene);
                stage.show();


            }else return;


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //To show data on the tableview
        addRefListCategorie();
        addRefListType();
        UpdateTable();


        update_button.disableProperty().bind(parametrageRef_table.getSelectionModel().selectedItemProperty().isNull());
        delete_button.disableProperty().bind(parametrageRef_table.getSelectionModel().selectedItemProperty().isNull());

        //display the info of a selected item in the textFields
        parametrageRef_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Set the values of the selected item to the text fields
                num_serie.setText(String.valueOf(newSelection.getSerialNumber()));
                type_comboBox.getSelectionModel().select(newSelection.getType());
                category_comboBox.getSelectionModel().select(newSelection.getCategorie());
                quantity.setText(String.valueOf(newSelection.getQuantity()));
                stock_max.setText(String.valueOf(newSelection.getStockMax()));
                stock_min.setText(String.valueOf(newSelection.getStockMin()));
                reference.setText(newSelection.getReference());
                marque.setText(newSelection.getBrand());
            }
        });

    }
}