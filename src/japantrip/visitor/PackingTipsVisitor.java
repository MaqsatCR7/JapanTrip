package japantrip.visitor; // пакет visitor

import japantrip.element.Hotel;
import japantrip.element.Restaurant;
import japantrip.element.Temple;
import japantrip.element.TrainTicket;

import java.util.LinkedHashSet; // множество, которое убирает дубли и сохраняет порядок добавления
import java.util.Set;

public class PackingTipsVisitor implements TripVisitor {

    // Set вместо List, чтобы одинаковые советы не повторялись
    private final Set<String> tips = new LinkedHashSet<>();

    @Override
    public void visitHotel(Hotel hotel) {
        tips.add("Power adapter type A/B and slippers (" + hotel.getCity() + ")");
    }

    @Override
    public void visitTemple(Temple temple) {
        tips.add("Modest clothes and coins for offerings (" + temple.getName() + ")");
    }

    @Override
    public void visitRestaurant(Restaurant restaurant) {
        tips.add("Cash in yen and basic Japanese phrases (" + restaurant.getName() + ")");
    }

    @Override
    public void visitTrainTicket(TrainTicket ticket) {
        tips.add("IC card (Suica) and a small bag for the train (" + ticket.getTo() + ")");
    }

    // Клиент забирает готовый список советов
    public Set<String> getTips() { return tips; }
}