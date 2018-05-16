package Entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "name"})})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer product_id;


    private String name;

    private Integer price;

    private String category;

    public Product(String name, Integer price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @OneToOne(mappedBy = "product",fetch = FetchType.LAZY)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Storage storage;


    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Product() {
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    private Blob photo;


}
