package main.java.ru.clevertec.check;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductList implements IParser<Product> {

    private List<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }

    public ProductList(final String file) {
        this();
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Product product = parse(line);
                products.add(product);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product parse(String line) {
        final String REGEX = ";";
        final int PRODUCT_CLASS = 5;
        String[] values = line.split(REGEX);
        Product product = null;

        int id = Integer.parseInt(values[0]);
        String description = values[1];
        double price = Double.parseDouble(values[2]);
        int quantity = Integer.parseInt(values[3]);
        boolean isWholesale = Boolean.parseBoolean(values[4]);

        if (values.length == PRODUCT_CLASS) {
            product = new Product.Builder()
                    .withId(id).withDescription(description)
                    .withPrice(price).withQuantity(quantity).withIsWholesale(isWholesale).build();
        }
        return product;
    }

    public List<Product> print() {
        return products;
    }
}
