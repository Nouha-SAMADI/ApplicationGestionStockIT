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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class AdminController implements Initializable {


    @FXML
    private Button addType_btn;

    @FXML
    private Button userButton;

    @FXML
    private TableColumn<ParametrageDeReference, String> category_col;

    @FXML
    private ComboBox<Category> category_comboBox;

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
    private ComboBox<Type> type_comboBox;

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
    @FXML
    private Button ajouter;

    @FXML
    private Button annuler;
    @FXML
    private TableColumn<Login, String> col_email;

    @FXML
    private TableColumn<Login, Long> col_id;

    @FXML
    private TableColumn<Login, String> col_password;

    @FXML
    private TableColumn<Login, String> col_statut;

    @FXML
    private TableColumn<Login, String> col_user;
    @FXML
    private TextField statut;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private TableView<Login> gestionutili_table;
    @FXML
    private Button addCategory_button;

    @FXML
    private AnchorPane user_form;

    private AddTypeController addTypeController; // AddTypeController reference

    private AddCategoryController addCategoryController;
    //------Entry attributes --------------


    @FXML
    private DatePicker entryDate;

    @FXML
    private AnchorPane entry_form;
    @FXML
    private TextField newquantity;

    @FXML
    private Label reference_label;
    @FXML
    private TextField searchField;

    @FXML
    private Label stockMax_label;

    @FXML
    private Label stock_label;
    @FXML
    private Button submitButton;
    @FXML
    private Label type_label;

    @FXML
    private Label category_label;
    @FXML
    private Label brand_label;

    @FXML
    private TableView<Entry> entryTable;

    @FXML
    private TableColumn<Entry,Integer > entryId_col;
    @FXML
    private TableColumn<Entry,String > entryReference_col;
    @FXML
    private TableColumn<Entry,Integer > entryQuantity_col;
    @FXML
    private TableColumn<Entry,LocalDate > entryDate_col;
    @FXML
    private Button entry_button;

    private ParametrageDeReferenceDAO paramRefDAO;
    private EntryDAO entryDAO;

    private void intializeEntryDAO(){

        try {
            paramRefDAO = new ParametrageDeReferenceDAO();
            entryDAO = new EntryDAO();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    //-----------------------------------

    private double x =0 ;
    private double y=0;
    //---------------------------------------Entries--------------------------------------------------------------------

    public ObservableList<Entry> addEntryListData() {


        EntryDAO entryDAO = null;

        ObservableList<Entry> entriesList  = FXCollections.observableArrayList();

        try {
            entryDAO = new EntryDAO();
            for (Entry ettemp : entryDAO.getAll())
                entriesList.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entriesList;

    }

    public void UpdateEntriesTable(){
        entryId_col.setCellValueFactory(new PropertyValueFactory<Entry,Integer>("id"));
        entryReference_col.setCellValueFactory(new PropertyValueFactory<Entry,String>("productReference"));

        entryQuantity_col.setCellValueFactory(new PropertyValueFactory<Entry,Integer>("quantity"));
        entryDate_col.setCellValueFactory(new PropertyValueFactory<Entry,LocalDate>("entryDate"));




        entryTable.setItems(this.addEntryListData());
    }

    @FXML
    private void searchFieldKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String reference = searchField.getText();

            try {
                ParametrageDeReference product = paramRefDAO.getByReference(reference);

                if (product != null) {
                    reference_label.setText(reference);
                    type_label.setText(product.getType().getName());
                    category_label.setText(product.getCategory().getName());
                    stock_label.setText(String.valueOf(product.getQuantity()));
                    brand_label.setText(product.getBrand());
                    stockMax_label.setText(String.valueOf(product.getStockMax()));

                } else {
                    System.out.println("Product not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any errors that may occur during database operations
            }
        }
    }

    @FXML
    private void submitButtonClicked() {
        String reference = searchField.getText();
        int quantity = Integer.parseInt(newquantity.getText());
        LocalDate date = entryDate.getValue();
        try {

            Entry newEntry = new Entry(reference, quantity, date);
            entryDAO.save(newEntry);


            paramRefDAO.updateQuantity(reference,quantity);

            UpdateEntriesTable();

            searchField.setText("");
            newquantity.setText("");
            entryDate.setValue(null);


        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any errors that may occur during database operations
        }
    }




    //------------------------------------parametrage de reference-----------------------------------------------------------
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

        category_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,String>("category"));
        serialNumber_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,Integer>("serialNumber"));
        marque_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,String>("brand"));
        reference_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,String>("reference"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,Integer>("quantity"));




        parametrageRef_table.setItems(this.addMaterialsListData());
    }


    private TypeDAO typeDAO;
    private CategoryDAO categoryDAO;

    private void initializeDAO() {
        try {
            typeDAO = new TypeDAO();
            categoryDAO = new CategoryDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to set the AddTypeController reference
    public void setAddTypeController(AddTypeController addTypeController) {
        this.addTypeController = addTypeController;
    }


    // Method to set the addCategoryController reference

    public void setAddCategoryController(AddCategoryController addCategoryController) {
        this.addCategoryController = addCategoryController;
    }

    @FXML
    private void populateTypeComboBox() {
        try {
            List<Type> types = typeDAO.getAll();
            type_comboBox.getItems().addAll(types);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void setTypeSelectionListener() {
        type_comboBox.setOnAction(event -> {
            Type selectedType = type_comboBox.getSelectionModel().getSelectedItem();
            if (selectedType != null) {
                if (!selectedType.getName().equalsIgnoreCase("consommable")) {
                    num_serie.setDisable(true);
                } else {
                    num_serie.setDisable(false);
                }
                try {
                    populateCategoryComboBox(selectedType);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @FXML
    private void populateCategoryComboBox(Type selectedType) throws SQLException {
        List<Category> categories = categoryDAO.getByType(selectedType);
        category_comboBox.getItems().clear();
        category_comboBox.getItems().addAll(categories);
    }
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
                         type_comboBox.getSelectionModel().getSelectedItem(),
                         category_comboBox.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(quantity.getText()),
                        Integer.parseInt(stock_max.getText()),
                        Integer.parseInt(stock_min.getText()),
                        reference.getText(),
                        marque.getText(),
                        serialNumberValue);

            } else {
                ref = new ParametrageDeReference(0l,
                        type_comboBox.getSelectionModel().getSelectedItem(),
                        category_comboBox.getSelectionModel().getSelectedItem(),
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
                selectedRef.setCategory(category_comboBox.getValue());
                selectedRef.setQuantity(Integer.parseInt(quantity.getText()));
                selectedRef.setStockMax(Integer.parseInt(stock_max.getText()));
                selectedRef.setStockMin(Integer.parseInt(stock_min.getText()));
                selectedRef.setReference(reference.getText());
                selectedRef.setBrand(marque.getText());

                if (type_comboBox.getValue() != null && !type_comboBox.getValue().getName().equalsIgnoreCase("consommable")) {
                    selectedRef.setSerialNumber(0);
                } else {
                    // Otherwise, update the serial number from the text field
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

    @FXML
    protected void addTypeScene(){
        try {
            // Load the addType_view.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addType_view.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller instance
            AddTypeController addTypeController = fxmlLoader.getController();
            setAddTypeController(addTypeController);

            // Set the ComboBox reference
            this.addTypeController.setComboBox(type_comboBox);

            // Create a new stage for the prompt scene
            Stage promptStage = new Stage();
            promptStage.setScene(new Scene(root));
            promptStage.setTitle("Ajouter un type");
            promptStage.initModality(Modality.APPLICATION_MODAL); // Prevent interaction with other windows
            promptStage.showAndWait(); // Show the prompt scene and wait for it to be closed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void addCategoryScene(){
        try {
            // Load the addType_view.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addCategory_view.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller instance
            AddCategoryController addCategoryController = fxmlLoader.getController();
            setAddCategoryController(addCategoryController);

            // Set the ComboBox reference
            this.addCategoryController.setAddTypeController(addTypeController);
            this.addCategoryController.setComboBox(category_comboBox);


            // Create a new stage for the prompt scene
            Stage promptStage = new Stage();
            promptStage.setScene(new Scene(root));
            promptStage.setTitle("Ajouter une catégorie");
            promptStage.initModality(Modality.APPLICATION_MODAL); // Prevent interaction with other windows
            promptStage.showAndWait(); // Show the prompt scene and wait for it to be closed



        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public void switchForm(ActionEvent event){


        if(event.getSource() == paramButton){
            parametrageRef_form.setVisible(true);
            historique_form.setVisible(false);
            user_form.setVisible(false);
            entry_form.setVisible(false);

            UpdateTable();
        } else if (event.getSource() == historyButton) {
            parametrageRef_form.setVisible(false);
            historique_form.setVisible(true);
            user_form.setVisible(false);
            entry_form.setVisible(false);

        } else if (event.getSource() == userButton){
            user_form.setVisible(true);
            parametrageRef_form.setVisible(false);
            historique_form.setVisible(false);
            entry_form.setVisible(false);
            UpdatedTable();
        }else if(event.getSource() == entry_button){
            user_form.setVisible(false);
            parametrageRef_form.setVisible(false);
            historique_form.setVisible(false);
            entry_form.setVisible(true);
            UpdateEntriesTable();

        }


    }

    //-------------------------------------------  user interface:  --------------------------------------------
    public ObservableList<Login> addUserListData() {
        LoginDAO loginDAO = null;
        ObservableList<Login> materialList  = FXCollections.observableArrayList();

        try {
            loginDAO = new LoginDAO();
            List<Login> logins = loginDAO.getAll();
            if (logins != null) {
                materialList.addAll(logins);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return materialList;
    }


    public void UpdatedTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Login,Long>("id"));
        col_user.setCellValueFactory(new PropertyValueFactory<Login,String>("username"));

        col_password.setCellValueFactory(new PropertyValueFactory<Login,String>("password"));
        col_statut.setCellValueFactory(new PropertyValueFactory<Login,String>("userType"));
        col_email.setCellValueFactory(new PropertyValueFactory<Login,String>("emailAddress"));





        gestionutili_table.setItems(this.addUserListData());
    }

    @FXML
    protected void onAjouterButtonClick() {

        // accees a la bdd

        try {
            LoginDAO loginDAO = new LoginDAO();

            Login refe;
            refe = new Login(0l , username.getText() ,password.getText(),statut.getText(),email.getText());

            loginDAO.save(refe);


            UpdatedTable();

            username.setText("");
            password.setText("");
            statut.setText("");
            email.setText("");




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    protected void ModifierButtonClick() {
        // get the selected item from the table
        Login selectedReff = gestionutili_table.getSelectionModel().getSelectedItem();


        try {
            // update the record in the database with the new values from the text fields
            selectedReff.setUsername(username.getText());
            selectedReff.setPassword(password.getText());
            selectedReff.setUserType(statut.getText());
            selectedReff.setEmailAddress(email.getText());



            LoginDAO loginDAO = new LoginDAO();
            loginDAO.update(selectedReff);

            // update the table view
            UpdatedTable();


            username.setText("");
            password.setText("");
            statut.setText("");
            email.setText("");




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void supprimerButtonClick() {

        Login selectedItems = gestionutili_table.getSelectionModel().getSelectedItem();



        try {
            LoginDAO loginDAO = new LoginDAO();

            loginDAO.delete(selectedItems);
            UpdatedTable();

            username.setText("");
            password.setText("");
            statut.setText("");
            email.setText("");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void onAnnulerButtonClick() {
        // Clear the text fields
        username.setText("");
        password.setText("");
        statut.setText("");
        email.setText("");
        // Clear the table selection
        gestionutili_table.getSelectionModel().clearSelection();
    }

    public void logout(){
        try{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you ant to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){


                FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
                Parent root = loader.load();
                Stage currentStage = (Stage) log.getScene().getWindow();
                Scene scene = new Scene(root);
                currentStage.setScene(scene);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();

                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    currentStage.setX(event.getScreenX());
                    currentStage.setY(event.getScreenY());

                    currentStage.setOpacity(0.8);

                });

                root.setOnMouseReleased((MouseEvent event)->{
                    currentStage.setOpacity(1);
                });

                currentStage.show();


            }else return;


        }catch(Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //To show data on the tableview

        UpdateTable();

        UpdatedTable();
        UpdateEntriesTable();

        //comboBoxes
        initializeDAO();
        populateTypeComboBox();
        setTypeSelectionListener();

        intializeEntryDAO();


        update_button.disableProperty().bind(parametrageRef_table.getSelectionModel().selectedItemProperty().isNull());
        delete_button.disableProperty().bind(parametrageRef_table.getSelectionModel().selectedItemProperty().isNull());

        //display the info of a selected item in the textFields
        parametrageRef_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Set the values of the selected item to the text fields
                num_serie.setText(String.valueOf(newSelection.getSerialNumber()));
                type_comboBox.getSelectionModel().select(newSelection.getType());
                category_comboBox.getSelectionModel().select(newSelection.getCategory());
                quantity.setText(String.valueOf(newSelection.getQuantity()));
                stock_max.setText(String.valueOf(newSelection.getStockMax()));
                stock_min.setText(String.valueOf(newSelection.getStockMin()));
                reference.setText(newSelection.getReference());
                marque.setText(newSelection.getBrand());
            }
        });

        //display the info of a selected user in the textFields
        gestionutili_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Set the values of the selected item to the text fieldsadmin
                username.setText(newSelection.getUsername());
                password.setText(newSelection.getPassword());
                statut.setText(newSelection.getUserType());
                email.setText(newSelection.getEmailAddress());

            }
        });


    }
}

