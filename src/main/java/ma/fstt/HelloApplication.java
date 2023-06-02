package ma.fstt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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




        stage.setTitle("StockTrack");

        // stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.show();
    }


    public static void setScene(Scene scene) {
        myStage.setScene(scene);
    }

    public static Scene getScene(){ return myStage.getScene(); }

    public static Stage getStage() {
        return myStage;
    }


    public static void main(String[] args) {
        launch();
    }
}