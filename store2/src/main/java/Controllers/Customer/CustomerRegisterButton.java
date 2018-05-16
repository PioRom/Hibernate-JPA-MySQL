package Controllers.Customer;

import Controllers.Controller;
import Entities.Customer;
import Models.CustomerRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class CustomerRegisterButton extends Controller {

    @FXML
    private Text message_1;

    @FXML
    private Text message_2;


    @FXML
    private Button RegisterButton;

    @FXML
    private PasswordField inputPasswordAgain;

    @FXML
    private TextField inputEMail;

    @FXML
    private TextField inputUsername;

    @FXML
    private TextField inputAddress;

    @FXML
    private TextField inputPhone;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private Button returnButton;

    @FXML
    void initialize() {
    }

    public void clickRegisterButton(ActionEvent event) throws IOException {

        String password = inputPassword.getText();
        String secondPassword = inputPasswordAgain.getText();
        String phoneNumber = inputPhone.getText();
        String email = inputEMail.getText();
        String username = inputUsername.getText();
        String address = inputAddress.getText();

        CustomerRegister register = new CustomerRegister();

        Integer counter = 0;

        if (register.checkEmailInDB(email)) {
            counter++;
            message_1.setText("This email is used");
        }
        if (register.checkPhoneNumberInDB(phoneNumber)) {
            counter++;
            message_1.setText("This phone number is used");
        }
        if (register.checkUsernameInDB(username)) {
            counter++;
            message_1.setText("This username is used");
        }
        if (!register.checkPhoneNumber(phoneNumber)) {
            counter++;
            message_1.setText("Phone Number is incorrect");
        }

        if (!register.checkEmail(email)) {
            counter++;
            message_1.setText("Email address is incorrect");
        }

        if (!register.checkAddress(address)) {
            counter++;
            message_1.setText("Enter the address");
        }

        if (!register.checkPasswords(password, secondPassword)) {
            counter++;
            message_1.setText("Passwords do not match");
        }

        if (!register.checkPassword(password)) {
            counter++;
            message_1.setText("The password must be at least 8 characters long");
        }


        if (!register.checkUsername(username)) {
            counter++;
            message_1.setText("Enter the username");
        }

        if (counter == 0) {
            Customer customer = register.addToDB(username, password, email, phoneNumber);
            init();
            session.save(customer);
            transaction.commit();
            changeView("CustomerLoginPanel.fxml", event);
        }

    }

    public void clickReturnButton(ActionEvent event) throws IOException {
        changeView("CustomerLoginPanel.fxml", event);
    }
}
