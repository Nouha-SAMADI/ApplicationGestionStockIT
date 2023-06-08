package ma.fstt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage myStage;
    @Override
    public void start(Stage stage) throws IOException {
        myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 789, 516);




        stage.setTitle("StockTrack Polydesign Systems version 1.0.1");

        // stage.initStyle(StageStyle.TRANSPARENT);
        Image icon = new Image(getClass().getResourceAsStream("/icons/icon.png"));

        // Set the application's icon
        stage.getIcons().add(icon);


        stage.setScene(scene);
        stage.show();
    }


    public static void setScene(Scene scene) {
        myStage.setScene(scene);
    }

    private static Rectangle2D getScreenBounds() {
        return Screen.getPrimary().getVisualBounds();
    }

    public static Scene getScene(){ return myStage.getScene(); }

    public static Stage getStage() {
        return myStage;
    }


    public static void main(String[] args) {
        launch();
    }
}