package Controllers.Customer;

import Controllers.Controller;
import Models.CustomerLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerLoginPanel extends Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignInButton;

    @FXML
    private Text message_1;

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField inputUsername;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private CheckBox rememberCheckBox;

    @FXML
    void initialize() throws FileNotFoundException {
        if(CustomerLogin.checkConfig()) {
            rememberCheckBox.setSelected(true);
            List<String> loginList = CustomerLogin.getLoginInputs();
            inputUsername.setText(loginList.get(0));
            inputPassword.setText(loginList.get(1));
        }
    }

    public void signInAction(ActionEvent event) throws IOException {
        CustomerLogin login = new CustomerLogin();
        String username = inputUsername.getText();
        String password = inputPassword.getText();

        if (login.verifyClient(username, password)==true) {
            customer = login.getCustomer(username);
            login.saveConfig(rememberCheckBox.isSelected(), username, password);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("CustomerMainPanel.fxml"));
            Parent root = loader.load();

            CustomerMainPanel controller = loader.getController();
            controller.setCustomer(customer);
            controller.firstView();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();

        } else message_1.setText("Username or password is incorrect");
    }

    public void registerAction(ActionEvent event) throws IOException {
        changeView("CustomerRegisterPanel.fxml", event);
    }

    public void clickReturnButton(ActionEvent event) throws IOException {
        changeView("StartPanel.fxml", event);
    }
}
