package Controllers.Customer;

import Controllers.Controller;
import Entities.Product;
import Entities.Storage;
import Models.CustomerBasket;
import Models.ProductTransaction;
import Models.SinglePart;
import Models.SinglePartCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CustomerEditBasket extends Controller {

    @FXML
    private Text quantityAvailableText;

    @FXML
    private Text messageText;

    @FXML
    private Button closeButton;

    @FXML
    private TextField inputValue;

    @FXML
    private ImageView imageView;

    @FXML
    private Button okButton;

    @FXML
    private Text priceForAllText;

    @FXML
    private Text productText;

    @FXML
    private Text inBasketText;

    @FXML
    private Text priceForOneText;


    @FXML
    void clickCloseButton(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    private SinglePart singlePart;

    public void setSinglePart(SinglePart singlePart) {
        this.singlePart = singlePart;
    }

    public void prepareView() {
        product = singlePart.getProduct();
        refreshInterface();
    }


    private Storage storage;

    private void refreshInterface() {
        ProductTransaction productTransaction = new ProductTransaction();

        storage = productTransaction.getProductInfo(product);

        quantityAvailableText.setText("Available: " + storage.getAvailability().toString());
        productText.setText(singlePart.getProduct().getName());
        inBasketText.setText("In basket: " + singlePart.getQuantityInBasket().toString());
        priceForOneText.setText("Price for one: " + singlePart.getProduct().getPrice().toString());
        priceForAllText.setText("Price for all: " + singlePart.getTotalValue().toString());
    }


    public void clickChangeButton(ActionEvent event) throws IOException {
        ProductTransaction productTransaction = new ProductTransaction();
        SinglePartCollection collection = new SinglePartCollection();

        Integer inBasket = singlePart.getQuantityInBasket();
        Integer orderNumber = Integer.parseInt(inputValue.getText());
        Integer availability = productTransaction.checkAvailability(product);

        if (availability >= orderNumber - inBasket) {
            singlePart.setQuantityInBasket(orderNumber);

            singlePartList = collection.updateSinglePartList(singlePartList, singlePart);

            basket.setProductList(collection.getProductList(singlePartList));
            basket.updateState();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("CustomerBasketPanel.fxml"));
            Parent root = loader.load();
            CustomerBasketPanel controller = loader.getController();
            controller.passSinglePart(singlePartList);
            controller.setBasket(basket);

            storage.setAvailability(storage.getAvailability()+storage.getInBasket()-orderNumber);
            storage.setInBasket(orderNumber);
            productTransaction.saveProductStatus(storage);

            refreshInterface();
        }
    }

    private List<SinglePart> singlePartList;

    public void setSinglePartList(List<SinglePart> singlePartList) {
        this.singlePartList = singlePartList;
    }

}
