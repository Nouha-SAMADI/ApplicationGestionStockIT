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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            ParametrageDeReferenceDAO paramDAO = new ParametrageDeReferenceDAO();

            ParametrageDeReference ref = new ParametrageDeReference(0l , (String)type_comboBox.getSelectionModel().getSelectedItem() ,(String) category_comboBox.getSelectionModel().getSelectedItem(),Integer.parseInt(quantity.getText()),Integer.parseInt(stock_max.getText()),Integer.parseInt(stock_min.getText()),reference.getText(),marque.getText(),Integer.parseInt(num_serie.getText()));

            paramDAO.save(ref);


            UpdateTable();




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
    }

    private String[] listCategorie = {"Ordinateur","Imprimante","Cable"};
    public void addRefListCategorie(){
        List<String> listCat = new ArrayList<>();

        for(String data: listCategorie ){
            listCat .add(data);
        }

        ObservableList <String> listData = FXCollections.observableArrayList(listCat );
        category_comboBox.setItems(listData);
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

    }
}