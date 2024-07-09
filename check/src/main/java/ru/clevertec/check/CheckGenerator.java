package main.java.ru.clevertec.check;

import java.time.LocalDate;
import java.time.LocalTime;

public class CheckGenerator {

    public CheckGenerator() {
    }

    public void addHeader(StringBuilder stringBuilder) {
        stringBuilder.append("Date;" + "Time");
        stringBuilder.append("\n");
        stringBuilder.append(LocalDate.now() + ";" + LocalTime.now());
        stringBuilder.append("\n\n");
        stringBuilder.append("QTY;" + "DESCRIPTION;" + "PRICE;" + "DISCOUNT;" + "TOTAL");
        stringBuilder.append("\n");
    }

    public void addFooter(StringBuilder stringBuilder,
                             double totalPrice, double totalDiscount, double totalWithDiscount) {
        stringBuilder.append("\n");
        stringBuilder.append("TOTAL PRICE;" + "TOTAL DISCOUNT;" + "TOTAL WITH DISCOUNT;");
        stringBuilder.append("\n");
        stringBuilder.append(String.format("%.2f", totalPrice) + "%;" +
                String.format("%.2f", totalDiscount) + "%;" + String.format("%.2f", totalWithDiscount) + "%");
    }

    public void addDiscountCardAndDiscountPercentage(StringBuilder stringBuilder, int number, int discount) {
        stringBuilder.append("\n");
        stringBuilder.append("DISCOUNT CARD;" + "DISCOUNT PERCENTAGE");
        stringBuilder.append("\n");
        stringBuilder.append(number + ";" + discount + "%");
        stringBuilder.append("\n");
    }
}
