package Controllers.Admin;


import Controllers.Controller;
import Controllers.Customer.CustomerBasketPanel;
import Entities.Basket;
import Entities.Product;
import Entities.Storage;
import Models.FromDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class AdminMainPanel extends Controller {

    @FXML
    private Button showDetailsBasketButton;

    @FXML
    private TableColumn<Storage, Integer> availableColumn;

    @FXML
    private TableColumn<Basket, String> customerColumn;

    @FXML
    private TableView<Storage> productTable;

    @FXML
    private CheckBox availableCheckBox;

    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private TableColumn<Basket, Integer> idOrderColumn;

    @FXML
    private TableColumn<Storage, Integer> soldColumn;

    @FXML
    private TableColumn<Storage, Integer> itemsColumn;

    @FXML
    private TableView<Basket> baskteTable;

    @FXML
    private TableColumn<Storage, String> nameColumn;

    @FXML
    private Button logOutButton;

    @FXML
    private TableColumn<Basket, Integer> valueColumn;

    @FXML
    private Button addProductButton;

    @FXML
    private TableColumn<Basket, Date> dateColumn;

    @FXML
    private TableColumn<Storage, Integer> priceColumn;

    @FXML
    private TableColumn<Storage, Integer> inBasketColumn;

    @FXML
    void clickAddProductButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("AdminAddProduct.fxml"));
        Parent root = loader.load();


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void clickDetailsBasketButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("AdminBasketDetail.fxml"));
        Parent root = loader.load();

        AdminBasketDetailPanel controller = loader.getController();
        controller.prepareView(baskteTable.getSelectionModel().getSelectedItem());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void clickRefreshButton(ActionEvent event) {
        getValuesFromDB(0);
    }


    @FXML
    void clickAvailableCheckBox(ActionEvent event) {
        FromDB fromDB = new FromDB();
        if (availableCheckBox.isSelected()) getValuesFromDB(1);
        else productTable.setItems((ObservableList<Storage>) fromDB.getStoragesFromDB(0));
    }

    @FXML
    void clickLogOutButton(ActionEvent event) throws IOException {
        changeView("StartPanel.fxml", event);
    }

    @FXML
    void initialize() {

        getValuesFromDB(0);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
        inBasketColumn.setCellValueFactory(new PropertyValueFactory<>("inBasket"));
        soldColumn.setCellValueFactory(new PropertyValueFactory<>("sold"));

        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        itemsColumn.setCellValueFactory(new PropertyValueFactory<>("totalItems"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    private void getValuesFromDB(Integer function) {
        FromDB fromDB = new FromDB();
        productTable.setItems((ObservableList<Storage>) fromDB.getStoragesFromDB(function));
        baskteTable.setItems((ObservableList<Basket>) fromDB.getBasketsFromDB());
    }

}
