package Controllers.Customer;

import Controllers.Controller;

import Entities.Basket;
import Entities.Product;
import Entities.Storage;
import Models.ProductTransaction;
import Models.SinglePart;
import Models.SinglePartCollection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CustomerBasketPanel extends Controller{
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
    public void initialize() throws IOException {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityInBasket"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        priceForOneColumn.setCellValueFactory(new PropertyValueFactory<>("priceForOne"));

    }

    //list from input basket
    List<Product> productList;

    //list display in table
    List<SinglePart> singlePartList;


    public void clickEditButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CustomerEditBasket.fxml"));
        Parent root = loader.load();

        CustomerEditBasket controller = loader.getController();
        controller.setBasket(basket);
        controller.setSinglePart(productTable.getSelectionModel().getSelectedItem());
        controller.setSinglePartList(singlePartList);
        controller.prepareView();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickDeleteButton(ActionEvent event) {
        SinglePartCollection collect = new SinglePartCollection();
        collect.delete(singlePartList,productTable.getSelectionModel().getSelectedItem());
        basket.setProductList(collect.getProductList(singlePartList));
        basket.updateState();
        refreshInterface();

    }

    public void clickReturnButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CustomerMainPanel.fxml"));
        Parent root = loader.load();

        CustomerMainPanel controller = loader.getController();
        controller.setBasket(basket);
        controller.prepareView();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();

    }

    public void clickConfirmButton(ActionEvent event) throws IOException {

        ProductTransaction productTransaction = new ProductTransaction();

        productTransaction.finishPurchase(basket,singlePartList);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CustomerFinishPurchase.fxml"));
        Parent root = loader.load();

        CustomerFinishPurchasePanel controller = loader.getController();
        controller.setBasket(basket);
        controller.prepareView();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();


    }

    public void passSinglePart(List<SinglePart> list){
        productTable.setItems((ObservableList<SinglePart>) list);
    }


    public void prepareView() {
        SinglePartCollection collect = new SinglePartCollection();
        productList=basket.getProductList();
        singlePartList = collect.makeSinglePartList(productList);
        productTable.setItems((ObservableList<SinglePart>) singlePartList);
        refreshInterface();

    }

    private void refreshInterface(){
        itemsText.setText("Items: "+ basket.getTotalItems());
        sumText.setText("Sum: "+basket.getTotalValue());
    }

    public void clickRefreshButton(ActionEvent event) {
        refreshInterface();

        System.out.println("clickRefreshButton");
        System.out.println(basket.getCustomer().getName());
        System.out.println(basket.getProductList().size());
    }
}
