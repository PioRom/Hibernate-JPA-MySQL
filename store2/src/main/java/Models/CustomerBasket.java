package Models;

import Entities.Basket;
import Entities.Customer;
import Entities.Product;

import java.util.ArrayList;
import java.util.List;

public class CustomerBasket {
    public static Basket generateNewBasket(Customer customer) {
        Basket basket = new Basket();
        List<Product> productList = new ArrayList<>();
        basket.setCustomer(customer);
        basket.setProductList(productList);
        return basket;
    }
}
