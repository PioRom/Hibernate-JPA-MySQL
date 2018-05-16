package Controllers.Customer;

import Controllers.Controller;
import Models.ProductTransaction;
import Util.ConnectionHibernate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerFinishPurchasePanel extends Controller{

    @FXML
    private Button backButton;

    @FXML
    private Text finalText;

    @FXML
    void clickBackButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CustomerMainPanel.fxml"));
        Parent root = loader.load();

        CustomerMainPanel controller = loader.getController();
        controller.setCustomer(basket.getCustomer());
        controller.firstView();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();

    }
    public void prepareView(){
        finalText.setText("You have bought "+basket.getTotalItems()+" items.");

    }


}

