package Models;

import Entities.Product;
import Entities.Storage;
import Util.ConnectionHibernate;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;

public class VerifyNewProduct extends ConnectionHibernate{

    public boolean checkProductNameInDB(String productName){
        init();
        Criteria criteria = session.createCriteria(Product.class).
                add(Restrictions.eq("name",productName));
        Product product = (Product) criteria.uniqueResult();
        transaction.commit();
        if(product!=null) return true;
        else return false;
    }

    public boolean checkPrice(Integer price){
        if(price>0) return true;
        else return false;
    }

    public boolean checkQuantity(Integer quantity) {
        if(quantity>0) return true;
        else return false;
    }

    public void addProductToDB(String name, Integer price, String category, File photoFile, Integer quantity) throws FileNotFoundException {
        Product product = new Product(name,price,category);
        Storage storage = new Storage(quantity,0,0,product);

        init();
        FileInputStream fileInputStream = new FileInputStream(photoFile);
        Blob blob = Hibernate.getLobCreator(session).createBlob(fileInputStream, photoFile.length());
        product.setPhoto(blob);

        session.save(product);
        session.save(storage);
        transaction.commit();
    }

    public boolean updateProductInDB(Integer newAvailableQuantity,Storage storage){

        init();
        Criteria criteria = session.createCriteria(Storage.class)
                .add(Restrictions.eq("id",storage.getId()));
        Storage actualStorage = (Storage) criteria.uniqueResult();
        transaction.commit();

        if(actualStorage.getAvailability()>=newAvailableQuantity){
            actualStorage.setAvailability(newAvailableQuantity);
            init();
            session.update(actualStorage);
            transaction.commit();
            return true;
        }

        else return false;
    }

}
