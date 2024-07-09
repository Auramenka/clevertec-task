package main.java.ru.clevertec.check;

public interface IParser<T> {

    T parse(String line);
}
