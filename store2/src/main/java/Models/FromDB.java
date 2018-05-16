package Models;

import Entities.Basket;
import Entities.Customer;
import Entities.Storage;
import Util.ConnectionHibernate;
import javafx.collections.FXCollections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FromDB extends ConnectionHibernate {
    public List<Storage> getStoragesFromDB(Integer function) {
        init();
        Criteria criteria = session.createCriteria(Storage.class);
        if(function==1) criteria.add(Restrictions.gt("availability",0));

        List<Storage> storageList = criteria.list();
        transaction.commit();

        List<Storage> outputList= FXCollections.observableArrayList();
        for(Storage storage: storageList){
            outputList.add(storage);
        }

        return outputList;

    }

    public List<Basket> getBasketsFromDB(){

        List<Basket> basketList = FXCollections.observableArrayList();
        Set<Basket> basketSetAfterQuery = new HashSet<>();
        Set<Basket> allBasketSetAfterQuery = new HashSet<>();
        init();
        Criteria criteria = session.createCriteria(Customer.class);

        List<Customer> customerList = criteria.list();
        for(Customer customer : customerList){
            basketSetAfterQuery=customer.getBaskets();
            allBasketSetAfterQuery.addAll(basketSetAfterQuery);
        }

        for(Basket basket: allBasketSetAfterQuery){
            basketList.add(basket);
        }
        transaction.commit();
        return basketList;


    }

}
