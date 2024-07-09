package org.clevertec.checkcrud.model;


public class Product {
    private int id;
    private String description;
    private double price;
    private int quantity;
    private boolean wholesale;

    public Product() {
    }

    public Product(String description, double price, int quantity, boolean wholesale) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.wholesale = wholesale;
    }

    public Product(int id, String description, double price, int quantity, boolean wholesale) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.wholesale = wholesale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isWholesale() {
        return wholesale;
    }

    public void setWholesale(boolean wholesale) {
        this.wholesale = wholesale;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", wholesale=" + wholesale +
                '}';
    }
}
