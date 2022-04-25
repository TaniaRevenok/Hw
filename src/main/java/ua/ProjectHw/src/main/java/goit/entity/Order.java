package goit.entity;


import java.time.LocalDateTime;

public class Order {
    private long id;
    private long petId;
    private int quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    public Order() {
        this.id = 1;
        this.petId = 1;
        this.quantity = 1;
        this.shipDate = LocalDateTime.now().toString();
        this.status = "Delivered";
        this.complete = false;
    }

    public Order(int quantity, String shipDate, String status, Boolean complete) {
        this.id = 1;
        this.petId = 1;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", shipDate='" + shipDate + '\'' +
                ", status='" + status + '\'' +
                ", complete=" + complete +
                '}';
    }
}

