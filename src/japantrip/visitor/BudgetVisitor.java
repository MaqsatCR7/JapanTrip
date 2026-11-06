package japantrip.visitor; // пакет visitor

import japantrip.element.Hotel;
import japantrip.element.Restaurant;
import japantrip.element.Temple;
import japantrip.element.TrainTicket;

public class BudgetVisitor implements TripVisitor {

    private int total; // накопитель: сюда складываем стоимость каждого элемента (по умолчанию 0)

    @Override
    public void visitHotel(Hotel hotel) {
        // стоимость отеля = ночи * цена за ночь
        add("Hotel " + hotel.getName(), hotel.getNights() * hotel.getPricePerNightUsd());
    }

    @Override
    public void visitTemple(Temple temple) {
        // стоимость храма = цена входного билета
        add("Temple " + temple.getName(), temple.getTicketPriceUsd());
    }

    @Override
    public void visitRestaurant(Restaurant restaurant) {
        // стоимость ресторана = средний чек
        add("Meal at " + restaurant.getName(), restaurant.getAverageBillUsd());
    }

    @Override
    public void visitTrainTicket(TrainTicket ticket) {
        // стоимость поезда = цена билета
        add("Train " + ticket.getFrom() + " -> " + ticket.getTo(), ticket.getPriceUsd());
    }

    // private-метод-помощник: чтобы не дублировать код сложения и печати в каждом visitX
    private void add(String label, int cost) {
        total += cost; // прибавляем стоимость к общей сумме
        // printf = форматированный вывод: %-34s = строка, выровненная влево на 34 символа; %5d = число на 5 символов
        System.out.printf("  [Budget] %-34s %5d USD%n", label, cost);
    }

    // Геттер: клиент (Main) после обхода забирает итоговую сумму
    public int getTotal() { return total; }
}