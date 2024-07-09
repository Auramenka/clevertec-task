package main.java.ru.clevertec.check;

import java.io.FileWriter;
import java.io.IOException;

public class CsvGenerator {

    public CsvGenerator() {
    }

    public void printCheckInFile(String str) {
        try (FileWriter writer = new FileWriter("result.csv")) {
            writer.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
