package Models;

import Entities.Product;

public class SinglePart {

    private Product product;

    private String name;
    private Integer priceForOne;
    private Integer quantityInBasket;
    private Integer totalValue;




    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriceForOne() {
        return priceForOne;
    }

    public void setPriceForOne(Integer priceForOne) {
        this.priceForOne = priceForOne;
    }

    public Integer getQuantityInBasket() {
        return quantityInBasket;
    }

    public void setQuantityInBasket(Integer quantityInBasket) {
        this.quantityInBasket = quantityInBasket;
    }

    public Integer getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Integer totalValue) {
        this.totalValue = totalValue;
    }


}
