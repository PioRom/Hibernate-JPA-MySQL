package Controllers.Admin;

import Controllers.Controller;
import Models.LoginAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginPanel extends Controller {
    @FXML
    private Button SignInButton;

    @FXML
    private Text infoText;

    @FXML
    private TextField inputUsername;

    @FXML
    private PasswordField inputPassword;

    @FXML
    void signInAction(ActionEvent event) throws IOException {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        LoginAdmin login = new LoginAdmin();
        if(login.checkInputs(username,password)){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("AdminMainPanel.fxml"));
            Parent root = loader.load();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        }
        else infoText.setText("Incorrect login or password");
    }

    @FXML
    public void initialize() {
        inputUsername.setText("Admin");
        inputPassword.setText("123");
    }

    public void clickReturnButton(ActionEvent event) throws IOException {
        init();
        changeView("StartPanel.fxml", event);
    }
}
