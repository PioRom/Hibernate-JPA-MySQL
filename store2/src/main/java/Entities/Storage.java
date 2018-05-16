package Entities;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @Column(name="id",unique = true, nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name="gen", strategy = "foreign",
            parameters = {@org.hibernate.annotations.Parameter(name="property", value="product")})
    private Integer id;

    private Integer availability;

    private Integer inBasket;

    private Integer sold;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Product product;

    public Storage(Integer availability, Integer inBasket, Integer sold, Product product) {
        this.availability = availability;
        this.inBasket = inBasket;
        this.sold = sold;
        this.product = product;
    }

    public Storage() {}

    public String getProductName() {
        return product.getName();
    }
    public Integer getProductPrice(){
        return product.getPrice();
    }

    public String getProductCategory(){
        return product.getCategory();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Integer getInBasket() {
        return inBasket;
    }

    public void setInBasket(Integer inbasket) {
        this.inBasket = inbasket;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public void print() {
        System.out.println("**************");
        System.out.println(getProduct().getName());
        System.out.println(getAvailability());
        System.out.println(getInBasket());
        System.out.println(getSold());
        System.out.println("***********");
    }
}
