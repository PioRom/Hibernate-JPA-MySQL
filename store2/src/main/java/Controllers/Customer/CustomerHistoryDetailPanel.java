package Controllers.Customer;

import Controllers.Controller;
import Entities.Product;
import Models.SinglePart;
import Models.SinglePartCollection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CustomerHistoryDetailPanel extends Controller{
    @FXML
    private TableColumn<SinglePart, String> quantityColumn;

    @FXML
    private Text sumText;

    @FXML
    private TableColumn<SinglePart, String> nameColumn;

    @FXML
    private Text itemsText;

    @FXML
    private TableView<SinglePart> productTable;

    @FXML
    private TableColumn<SinglePart, Integer> priceForOneColumn;

    @FXML
    private TableColumn<SinglePart, Integer> priceColumn;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityInBasket"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        priceForOneColumn.setCellValueFactory(new PropertyValueFactory<>("priceForOne"));

    }

    //list from input basket
    List<Product> productList;

    //list display in table
    List<SinglePart> mainSingleOrderList;


    public void prepareView() {
        SinglePartCollection collect = new SinglePartCollection();
        productList = basket.getProductList();
        basket.printList();
        mainSingleOrderList = collect.makeSinglePartList(productList);
        productTable.setItems((ObservableList<SinglePart>) mainSingleOrderList);
        itemsText.setText("Items: " + basket.getTotalItems());
        sumText.setText("Sum: " + basket.getTotalValue());
    }


    public void clickCloseButton(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
}

