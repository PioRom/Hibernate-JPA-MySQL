package Controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class StartController extends  Controller {
    public void clickCustomerButton(ActionEvent event) throws IOException {
        changeView("CustomerLoginPanel.fxml",event);
    }

    public void clickAdminButton(ActionEvent event) throws IOException {

        changeView("AdminLogin.fxml", event);
    }
}
