package japantrip.element; // пакет element

import japantrip.visitor.TripVisitor; // импорт интерфейса визитора


public class Restaurant implements TripElement {

    private final String name;           // название ресторана
    private final String city;           // город
    private final String dish;           // что заказываем (например, рамен)
    private final int averageBillUsd;    // средний чек в долларах

    public Restaurant(String name, String city, String dish, int averageBillUsd) {
        this.name = name;
        this.city = city;
        this.dish = dish;
        this.averageBillUsd = averageBillUsd;
    }

    // Геттеры
    public String getName()        { return name; }
    public String getCity()        { return city; }
    public String getDish()        { return dish; }
    public int getAverageBillUsd() { return averageBillUsd; }

    @Override
    public void accept(TripVisitor visitor) {
        // Ресторан вызывает visitRestaurant и передаёт себя
        visitor.visitRestaurant(this);
    }
}