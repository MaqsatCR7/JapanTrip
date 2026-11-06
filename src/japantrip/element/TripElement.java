package japantrip.element; // пакет: папка element, тут лежат все элементы поездки

import japantrip.visitor.TripVisitor; // импорт интерфейса Visitor, он нужен в сигнатуре метода accept

public interface TripElement {

    // accept = "принять посетителя".
    // Каждый элемент реализует его и внутри вызывает нужный visitX() у посетителя.
    // Это ПЕРВЫЙ шаг double dispatch: Java выбирает accept() по реальному типу элемента.
    void accept(TripVisitor visitor);
}