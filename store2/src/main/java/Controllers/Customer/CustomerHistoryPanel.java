package Controllers.Customer;

import Controllers.Controller;
import Entities.Basket;
import Models.HistoryOrders;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CustomerHistoryPanel extends Controller {
    @FXML
    private TableColumn<Basket, Integer> itemsColumn;

    @FXML
    private TableColumn<Basket, Integer> valueColumn;

    @FXML
    private Button detailsButton;

    @FXML
    private TableView<Basket> productTable;

    @FXML
    private TableColumn<Basket, Date> dateColumn;

    @FXML
    private Button returnButton;

    @FXML
    void clickDetailButton(ActionEvent event) throws IOException {

        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CustomerHistoryDetails.fxml"));
        Parent root = loader.load();
        CustomerHistoryDetailPanel controller = loader.getController();

        controller.setBasket(productTable.getSelectionModel().getSelectedItem());
        productTable.getSelectionModel().getSelectedItem().printList();
        controller.prepareView();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void clickReturnButton(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    public void initialize() {
        itemsColumn.setCellValueFactory(new PropertyValueFactory<>("totalItems"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    public void prepareView() {
        System.out.println("addValues:");
        HistoryOrders orders = new HistoryOrders();
        List<Basket> basketList = orders.searchOrders(customer);
        System.out.println(basketList.size());
        productTable.setItems((ObservableList<Basket>) basketList);

    }

}

