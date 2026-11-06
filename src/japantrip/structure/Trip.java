package japantrip.structure; // пакет structure, тут лежит структура объектов

import japantrip.element.TripElement;
import japantrip.visitor.TripVisitor;

import java.util.ArrayList;
import java.util.List;

public class Trip {

    private final String title;                                   // название поездки
    private final List<TripElement> elements = new ArrayList<>(); // список ВСЕХ элементов (любых типов)

    public Trip(String title) { this.title = title; }

    // Добавляем любой элемент. Тип параметра = интерфейс TripElement,
    // поэтому сюда подходят Hotel, Temple, Restaurant, TrainTicket.
    public void add(TripElement element) { elements.add(element); }

    public String getTitle() { return title; }

    // Обход структуры: для каждого элемента вызываем accept(visitor).
    // Дальше срабатывает double dispatch, и проверки типа не нужны.
    public void accept(TripVisitor visitor) {
        for (TripElement element : elements) { // цикл for-each по всем элементам
            element.accept(visitor);           // элемент сам выберет нужный visitX
        }
    }
}