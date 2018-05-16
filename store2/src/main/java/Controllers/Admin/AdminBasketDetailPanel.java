package Controllers.Admin;


import Controllers.Customer.CustomerBasketPanel;
import Entities.Basket;
import Models.SinglePart;
import Models.SinglePartCollection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminBasketDetailPanel extends CustomerBasketPanel{

    @FXML
    private Text sumText;

    @FXML
    private Text itemsText;

    @FXML
    private Text addressText;

    @FXML
    private TableView<SinglePart> productTable;

    @FXML
    private Text emailText;

    @FXML
    private Text customerNameText;

    @FXML
    private Text phoneText;

    @FXML
    private TableColumn<SinglePart, ?> quantityColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<?, ?> priceForOneColumn;

    @FXML
    private Text ordeIDText;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    void clickCloseButton(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();

    }

    public void prepareView(Basket basket){
        SinglePartCollection collect = new SinglePartCollection();
        productTable.setItems((ObservableList<SinglePart>) collect.makeSinglePartList(basket.getProductList()));

        sumText.setText("Sum: "+ basket.getTotalValue());
        itemsText.setText("Items: "+basket.getTotalItems());
        customerNameText.setText("Customer: "+basket.getCustomer().getName());
        emailText.setText("Email: "+basket.getCustomer().getEmail());
        phoneText.setText("Phone: "+ basket.getCustomer().getPhone());
        ordeIDText.setText("Order ID: "+basket.getId());
        addressText.setText("Address: "+ basket.getCustomer().getAddress());
    }


}
