package main.java.ru.clevertec.check;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiscountCardList implements IParser<DiscountCard> {

    private List<DiscountCard> discountCards;

    public DiscountCardList() {
        discountCards = new ArrayList<>();
    }

    public DiscountCardList(final String file) {
        this();
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                DiscountCard discountCard = parse(line);
                discountCards.add(discountCard);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DiscountCard parse(String line) {
        final String REGEX = ";";
        final int DISCOUNT_CARD_CLASS = 2;
        String[] values = line.split(REGEX);
        DiscountCard discountCard = null;

        int number = Integer.parseInt(values[0]);
        int discount = Integer.parseInt(values[1]);

        if (values.length == DISCOUNT_CARD_CLASS) {
            discountCard = new DiscountCard.Builder()
                    .withNumber(number).withDiscount(discount).build();
        }
        return discountCard;
    }

    public List<DiscountCard> print() {
        return discountCards;
    }
}
