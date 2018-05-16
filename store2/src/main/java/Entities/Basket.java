
package Entities;

import Models.SinglePart;
import Models.SinglePartCollection;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private int id;

    @Column(name = "total_value")
    private int totalValue = 0;

    @Column(name = "total_items")
    private int totalItems = 0;


    @ManyToMany(targetEntity = Product.class, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)

    @JoinTable(name = "basket_product",
            joinColumns = {@JoinColumn(name = "basket_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> productList;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Date time;

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getCustomerName(){
        return customer.getName();
    }

    public void updateState() {
        SinglePartCollection collection = new SinglePartCollection();
        List<SinglePart> singleParts = collection.makeSinglePartList(productList);
        totalValue = 0;
        totalItems = 0;
        for (SinglePart part : singleParts) {
            totalItems = totalItems + part.getQuantityInBasket();
            totalValue = totalValue + part.getTotalValue();
        }
    }

    public void printList(){
        for(Product product: this.productList){
            System.out.println(product.getName());
        }
    }

}





