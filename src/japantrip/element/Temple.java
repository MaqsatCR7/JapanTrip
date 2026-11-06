package japantrip.element; // пакет element

import japantrip.visitor.TripVisitor; // импорт интерфейса визитора

public class Temple implements TripElement {

    private final String name;         // название храма
    private final String city;         // город
    private final int ticketPriceUsd;  // цена входного билета в долларах (может быть 0)
    private final int visitHours;      // сколько часов занимает посещение

    public Temple(String name, String city, int ticketPriceUsd, int visitHours) {
        this.name = name;
        this.city = city;
        this.ticketPriceUsd = ticketPriceUsd;
        this.visitHours = visitHours;
    }

    // Геттеры для чтения данных визиторами
    public String getName()        { return name; }
    public String getCity()        { return city; }
    public int getTicketPriceUsd() { return ticketPriceUsd; }
    public int getVisitHours()     { return visitHours; }

    @Override
    public void accept(TripVisitor visitor) {
        // Храм вызывает visitTemple и передаёт себя (this типа Temple)
        visitor.visitTemple(this);
    }
}