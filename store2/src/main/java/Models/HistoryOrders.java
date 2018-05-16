package Models;

import Entities.Basket;
import Entities.Customer;
import Util.ConnectionHibernate;
import javafx.collections.FXCollections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HistoryOrders extends ConnectionHibernate {
    public List<Basket> searchOrders(Customer customer) {

        List<Basket> basketList = FXCollections.observableArrayList();
        init();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("id", customer.getId()));
        Customer customerFromQuery  = (Customer) criteria.uniqueResult();


        Set<Basket> basketSetAfterQuery = new HashSet<>();
        basketSetAfterQuery=customerFromQuery.getBaskets();

        for(Basket basket: basketSetAfterQuery){
            basketList.add(basket);
        }
        transaction.commit();
        return basketList;
}
}
