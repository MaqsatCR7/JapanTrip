package japantrip.element; // пакет element

import japantrip.visitor.TripVisitor; // импорт интерфейса визитора

public class TrainTicket implements TripElement {

    private final String from;            // станция/город отправления
    private final String to;              // станция/город прибытия
    private final int priceUsd;           // цена билета в долларах
    private final int durationMinutes;    // время в пути в минутах

    public TrainTicket(String from, String to, int priceUsd, int durationMinutes) {
        this.from = from;
        this.to = to;
        this.priceUsd = priceUsd;
        this.durationMinutes = durationMinutes;
    }

    // Геттеры
    public String getFrom()         { return from; }
    public String getTo()           { return to; }
    public int getPriceUsd()        { return priceUsd; }
    public int getDurationMinutes() { return durationMinutes; }

    @Override
    public void accept(TripVisitor visitor) {
        // Билет вызывает visitTrainTicket и передаёт себя
        visitor.visitTrainTicket(this);
    }
}