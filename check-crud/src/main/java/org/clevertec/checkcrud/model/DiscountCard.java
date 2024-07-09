package org.clevertec.checkcrud.model;

public class DiscountCard {
    private int id;
    private int number;
    private int discount;

    public DiscountCard() {
    }

    public DiscountCard(int id, int number, int discount) {
        this.id = id;
        this.number = number;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", number=" + number +
                ", discount=" + discount +
                '}';
    }
}