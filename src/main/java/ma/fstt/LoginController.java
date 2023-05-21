package ma.fstt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import model.LoginDAO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void onLoginClickButton() {
        try {
            LoginDAO ldao = new LoginDAO();
            if (ldao.authentifier(username.getText(), password.getText())) {
                Login loggedInUser = ldao.getLoggedInUser(username.getText());
                SessionManager.setLoggedInUser(loggedInUser);
                if (loggedInUser.getUserType().equals("admin")) {
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
                    try {
                        Scene myScene = new Scene(loader.load(), 1100, 616);
                        HelloApplication.setScene(myScene);
                    } catch (IOException e) {
                        showErrorMessage("Erreur", "Opération n'a pas pu être effectuée", e.toString());
                    }
                } else if (loggedInUser.getUserType().equals("user")) {
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    try {
                        Scene myScene = new Scene(loader.load(), 750, 470);
                        HelloApplication.setScene(myScene);
                    } catch (IOException e) {
                        showErrorMessage("Erreur", "Opération n'a pas pu être effectuée", e.toString());
                    }
                }
            } else {
                showErrorMessage("Accès non autorisé", "Erreur Authentification échouée", "Les valeurs saisies ne sont pas correctes");
            }
        } catch (SQLException e) {
            showErrorMessage("Erreur", "Connexion à la base de données", e.toString());
        }
    }

    private void showErrorMessage(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(HelloApplication.getStage());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
