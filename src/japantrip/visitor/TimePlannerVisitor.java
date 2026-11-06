package japantrip.visitor; // пакет visitor

import japantrip.element.Hotel;
import japantrip.element.Restaurant;
import japantrip.element.Temple;
import japantrip.element.TrainTicket;

public class TimePlannerVisitor implements TripVisitor {

    private static final int HOURS_PER_MEAL = 1; // константа вместо "магического числа": 1 час на обед
    private int totalMinutes;                    // накопитель времени в минутах

    @Override
    public void visitHotel(Hotel hotel) {
        add("Check-in/out " + hotel.getName(), 60); // заселение и выселение = 60 минут
    }

    @Override
    public void visitTemple(Temple temple) {
        // часы посещения * 60 = минуты
        add("Visit " + temple.getName(), temple.getVisitHours() * 60);
    }

    @Override
    public void visitRestaurant(Restaurant restaurant) {
        add("Eat " + restaurant.getDish(), HOURS_PER_MEAL * 60); // обед = 1 час = 60 минут
    }

    @Override
    public void visitTrainTicket(TrainTicket ticket) {
        // время поезда уже в минутах
        add("Ride " + ticket.getFrom() + " -> " + ticket.getTo(), ticket.getDurationMinutes());
    }

    // Помощник: добавляет минуты и печатает строку
    private void add(String label, int minutes) {
        totalMinutes += minutes;
        System.out.printf("  [Time]   %-34s %4d min%n", label, minutes);
    }

    // Переводим минуты в часы; 60.0 (с .0) нужно, чтобы деление было дробным, а не целым
    public double getTotalHours() { return totalMinutes / 60.0; }
}