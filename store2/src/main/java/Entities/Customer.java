package Entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Customer",uniqueConstraints ={@UniqueConstraint(columnNames = {"customer_id","name","phone","email"})} )

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Integer id;

    private String name;

    private String address;

    private String phone;

    private String password;

    private String email;

    public Customer(String name, String address, String phone, String password, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.email = email;
    }

    @OneToMany(mappedBy = "customer")
    private Set<Basket> baskets;


    public Set<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(Set<Basket> baskets) {
        this.baskets = baskets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Customer() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
