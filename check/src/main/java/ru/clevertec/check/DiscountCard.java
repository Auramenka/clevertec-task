package main.java.ru.clevertec.check;

public class DiscountCard {
    private int number;
    private int discount;

    public int getNumber() {
        return number;
    }

    public int getDiscount() {
        return discount;
    }

    public static class Builder {
        private DiscountCard newDiscountCard;

        public Builder() {
            newDiscountCard = new DiscountCard();
        }

        public Builder withNumber(int number) {
            newDiscountCard.number = number;
            return this;
        }

        public Builder withDiscount(int discount) {
            newDiscountCard.discount = discount;
            return this;
        }

        public DiscountCard build() {
            return newDiscountCard;
        }
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "number=" + number +
                ", discount=" + discount +
                '}';
    }
}
