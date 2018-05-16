package Models;

import App.MyApp;
import Entities.Basket;
import Entities.Customer;
import Entities.Product;
import Entities.Storage;
import Util.ConnectionHibernate;
import javafx.collections.FXCollections;
import jdk.nashorn.internal.runtime.StoredScript;
import org.codehaus.plexus.util.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;


public class ProductTransaction extends ConnectionHibernate {

    public Basket addToBasket(Basket basket, Product product, Integer orderNumber) {
        List<Product> productList = basket.getProductList();

        Storage storage = getProductInfo(product);
        Integer availability = storage.getAvailability();

        Integer i = 0;
        while (i < orderNumber) {
            productList.add(product);
            i++;
        }

        basket.setProductList(productList);
        basket.updateState();

        storage.setAvailability(availability - orderNumber);
        storage.setInBasket(storage.getInBasket() + orderNumber);
        saveProductStatus(storage);

        return basket;
    }

    public void saveProductStatus(Storage storage) {
        init();
        session.merge(storage);
        session.evict(storage);
        transaction.commit();

    }


    public Storage getProductInfo(Product product) {

        Integer id = product.getProduct_id();

        init();
        Criteria criteria = session.createCriteria(Storage.class).add(Restrictions.eq("id", id));
        Storage storage = (Storage) criteria.uniqueResult();
        transaction.commit();

        return storage;
    }

    public Integer checkAvailability(Product product) {
        Storage storage = getProductInfo(product);
        Integer availability = storage.getAvailability();
        return availability;

    }

    public void finishPurchase(Basket basket, List<SinglePart> singleParts) {
        basket.setTime(new Date());
        basket.setDate(new Date());

        List<TempStorage> tempStorageList = prepareCopyOfStorage(singleParts);
        updateBasket(basket);
        updateStorages(singleParts, tempStorageList);
    }

    public List<TempStorage> prepareCopyOfStorage(List<SinglePart> singleParts) {

        List<TempStorage> tempStorageList = new ArrayList<>();
        for (SinglePart singlePart : singleParts) {

            Integer orders = singlePart.getQuantityInBasket();
            Storage storage = getProductInfo(singlePart.getProduct());

            TempStorage tempStorage = new TempStorage();
            tempStorage.setId(storage.getId());
            tempStorage.setInBasket(storage.getInBasket() - orders);
            tempStorage.setAvailability(storage.getAvailability());
            tempStorage.setSold(storage.getSold() + orders);

            tempStorageList.add(tempStorage);
        }

        return tempStorageList;
    }

    public boolean checkOrderNumber(Integer orderNumber) {
        if (orderNumber > 0) return true;
        else return false;
    }

    public void updateBasket(Basket basket) {
        Session basketSession = MyApp.sessionFactory.getCurrentSession();
        transaction = basketSession.beginTransaction();
        basketSession.save(basket);
        transaction.commit();
    }

    public void updateStorages(List<SinglePart> singleParts, List<TempStorage> tempStorageList) {
        for (SinglePart singlePart : singleParts) {
            Storage storage = getProductInfo(singlePart.getProduct());
            for (TempStorage tempStorage : tempStorageList) {
                if (tempStorage.getId() == storage.getId()) {
                    storage.setAvailability(tempStorage.getAvailability());
                    storage.setInBasket(tempStorage.getInBasket());
                    storage.setSold(tempStorage.getSold());
                }
                init();
                session.update(storage);
                transaction.commit();
            }
        }
    }
}

