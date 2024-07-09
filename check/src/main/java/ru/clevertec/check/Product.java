package main.java.ru.clevertec.check;


public class Product {
    private int id;
    private String description;
    private double price;
    private int quantity;
    private boolean isWholesale;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isWholesale() {
        return isWholesale;
    }

    public static class Builder {
        private Product newProduct;

        public Builder() {
            newProduct = new Product();
        }

        public Builder withId (int id) {
            newProduct.id = id;
            return this;
        }

        public Builder withDescription (String description) {
            newProduct.description = description;
            return this;
        }

        public Builder withPrice (double price) {
            newProduct.price = price;
            return this;
        }

        public Builder withQuantity (int quantity) {
            newProduct.quantity = quantity;
            return this;
        }

        public Builder withIsWholesale (boolean isWholesale) {
            newProduct.isWholesale = isWholesale;
            return this;
        }

        public Product build() {
            return newProduct;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", isWholesale=" + isWholesale +
                '}';
    }
}
