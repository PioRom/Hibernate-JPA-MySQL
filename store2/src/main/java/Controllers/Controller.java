package Controllers;

import App.MyApp;
import Entities.Basket;
import Entities.Customer;
import Entities.Product;
import Util.ConnectionHibernate;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class Controller extends ConnectionHibernate {

    protected Customer customer;
    protected Product product;
    protected Basket basket;

    protected void changeView(String panelfxml, ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource(panelfxml));
        Scene scene = new Scene(parent);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Basket getBasket() { return basket; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product;

    }
}
