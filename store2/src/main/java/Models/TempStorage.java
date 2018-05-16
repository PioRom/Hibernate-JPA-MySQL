package Models;

public class TempStorage {
    private Integer id;

    private Integer availability;

    private Integer inBasket;

    private Integer sold;

    public TempStorage(Integer id, Integer availability, Integer inBasket, Integer sold) {
        this.id = id;
        this.availability = availability;
        this.inBasket = inBasket;
        this.sold = sold;
    }

    public TempStorage() {

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

    public void setInBasket(Integer inBasket) {
        this.inBasket = inBasket;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }
}
