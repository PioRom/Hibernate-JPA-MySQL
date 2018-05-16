package Controllers.Customer;

import Controllers.Controller;
import Entities.Basket;
import Entities.Storage;
import Models.DisplayImage;
import Models.ProductTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerBuyProduct extends Controller {

    @FXML
    private Text quantityAvailableText;

    @FXML
    private Text messageText;

    @FXML
    private Text descriptionTexr;

    @FXML
    private ImageView imageView;

    @FXML
    private Text productText;

    @FXML
    private Button returnButton;

    @FXML
    private Text priceText;

    @FXML
    private Button buyButton;

    @FXML
    private TextField quantityInput;

    @FXML
    void clickBuyButton(ActionEvent event) throws NumberFormatException, IOException, SQLException {
        Integer orderNumber = Integer.parseInt(quantityInput.getText());
        ProductTransaction productTransaction = new ProductTransaction();
        Integer availability = productTransaction.checkAvailability(product);
        if(productTransaction.checkOrderNumber(orderNumber)) {
            if (availability >= orderNumber) {
                basket = productTransaction.addToBasket(basket, product, orderNumber);
                updateInterface();
                messageText.setText("Products has been added to basket");

                passBasket(basket);
            } else messageText.setText("You can only buy: " + availability);
        }else messageText.setText("Enter a positive number");
    }

    public void prepareView() throws IOException, SQLException {
        updateInterface();
    }

    private void passBasket(Basket basket) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CustomerMainPanel.fxml"));
        Parent root = loader.load();

        CustomerMainPanel controller = loader.getController();
        controller.setBasket(basket);

    }

    private void updateInterface() throws IOException, SQLException {
        ProductTransaction productTransaction = new ProductTransaction();
        Storage storage = productTransaction.getProductInfo(product);

        priceText.setText("Price: " + product.getPrice().toString());
        descriptionTexr.setText("Category: "+product.getCategory());
        quantityAvailableText.setText("Available: "+storage.getAvailability().toString());

        DisplayImage displayImage = new DisplayImage();

        if(displayImage.checkImage(product)) {
            Image image = displayImage.display(product);
            imageView.setImage(image);
        }

    }

    public void clickReturnButton(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();

    }
}
