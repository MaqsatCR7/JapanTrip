package japantrip.element; // пакет element

import japantrip.visitor.TripVisitor; // нужен, чтобы использовать тип TripVisitor в accept

/**
 * CONCRETE ELEMENT #1 (конкретный элемент): Отель.
 * Хранит только данные об отеле. Никакой логики расчётов тут нет,
 * вся логика живёт в визиторах (это и есть смысл паттерна Visitor).
 */
public class Hotel implements TripElement { // implements = класс выполняет договор TripElement

    // private final = поле закрыто снаружи и не меняется после создания (неизменяемость)
    private final String name;           // название отеля
    private final String city;           // город, где находится отель
    private final int nights;            // сколько ночей проживаем
    private final int pricePerNightUsd;  // цена за одну ночь в долларах

    // Конструктор: вызывается при new Hotel(...) и заполняет все поля
    public Hotel(String name, String city, int nights, int pricePerNightUsd) {
        this.name = name;                         // this.name = поле класса, name = параметр конструктора
        this.city = city;
        this.nights = nights;
        this.pricePerNightUsd = pricePerNightUsd;
    }

    // Геттеры: единственный способ для визитора прочитать данные отеля.
    // Сеттеров нет, поэтому визитор может только читать, но не ломать состояние.
    public String getName()          { return name; }
    public String getCity()          { return city; }
    public int getNights()           { return nights; }
    public int getPricePerNightUsd() { return pricePerNightUsd; }

    @Override // @Override = мы реализуем метод из интерфейса TripElement
    public void accept(TripVisitor visitor) {
        // ВТОРОЙ шаг double dispatch.
        // Отель говорит визитору: "обработай именно ОТЕЛЬ" и передаёт сам себя (this).
        // Здесь this имеет точный тип Hotel, поэтому вызывается visitHotel, а не другой метод.
        visitor.visitHotel(this);
    }
}