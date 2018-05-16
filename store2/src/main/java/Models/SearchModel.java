package Models;

import Controllers.Controller;
import Entities.Product;
import Util.ConnectionHibernate;
import javafx.collections.FXCollections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SearchModel extends ConnectionHibernate {


    public List<Product> searchByPrice(String maxPriceInput, String minPriceInput){

        init();
        Integer maxPrice=null;
        Integer minPrice=null;

        try {
            minPrice = Integer.parseInt(minPriceInput);

        } catch (Exception e) {
        }

        try {
            maxPrice = Integer.parseInt(maxPriceInput);

        } catch (Exception e) {
        }

        List<Product> productList=FXCollections.observableArrayList();
        Criteria criteria = session.createCriteria(Product.class);

        if (minPrice != null) {
            if (maxPrice != null) {

                criteria.add(Restrictions.or(Restrictions.eq("price", minPrice),
                        Restrictions.eq("price",maxPrice),Restrictions.and(
                                Restrictions.gt("price",minPrice),
                                Restrictions.lt("price",maxPrice)
                        )));

            } else
                criteria.add(Restrictions.or(Restrictions.eq("price",minPrice),
                        Restrictions.gt("price",minPrice)));
        } else {
            if (maxPrice != null) criteria.add(Restrictions.or(Restrictions.eq("price",maxPrice),
                    Restrictions.lt("price",maxPrice)));
        }

        List<Product> productListAfterSearchQuery = criteria.list();
        transaction.commit();


        for (Product product : productListAfterSearchQuery) {
            productList.add(product);
        }

        return productList;
    }



}
