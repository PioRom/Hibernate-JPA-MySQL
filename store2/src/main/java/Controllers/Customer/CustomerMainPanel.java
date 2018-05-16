package Controllers.Customer;

import Controllers.Controller;
import Entities.Customer;
import Entities.Product;
import Entities.Storage;
import Models.CustomerBasket;
import Models.ProductTransaction;
import Models.SearchModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerMainPanel extends Controller {

    @FXML
    private TableColumn<Product, String> descriptionColumn;

    @FXML
    private Text loginMessage;

    @FXML
    private Button ordersButton;

    @FXML
    private TextField maxInputPrice;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private Button searchByPriceButton;

    @FXML
    private Button basketButton;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Button buyButton;

    @FXML
    private TextField minPriceInput;

    @FXML
    private TableColumn<Product, Integer> priceColumn;

    @FXML
    private Button signOutButton;

    @FXML
    void clickBuyButton(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CustomerBuyProduct.fxml"));
        Parent root = loader.load();

        CustomerBuyProduct controller = loader.getController();
        controller.setBasket(basket);
        controller.setProduct(productTable.getSelectionModel().getSelectedItem());
        controller.prepareView();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void clickSingOutButton(ActionEvent event) throws IOException {
        changeView("CustomerLoginPanel.fxml", event);
    }

    @FXML
    void clickOrdersButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CustomerHistory.fxml"));
        Parent root = loader.load();

        CustomerHistoryPanel controller = loader.getController();
        controller.setCustomer(customer);
        controller.prepareView();

        Stage window = new Stage();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    void clickBasketButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CustomerBasketPanel.fxml"));
        Parent root = loader.load();

        CustomerBasketPanel controller = loader.getController();
        controller.setBasket(basket);
        controller.prepareView();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();


    }

    @FXML
    void clickSearchByPriceButton(ActionEvent event) {
        searchByPrice();
    }

    @FXML
    void minPriceInputField(ActionEvent event) {
        searchByPrice();
    }

    @FXML
    void fieldMaxInputPrice(ActionEvent event) {
        searchByPrice();
    }

    private void searchByPrice() {

        String minPrice = minPriceInput.getText();
        String maxPrice = maxInputPrice.getText();

        SearchModel searchModel = new SearchModel();

        productTable.setItems((ObservableList<Product>) searchModel.searchByPrice(maxPrice, minPrice));

    }

    @FXML
    void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    }

    public void prepareView() {
        setCustomer(basket.getCustomer());
        searchByPrice();
    }

    public void firstView() {
        loginMessage.setText(customer.getName());
        basket = CustomerBasket.generateNewBasket(customer);
        searchByPrice();
    }

}
