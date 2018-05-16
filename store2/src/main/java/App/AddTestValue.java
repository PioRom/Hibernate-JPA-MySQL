package App;

import Entities.Basket;
import Entities.Customer;
import Entities.Product;
import Entities.Storage;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AddTestValue {
    public static void main(String[] args) throws  SQLException, IOException {

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        Product product = new Product("nokija",100,"phone");
        Storage storage = new Storage();
        storage.setProduct(product);
        storage.setAvailability(1000);
        storage.setInBasket(0);
        storage.setSold(0);
        Customer customer = new Customer("Customer1","Warsaw","123123123","12345678","customer1@gmail.com");
        Basket basket = new Basket();
        basket.setCustomer(customer);
        basket.setDate(new Date());
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        basket.setProductList(productList);


        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            System.out.println("Session created");
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            session.save(product);
           // session.save(customer);
            session.save(storage);
           // session.save(basket);

            tx.commit();


        } catch (Exception e) {
            System.out.println("Exception occured. " + e.getMessage());
        } finally {
            if (!sessionFactory.isClosed()) {
                System.out.println("Closing SessionFactory");
                sessionFactory.close();
            }
        }
    }

}