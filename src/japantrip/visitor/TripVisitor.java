package japantrip.visitor; // пакет visitor, тут лежат все операции над поездкой

// Импортируем все 4 конкретных элемента, потому что они указаны в параметрах методов
import japantrip.element.Hotel;
import japantrip.element.Restaurant;
import japantrip.element.Temple;
import japantrip.element.TrainTicket;

/**
 * VISITOR INTERFACE (интерфейс посетителя).
 * Для КАЖДОГО типа элемента объявлен СВОЙ метод visitX.
 * Благодаря этому не нужны проверки типа (if с проверкой класса): элемент сам говорит, какой метод вызвать.
 */
public interface TripVisitor {
    void visitHotel(Hotel hotel);                  // что делаем с отелем
    void visitTemple(Temple temple);               // что делаем с храмом
    void visitRestaurant(Restaurant restaurant);   // что делаем с рестораном
    void visitTrainTicket(TrainTicket ticket);     // что делаем с билетом на поезд
}