package ma.fstt;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class AdminController implements Initializable {


    private double x =0 ;
    private double y=0;
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

    private AddTypeController addTypeController; // AddTypeController reference

    private AddCategoryController addCategoryController;
    @FXML
    private Button addCategory_button;
    //---------------------------------User attributes----------------
    @FXML
    private Button ajouter;

    @FXML
    private Button annuler;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
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
    private ComboBox<String> userType_comboBox;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private TableView<Login> gestionutili_table;


    @FXML
    private AnchorPane user_form;
    @FXML
    private Button exportButton;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Label usernameText;


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
    @FXML
    private List<String> referenceList;
    private FilteredList<String> filteredReferences;


    //------------------Sortie Attributes ------------------------
    @FXML
    private AnchorPane sortie_form;
    @FXML
    private Button search_button;
    @FXML
    private Button sortie_submit;
    @FXML
    private TextField searchField_sortie;
    @FXML
    private Label sortie_reference;
    @FXML
    private Label sortie_type;
    @FXML
    private Label sortie_category;
    @FXML
    private Label sortie_currentStock;
    @FXML
    private Label sortie_brand;
    @FXML
    private Label sortie_stockMin;
    @FXML
    private TextField sortie_quantity;
    @FXML
    private DatePicker sortie_date;
    private ParametrageDeReferenceDAO sortie_paramRefDAO;
    private SortieDAO sortieDAO;
    @FXML
    private List<String> referenceList_sortie;
    private FilteredList<String> filteredReferences_sortie;
    @FXML
    private Button sortie_button;
    @FXML
    private TableView<Sortie> sortie_table;

    @FXML
    private TableColumn<Sortie,Long > sortie_id_col;
    @FXML
    private TableColumn<Sortie,String > sortie_reference_col;
    @FXML
    private TableColumn<Sortie,Integer > sortie_quantity_col;
    @FXML
    private TableColumn<Sortie,LocalDate > sortie_date_col;



    //-----------------History attributes------------------
    @FXML
    private TableView<Sortie> history_table;

    @FXML
    private TableColumn<Sortie,Long > history_id;
    @FXML
    private TableColumn<Sortie,String > history_reference;
    @FXML
    private TableColumn<Sortie,Integer > history_quantity;
    @FXML
    private TableColumn<Sortie,LocalDate > history_date;
    @FXML
    private TableColumn<Sortie,String > history_user;
    //----------------------Dashboard----------------
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private Button dashboard_button;

    @FXML
    private TableView<ParametrageDeReference> tableView;

    @FXML
    private TableColumn<ParametrageDeReference, String> referenceColumn;

    @FXML
    private TableColumn<ParametrageDeReference, String> brandColumn;

    @FXML
    private TableColumn<ParametrageDeReference, Integer> quantityColumn;

    @FXML
    private TableColumn<ParametrageDeReference, String> stripColumn;
    @FXML
    private TableColumn<ParametrageDeReference, String> categoryColumun;
    @FXML
    private BarChart<String, Number> chart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    @FXML
    private Label lastArrival_category;

    @FXML
    private Label lastArrival_quantity;

    @FXML
    private Label lastArrival_reference;




    //-----------------------------History----------------------------



    public ObservableList<Sortie> addHistoryList(){


        SortieDAO sortieDAO = null;

        ObservableList<Sortie> materialList  = FXCollections.observableArrayList();

        try {
            sortieDAO = new SortieDAO();
            for (Sortie ettemp : sortieDAO.getAllWithUsernames())
                materialList.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return materialList;
    }

    public void UpdateHistoryTable(){
//        history_id.setCellValueFactory(new PropertyValueFactory<Sortie,Long>("id"));
        history_reference.setCellValueFactory(new PropertyValueFactory<Sortie,String>("productReference"));

        history_quantity.setCellValueFactory(new PropertyValueFactory<Sortie,Integer>("quantity"));
        history_date.setCellValueFactory(new PropertyValueFactory<Sortie,LocalDate>("sortieDate"));
        history_user.setCellValueFactory(new PropertyValueFactory<Sortie,String>("username"));




        history_table.setItems(this.addHistoryList());
    }


    //----------------------------------------Sorties -------------------------------------

    private void intializeSortieDAO(){

        try {
            sortie_paramRefDAO = new ParametrageDeReferenceDAO();
            sortieDAO = new SortieDAO();
            referenceList_sortie = sortie_paramRefDAO.getAllReferences(); // Retrieve all references from the database
            filteredReferences_sortie = new FilteredList<>(FXCollections.observableArrayList(referenceList_sortie));
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    @FXML
    private void sortieSearchButtonClicked() {
        String reference = searchField_sortie.getText();
        if (reference.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter the reference of the product.");
            return;
        }

        try {
            ParametrageDeReference product = sortie_paramRefDAO.getByReference(reference);

            if (product != null) {
                sortie_reference.setText(reference);
                sortie_type.setText(product.getType().getName());
                sortie_category.setText(product.getCategory().getName());
                sortie_currentStock.setText(String.valueOf(product.getQuantity()));
                sortie_brand.setText(product.getBrand());
                sortie_stockMin.setText(String.valueOf(product.getStockMin()));
            } else {
                showAlert(Alert.AlertType.INFORMATION, null, null, "Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any errors that may occur during database operations
        }
    }
    @FXML
    private void sortieSearchFieldKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String reference = searchField_sortie.getText();
            if (reference.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter the reference of the product.");
                return;
            }

            try {
                ParametrageDeReference product = sortie_paramRefDAO.getByReference(reference);

                if (product != null) {
                    sortie_reference.setText(reference);
                    sortie_type.setText(product.getType().getName());
                    sortie_category.setText(product.getCategory().getName());
                    sortie_currentStock.setText(String.valueOf(product.getQuantity()));
                    sortie_brand.setText(product.getBrand());
                    sortie_stockMin.setText(String.valueOf(product.getStockMin()));
                } else {
                    showAlert(Alert.AlertType.INFORMATION, null, null, "Product not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any errors that may occur during database operations
            }
        }
    }


    @FXML
    private void sortieSubmitButtonClicked() {
        String reference = searchField_sortie.getText();

        String quantityText = sortie_quantity.getText();

        if (quantityText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter the quantity.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            ParametrageDeReference product = sortie_paramRefDAO.getByReference(reference);

            if (product != null) {
                int updatedQuantity = product.getQuantity() - quantity;
                int stockMin = product.getStockMin();

                if (quantity > product.getQuantity()) {
                    // Display an alert for exceeding the available stock
                    String alertMessage = "The entered quantity exceeds the available stock.\n";
                    alertMessage += "Please enter a valid quantity less than or equal to " + product.getQuantity();

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Exceeding Available Stock");
                    alert.setContentText(alertMessage);
                    alert.showAndWait();

                    // Clear the quantity field
                    sortie_quantity.setText("");
                } else if (quantity == product.getQuantity()) {
                    // Display a confirmation alert for depleting the stock
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Depleting Stock");
                    alert.setContentText("Are you sure you want to retrieve the entire stock?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        // Create and save the new sortie
                        Login loggedInUser = SessionManager.getLoggedInUser();
                        Sortie newSortie = new Sortie(reference, quantity, loggedInUser.getId());
                        sortieDAO.save(newSortie);

                        // Update the product quantity
                        sortie_paramRefDAO.updateSortieQuantity(reference, quantity);
                        UpdateSortieTable();

                        // Clear the fields
                        searchField_sortie.setText("");
                        sortie_quantity.setText("");

                        sortie_reference.setText("");
                        sortie_type.setText("");
                        sortie_category.setText("");
                        sortie_currentStock.setText("");
                        sortie_brand.setText("");
                        sortie_stockMin.setText("");

                    }
                } else {
                    // Create and save the new sortie
                    Login loggedInUser = SessionManager.getLoggedInUser();
                    Sortie newSortie = new Sortie(reference, quantity, loggedInUser.getId());
                    sortieDAO.save(newSortie);

                    // Update the product quantity
                    sortie_paramRefDAO.updateSortieQuantity(reference, quantity);
                    UpdateSortieTable();

                    // Clear the fields
                    searchField_sortie.setText("");
                    sortie_quantity.setText("");

                    sortie_reference.setText("");
                    sortie_type.setText("");
                    sortie_category.setText("");
                    sortie_currentStock.setText("");
                    sortie_brand.setText("");
                    sortie_stockMin.setText("");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any errors that may occur during database operations
        }
    }


    private void sortieSetupAutoCompletion() {
        // Fetch references from the database
        List<String> references = null;
        try {
            references = paramRefDAO.getAllReferences();


            TextFields.bindAutoCompletion(searchField, references).setOnAutoCompleted(event -> {
                String selectedReference = event.getCompletion();

                try {
                    ParametrageDeReference product = sortie_paramRefDAO.getByReference(selectedReference);

                    if (product != null) {
                        sortie_reference.setText(selectedReference);
                        sortie_type.setText(product.getType().getName());
                        sortie_category.setText(product.getCategory().getName());
                        sortie_currentStock.setText(String.valueOf(product.getQuantity()));
                        sortie_brand.setText(product.getBrand());
                        sortie_stockMin.setText(String.valueOf(product.getStockMin()));
                    } else {
                        System.out.println("Product not found.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle any errors that may occur during database operations
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Sortie> addSortieList(){


        SortieDAO sortieDAO = null;

        ObservableList<Sortie> materialList  = FXCollections.observableArrayList();

        try {
            sortieDAO = new SortieDAO();
            for (Sortie ettemp : sortieDAO.getAll())
                materialList.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return materialList;
    }

    public void UpdateSortieTable(){
//        sortie_id_col.setCellValueFactory(new PropertyValueFactory<Sortie,Long>("id"));
        sortie_reference_col.setCellValueFactory(new PropertyValueFactory<Sortie,String>("productReference"));

        sortie_quantity_col.setCellValueFactory(new PropertyValueFactory<Sortie,Integer>("quantity"));
        sortie_date_col.setCellValueFactory(new PropertyValueFactory<Sortie,LocalDate>("sortieDate"));

        sortie_table.setItems(this.addSortieList());
    }


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
//        entryId_col.setCellValueFactory(new PropertyValueFactory<Entry,Integer>("id"));
        entryReference_col.setCellValueFactory(new PropertyValueFactory<Entry,String>("productReference"));

        entryQuantity_col.setCellValueFactory(new PropertyValueFactory<Entry,Integer>("quantity"));
        entryDate_col.setCellValueFactory(new PropertyValueFactory<Entry,LocalDate>("entryDate"));




        entryTable.setItems(this.addEntryListData());
    }

    private void intializeEntryDAO(){

        try {
            paramRefDAO = new ParametrageDeReferenceDAO();
            entryDAO = new EntryDAO();
            referenceList = paramRefDAO.getAllReferences(); // Retrieve all references from the database
            filteredReferences = new FilteredList<>(FXCollections.observableArrayList(referenceList));
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @FXML

    private void submitButtonClicked() {
        String reference = searchField.getText();
        String quantityText = newquantity.getText();

        if (quantityText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter the quantity.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            ParametrageDeReference product = paramRefDAO.getByReference(reference);

            if (product != null) {
                int updatedQuantity = product.getQuantity() + quantity;
                int stockMax = product.getStockMax();

                if (quantity > stockMax || updatedQuantity > stockMax) {
                    // Display an alert for exceeding stock max
                    String alertMessage = "The entered quantity exceeds the stock maximum.\n";
                    alertMessage += "Please enter a valid quantity less than or equal to " + (stockMax - product.getQuantity());

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Exceeding Stock Maximum");
                    alert.setContentText(alertMessage);
                    alert.showAndWait();

                    // Clear the newquantity field
                    newquantity.setText("");
                } else {
                    // Create and save the new entry
                    Entry newEntry = new Entry(reference, quantity);
                    entryDAO.save(newEntry);

                    // Update the product quantity
                    paramRefDAO.updateQuantity(reference, quantity);

                    // Update the table
                    UpdateEntriesTable();

                    // Clear the fields
                    searchField.setText("");
                    newquantity.setText("");
                    reference_label.setText("");
                    type_label.setText("");
                    category_label.setText("");
                    stock_label.setText("");
                    brand_label.setText("");
                    stockMax_label.setText("");

                    showAlert(Alert.AlertType.INFORMATION, "Success", null, "Product entry saved successfully.");
                }
            } else {
                showAlert(Alert.AlertType.INFORMATION, null, null, "Product not found.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter a valid quantity.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any errors that may occur during database operations
        }
    }

    @FXML
    private void searchFieldKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String reference = searchField.getText();
            if (reference.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter the reference of the product.");
                return;
            }

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
                    showAlert(Alert.AlertType.INFORMATION, null, null, "Product not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any errors that may occur during database operations
            }
        }
    }

    @FXML
    private void searchButtonClicked() {
        String reference = searchField.getText();
        if (reference.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter the reference of the product.");
            return;
        }

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
                showAlert(Alert.AlertType.INFORMATION, null, null, "Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any errors that may occur during database operations
        }
    }




    private void setupAutoCompletion() {
        try {
            // Fetch references from the database
            List<String> references = paramRefDAO.getAllReferences();

            TextFields.bindAutoCompletion(searchField, references).setOnAutoCompleted(event -> {
                String selectedReference = event.getCompletion();

                try {
                    ParametrageDeReference product = paramRefDAO.getByReference(selectedReference);

                    if (product != null) {
                        reference_label.setText(selectedReference);
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
            });
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
//        id_col.setCellValueFactory(new PropertyValueFactory<ParametrageDeReference,Long>("id"));
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

        // Access the database

        try {
            ParametrageDeReferenceDAO paramDAO = new ParametrageDeReferenceDAO();

            String serialNumberValue = null;
            if (num_serie.getText() != null && !num_serie.getText().isEmpty()) {
                serialNumberValue = num_serie.getText();
            }

            // Check if any required fields are empty
            if (type_comboBox.getSelectionModel().isEmpty()) {
                showAlert("Error", "Please select a type.");
                return; // Stop execution if type is empty
            }
            if (category_comboBox.getSelectionModel().isEmpty()) {
                showAlert("Error", "Please select a category.");
                return; // Stop execution if category is empty
            }
            if (quantity.getText().isEmpty()) {
                showAlert("Error", "Please enter a quantity.");
                return; // Stop execution if quantity is empty
            }
            if (stock_max.getText().isEmpty()) {
                showAlert("Error", "Please enter a maximum stock value.");
                return; // Stop execution if maximum stock is empty
            }
            if (stock_min.getText().isEmpty()) {
                showAlert("Error", "Please enter a minimum stock value.");
                return; // Stop execution if minimum stock is empty
            }
            if (reference.getText().isEmpty()) {
                showAlert("Error", "Please enter a reference.");
                return; // Stop execution if reference is empty
            }
            if (marque.getText().isEmpty()) {
                showAlert("Error", "Please enter a brand.");
                return; // Stop execution if marque is empty
            }
            String selectedTypeName =  type_comboBox.getSelectionModel().getSelectedItem().getName();
            TypeDAO typeDAO = new TypeDAO();
            Type selectedType = typeDAO.getByName(selectedTypeName);
            String selectedCategoryName = category_comboBox.getSelectionModel().getSelectedItem().getName();
            CategoryDAO categoryDAO = new CategoryDAO();
            Category selectedCategory = categoryDAO.getByName(selectedCategoryName);


            ParametrageDeReference ref;
            if (serialNumberValue != null) {
                ref = new ParametrageDeReference(0l,
                        selectedType,
                        selectedCategory,
                        Integer.parseInt(quantity.getText()),
                        Integer.parseInt(stock_max.getText()),
                        Integer.parseInt(stock_min.getText()),
                        reference.getText(),
                        marque.getText(),
                        serialNumberValue);

            } else {
                ref = new ParametrageDeReference(0l,
                        selectedType,
                        selectedCategory,
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
            showAlert(Alert.AlertType.INFORMATION, "Success", null, "Product saved successfully.");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
                    selectedRef.setSerialNumber(null);
                } else {
                    // Otherwise, update the serial number from the text field
                    if (num_serie.getText() != null && !num_serie.getText().isEmpty()) {
                        selectedRef.setSerialNumber(num_serie.getText());
                    } else {
                        selectedRef.setSerialNumber(null);
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

                showAlert(Alert.AlertType.INFORMATION, "Success", null, "Product modified successfully.");



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
            showAlert(Alert.AlertType.INFORMATION, "Success", null, "Product deleted successfully.");

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
            promptStage.setTitle("Add type");
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
            this.addCategoryController.setTypeComboBox(type_comboBox);
            this.addCategoryController.setComboBox(category_comboBox);


            // Create a new stage for the prompt scene
            Stage promptStage = new Stage();
            promptStage.setScene(new Scene(root));
            promptStage.setTitle("Add category");
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
            sortie_form.setVisible(false);
            dashboard_form.setVisible(false);

            UpdateTable();
        } else if (event.getSource() == historyButton) {
            parametrageRef_form.setVisible(false);
            historique_form.setVisible(true);
            user_form.setVisible(false);
            entry_form.setVisible(false);
            sortie_form.setVisible(false);
            dashboard_form.setVisible(false);
            UpdateHistoryTable();

        } else if (event.getSource() == userButton){
            user_form.setVisible(true);
            parametrageRef_form.setVisible(false);
            historique_form.setVisible(false);
            entry_form.setVisible(false);
            sortie_form.setVisible(false);
            dashboard_form.setVisible(false);
            UpdateUserTable();;
        }else if(event.getSource() == entry_button){
            user_form.setVisible(false);
            parametrageRef_form.setVisible(false);
            historique_form.setVisible(false);
            entry_form.setVisible(true);
            sortie_form.setVisible(false);
            dashboard_form.setVisible(false);
            UpdateEntriesTable();

        }else if(event.getSource() == sortie_button) {
            user_form.setVisible(false);
            parametrageRef_form.setVisible(false);
            historique_form.setVisible(false);
            entry_form.setVisible(false);
            sortie_form.setVisible(true);
            dashboard_form.setVisible(false);
            UpdateSortieTable();
        }else if (event.getSource() == dashboard_button) {
            parametrageRef_form.setVisible(false);
            historique_form.setVisible(false);
            user_form.setVisible(false);
            entry_form.setVisible(false);
            sortie_form.setVisible(false);
            dashboard_form.setVisible(true);
            configureTableColumns();

            lastArrivals();
            fillProductChart();
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


    public void UpdateUserTable(){
//        col_id.setCellValueFactory(new PropertyValueFactory<Login,Long>("id"));
        col_user.setCellValueFactory(new PropertyValueFactory<Login,String>("username"));

        col_password.setCellValueFactory(new PropertyValueFactory<Login,String>("password"));
        col_statut.setCellValueFactory(new PropertyValueFactory<Login,String>("userType"));
        col_email.setCellValueFactory(new PropertyValueFactory<Login,String>("emailAddress"));





        gestionutili_table.setItems(this.addUserListData());
    }


    @FXML
    protected void onChooseImageButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) username.getScene().getWindow();
        File profilePictureFile = fileChooser.showOpenDialog(stage);
        if (profilePictureFile != null) {
            // Get the absolute file path
            String profilePicturePath = profilePictureFile.getAbsolutePath();

            // Update the portrait image
            Image profilePicture = new Image(profilePictureFile.toURI().toString());
            imageView.setImage(profilePicture);

            // Save the profile picture path for later use
            imageView.setUserData(profilePicturePath);

            System.out.println("Profile picture chosen successfully.");
        }
    }

    @FXML
    protected void onAjouterButtonClick() {
        try {
            LoginDAO loginDAO = new LoginDAO();

            if (username.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter a username.");
                return;
            }
            if (password.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter a password.");
                return;
            }



            String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
            String enteredEmail = email.getText().trim();

            if (!enteredEmail.matches(emailPattern)) {
                showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter a valid Gmail address.");
                return;
            }
            String profilePicturePath = (String) imageView.getUserData();
            if (profilePicturePath == null) {
                showAlert(Alert.AlertType.WARNING, "Error", null, "Please choose a profile picture.");
                return;
            }

            // Save the Login object in the database
            Login refe = new Login(0L, username.getText(), password.getText(), userType_comboBox.getValue(), enteredEmail);
            refe.setProfilePicturePath(profilePicturePath);

            loginDAO.save(refe);
            UpdateUserTable();


            // Clear the form fields
            username.setText("");
            password.setText("");
            userType_comboBox.getSelectionModel().clearSelection();
            email.setText("");
            imageView.setImage(null);
            imageView.setUserData(null);
            showAlert(Alert.AlertType.INFORMATION, "Success", null, "User saved successfully.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    protected void ModifierButtonClick() {
        // get the selected item from the table
        Login selectedReff = gestionutili_table.getSelectionModel().getSelectedItem();

        if (selectedReff == null) {
            showAlert(Alert.AlertType.WARNING, "Error", null, "Please select a user to modify.");
            return;
        }

        try {
            // update the record in the database with the new values from the text fields
            selectedReff.setUsername(username.getText());
            selectedReff.setPassword(password.getText());
            selectedReff.setUserType(userType_comboBox.getValue());
            selectedReff.setEmailAddress(email.getText());

            String profilePicturePath = (String) imageView.getUserData();
            if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
                selectedReff.setProfilePicturePath(profilePicturePath);
            }

            // Get the updated profile picture path from the selectedReff object
            String updatedProfilePicturePath = selectedReff.getProfilePicturePath();

            LoginDAO loginDAO = new LoginDAO();
            loginDAO.update(selectedReff);

            // Update the logged-in user's profile picture and name if the logged-in user is the one being modified
            Login loggedInUser = SessionManager.getLoggedInUser();
            if (loggedInUser != null && loggedInUser.getId() == selectedReff.getId()) {
                loggedInUser.setUsername(selectedReff.getUsername());
                loggedInUser.setPassword(selectedReff.getPassword());
                loggedInUser.setUserType(selectedReff.getUserType());
                loggedInUser.setEmailAddress(selectedReff.getEmailAddress());
                loggedInUser.setProfilePicturePath(updatedProfilePicturePath);
            }


            // Update the table view
            UpdateUserTable();


            username.setText("");
            password.setText("");
            userType_comboBox.getSelectionModel().clearSelection();
            email.setText("");

            // Update the profile picture displayed in the UI if the logged-in user is the one being modified
            if (loggedInUser != null && loggedInUser.getId() == selectedReff.getId()) {
                 profilePicturePath = loggedInUser.getProfilePicturePath();
                Image profilePicture = new Image("file:" + profilePicturePath);
                this.profilePicture.setImage(profilePicture);

                String username = loggedInUser.getUsername();
                usernameText.setText(username);
            }

            imageView.setImage(null);
            imageView.setUserData(null);
            showAlert(Alert.AlertType.INFORMATION, "Success", null, "User modified successfully.");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    protected void supprimerButtonClick() {

        Login selectedItems = gestionutili_table.getSelectionModel().getSelectedItem();
        if (selectedItems == null) {
            showAlert(Alert.AlertType.WARNING, "Error", null, "Please select a user to delete.");
            return;
        }




        try {
            LoginDAO loginDAO = new LoginDAO();

            loginDAO.delete(selectedItems);
            UpdateUserTable();


            username.setText("");
            password.setText("");
            userType_comboBox.getSelectionModel().clearSelection();
            email.setText("");
            imageView.setImage(null);
            imageView.setUserData(null);
            showAlert(Alert.AlertType.INFORMATION, "Success", null, "User deleted successfully.");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void onAnnulerButtonClick() {
        // Clear the text fields
        username.setText("");
        password.setText("");
        userType_comboBox.getSelectionModel().clearSelection();
        email.setText("");
        // Clear the table selection
        gestionutili_table.getSelectionModel().clearSelection();
        imageView.setImage(null);
        imageView.setUserData(null);
    }
    private ObservableList<String> userTypeList;

    @FXML
    private void setUpUserComboBox() {
        userTypeList = FXCollections.observableArrayList("user", "admin");

        if (userType_comboBox != null) {
            userType_comboBox.setItems(userTypeList);
        } else {
            showUserTypeErrorAlert();
        }
    }

    private void showUserTypeErrorAlert() {
        showAlert(Alert.AlertType.WARNING, "Error", null, "Please choose a user type");
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


    //------------------------------------------- -------------------------------------------

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
    public ObservableList<ParametrageDeReference> addDashboardTableList(){


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


    private void configureTableColumns() {
        // Set the initial items for the table
        tableView.setItems(addDashboardTableList());
        // Configure the reference column
        categoryColumun.setCellValueFactory(new PropertyValueFactory<>("category"));
        referenceColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));

        // Configure the brand column
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        // Configure the quantity column
        quantityColumn.setCellValueFactory(param -> {
            ParametrageDeReference product = param.getValue();
            IntegerBinding quantityBinding = Bindings.createIntegerBinding(() -> product.getQuantity());
            quantityBinding.addListener((observable, oldValue, newValue) -> {
                tableView.setItems(addDashboardTableList());
            });
            return quantityBinding.asObject();
        });
    }

    private void configureStripColumn() {
        // Create the strip column
        TableColumn<ParametrageDeReference, String> stripColumn = new TableColumn<>("Etat de stock");
        stripColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getReference()));
        stripColumn.setCellFactory(column -> new TableCell<ParametrageDeReference, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    ParametrageDeReference product = getTableView().getItems().get(getIndex());
                    String stripColor = "";
                    String stripText = "";

                    // Determine the strip color and text based on the stock quantity
                    if (product.getQuantity() > product.getStockMin()) {
                        stripColor = "green";
                        stripText = "Available";
                    } else if (product.getQuantity() == product.getStockMin()) {
                        stripColor = "orange";
                        stripText = "Need to order";
                    } else if (product.getQuantity() < product.getStockMin() && product.getQuantity() > 0) {
                        stripColor = "gray";
                        stripText = "Almost out of stock";
                    } else if (product.getQuantity() == 0) {
                        stripColor = "red";
                        stripText = "Out of stock";
                    }

                    // Set the strip color and text
                    String stripStyle = String.format("-fx-background-color: %s;", stripColor);
                    setStyle(stripStyle);
                    setText(stripText);
                }
            }
        });

        // Add the strip column to the table
        tableView.getColumns().add(stripColumn);

        // Update the strip color and text whenever the table view is refreshed
        tableView.setItems(addDashboardTableList());
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
                if (param.getQuantity() == param.getStockMin()) {
                    String notificationMessage = "Product " + param.getCategory() + " (" + param.getReference() + ") is almost out of stock.";
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
                .position(Pos.BASELINE_RIGHT)
                .hideAfter(Duration.seconds(60))
                .showInformation();
    }


    private void showProductCountsChart(Map<String, Integer> productCounts) {
        // Create the chart axes
        xAxis.setLabel("Product Reference");
        yAxis.setLabel("Frequency");

        // Create the data series for the chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Product Count");
        for (Map.Entry<String, Integer> entry : productCounts.entrySet()) {
            String productReference = entry.getKey();
            int count = entry.getValue();
            series.getData().add(new XYChart.Data<>(productReference, count));
        }

        // Add the data series to the chart
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList(series);
        chart.setData(data);
    }

    private void fillProductChart(){
        // Retrieve the overall product counts and most retrieved product per month from the SortieDAO
        SortieDAO sortieDAO = null;
        try {
            sortieDAO = new SortieDAO();
            Map<String, Integer> productCounts = sortieDAO.getOverallProductCounts();
            Map<String, String> mostRetrievedProducts = sortieDAO.getMostRetrievedProductPerMonth();

            // Generate and display the product counts chart
            showProductCountsChart(productCounts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void lastArrivals(){
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
                    lastArrival_reference.setText( productReference);
                    lastArrival_quantity.setText(String.valueOf(quantity));
                    lastArrival_category.setText(category);
                } else {
                    lastArrival_category.setText("Category not found");

                }
            } else {
                lastArrival_category.setText("No entries found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void showUserProfile(){
        Login loggedInUser = SessionManager.getLoggedInUser();

        if (loggedInUser != null) {
            String profilePicturePath = loggedInUser.getProfilePicturePath();
            Image profilePicture = new Image("file:" + profilePicturePath);
            this.profilePicture.setImage(profilePicture);

            String username = loggedInUser.getUsername();
            usernameText.setText(username);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        showUserProfile();

        //To show data on the tableview

        UpdateTable();
        setUpUserComboBox();

        UpdateUserTable();;
        UpdateEntriesTable();
        UpdateHistoryTable();
        UpdateSortieTable();

        //comboBoxes
        initializeDAO();
        populateTypeComboBox();
        setTypeSelectionListener();

        intializeEntryDAO();
        intializeSortieDAO();
        checkStockAndShowNotification();
        lastArrivals();
        fillProductChart();




        update_button.disableProperty().bind(parametrageRef_table.getSelectionModel().selectedItemProperty().isNull());
        delete_button.disableProperty().bind(parametrageRef_table.getSelectionModel().selectedItemProperty().isNull());


        modifier.disableProperty().bind(gestionutili_table.getSelectionModel().selectedItemProperty().isNull());
        supprimer.disableProperty().bind(gestionutili_table.getSelectionModel().selectedItemProperty().isNull());

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

        // Assuming you have an ImageView component named 'imageView' in your FXML file

        gestionutili_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    // Set the values of the selected item to the text fields
                    username.setText(newSelection.getUsername());
                    password.setText(newSelection.getPassword());

                    email.setText(newSelection.getEmailAddress());

                    // Load the profile picture of the selected user
                    String profilePicturePath = newSelection.getProfilePicturePath();
                    if (profilePicturePath != null) {
                        Image profilePicture = new Image(new File(profilePicturePath).toURI().toString());
                        imageView.setImage(profilePicture);
                    } else {
                        // If no profile picture is available, clear the image view
                        imageView.setImage(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //search suggestions
        // Bind the filteredReferences to the searchField
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();

            try {
                List<String> allReferences = paramRefDAO.getAllReferences();
                List<String> filteredReferences = new ArrayList<>();

                for (String reference : allReferences) {
                    if (reference.toLowerCase().contains(searchText)) {
                        filteredReferences.add(reference);
                    }
                }

                // Update the auto-completion suggestions
                TextFields.bindAutoCompletion(searchField, filteredReferences);

            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any errors that may occur during database operations
            }
        });
        // Set up auto-completion and onAutoCompleted event listener
        setupAutoCompletion();

        searchField_sortie.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();

            try {
                List<String> allReferences = paramRefDAO.getAllReferences();
                List<String> filteredReferences = new ArrayList<>();

                for (String reference : allReferences) {
                    if (reference.toLowerCase().contains(searchText)) {
                        filteredReferences.add(reference);
                    }
                }

                // Update the auto-completion suggestions
                TextFields.bindAutoCompletion(searchField_sortie, filteredReferences);

            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any errors that may occur during database operations
            }
        });


        sortieSetupAutoCompletion();



        // Configure the table columns
        configureTableColumns();

        // Configure the strip column
        configureStripColumn();






    }

}





