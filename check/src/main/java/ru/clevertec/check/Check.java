package main.java.ru.clevertec.check;

import java.util.ArrayList;
import java.util.List;

public class Check {

    private final List<Product> productList = new ArrayList<>();
    private final List<DiscountCard> cardList = new ArrayList<>();
    private final CheckGenerator checkGenerator = new CheckGenerator();
    private final CsvGenerator csvGenerator = new CsvGenerator();
    private final Calculate calculate = new Calculate();

    public Check(List<Product> products, List<DiscountCard> discountCardList, String[] args) {
        Product pr;
        DiscountCard dc;
        for (String arg : args) {
            String[] information = arg.split("=");
            if (information[0].equals("discountCard")) {
                int number = Integer.parseInt(information[1]);
                for (DiscountCard discountCard : discountCardList) {
                    if (discountCard.getNumber() == number) {
                        dc = new DiscountCard.Builder()
                                .withNumber(number).withDiscount(discountCard.getDiscount()).build();
                        cardList.add(dc);
                    }
                }
                csvGenerator.printCheckInFile(printCheckWithDiscountCard(args));
            } else {
                String[] inf = arg.split("-");
                int id = Integer.parseInt(inf[0]);
                int quantity = Integer.parseInt(inf[1]);
                for (Product product : products) {
                    if (product.getId() == id) {
                        pr = new Product.Builder().withId(id).withDescription(product.getDescription())
                                .withPrice(product.getPrice()).withQuantity(quantity)
                                .withIsWholesale(product.isWholesale()).build();
                        productList.add(pr);
                    }
                }
                csvGenerator.printCheckInFile(printCheckWithoutDiscountCard());
            }
        }
        productList.forEach(System.out::println);
        cardList.forEach(System.out::println);
    }

    public String printCheckWithoutDiscountCard() {
        double totalPrice = 0.0;
        double totalDiscount = 0.0;
        double totalWithDiscount = 0.0;
        StringBuilder stringBuilder = new StringBuilder();
        checkGenerator.addHeader(stringBuilder);
        for (Product product : productList) {
            double calculateDiscount = calculate.calculateDiscount(product, new DiscountCard());
            stringBuilder.append(product.getQuantity() + ";" + product.getDescription() + ";"
                    + product.getPrice() + "$;" + String.format("%.2f", calculateDiscount) + "$;" +
                    calculate.total(product) + "$");

            totalPrice = calculate.calculateTotalPrice(totalPrice, product);
            totalDiscount = calculate.calculateTotalDiscount(totalDiscount, calculateDiscount);
            totalWithDiscount = calculate.calculateTotalWithDiscount(totalPrice, product, totalDiscount, calculateDiscount);
            stringBuilder.append("\n");
        }
        checkGenerator.addFooter(stringBuilder, totalPrice, totalDiscount, totalWithDiscount);

        return stringBuilder.toString();
    }

    public String printCheckWithDiscountCard(String[] args) {
        int number;
        int discount = 0;
        double totalPrice = 0.0;
        double totalDiscount = 0.0;
        double totalWithDiscount = 0.0;
        StringBuilder stringBuilder = new StringBuilder();
        checkGenerator.addHeader(stringBuilder);
        for (String arg : args) {
            String[] information = arg.split("=");
            if (information[0].equals("discountCard")) {
                number = Integer.parseInt(information[1]);
                for (DiscountCard discountCard : cardList) {
                    if (discountCard.getNumber() == number) {
                        for (Product product : productList) {
                            double calculateDiscount = calculate.calculateDiscount(product, discountCard);
                            stringBuilder.append(product.getQuantity() + ";" + product.getDescription() + ";"
                                    + product.getPrice() + "$;" + String.format("%.2f", calculateDiscount) + "$;" +
                                    calculate.total(product) + "$");
                            totalPrice = calculate.calculateTotalPrice(totalPrice, product);
                            totalDiscount = calculate.calculateTotalDiscount(totalDiscount, calculateDiscount);
                            totalWithDiscount = calculate.calculateTotalWithDiscount(totalPrice, product, totalDiscount, calculateDiscount);
                            stringBuilder.append("\n");
                        }
                    }
                    discount = discountCard.getDiscount();
                }
                checkGenerator.addDiscountCardAndDiscountPercentage(stringBuilder, number, discount);
            }
        }
        checkGenerator.addFooter(stringBuilder, totalPrice, totalDiscount, totalWithDiscount);
        return stringBuilder.toString();
    }
}