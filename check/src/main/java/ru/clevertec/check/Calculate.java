package main.java.ru.clevertec.check;

public class Calculate {

    public double total(Product product) {
        return product.getPrice() * product.getQuantity();
    }

    public double calculateTotalPrice(double totalPrice, Product product) {
        totalPrice += total(product);
        return totalPrice;
    }

    public double calculateDiscount(Product product, DiscountCard discountCard) {
        double discount;
        if (product.isWholesale() && product.getQuantity() >= 5) {
            discount = ((product.getPrice() * product.getQuantity()) / 100) * 10;
        } else if (product.isWholesale()) {
            discount = ((product.getPrice() * product.getQuantity()) / 100) * discountCard.getDiscount();
        } else {
            discount = product.getPrice() * product.getQuantity();
        }

        return discount;
    }

    public double calculateTotalDiscount(double totalDiscount, double calculateDiscount) {
        totalDiscount += calculateDiscount;
        return totalDiscount;
    }

    public double calculateTotalWithDiscount(double totPrice, Product product,
                                              double totDiscount, double calculateDiscount) {
        double totalPrice = calculateTotalPrice(totPrice, product);
        double totalDiscount = calculateTotalDiscount(totDiscount, calculateDiscount);
        return totalPrice - totalDiscount;
    }
}
