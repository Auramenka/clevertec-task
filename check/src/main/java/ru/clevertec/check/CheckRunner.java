package main.java.ru.clevertec.check;

import java.util.List;

public class CheckRunner {

    public static void main(String[] args) {
        final String PRODUCTS_FILE_NAME = "./src/resources/products.csv";
        final String DISCOUNT_CARD_FILE_NAME = "./src/resources/discountCards.csv";

        ProductList productList = new ProductList(PRODUCTS_FILE_NAME);
        List<Product> products = productList.print();
        DiscountCardList discountCardList = new DiscountCardList(DISCOUNT_CARD_FILE_NAME);
        List<DiscountCard> discountCards = discountCardList.print();

        new Check(products, discountCards, args);
    }
}
