package japantrip; // корневой пакет проекта

import japantrip.element.Hotel;
import japantrip.element.Restaurant;
import japantrip.element.Temple;
import japantrip.element.TrainTicket;
import japantrip.structure.Trip;
import japantrip.visitor.BudgetVisitor;
import japantrip.visitor.PackingTipsVisitor;
import japantrip.visitor.TimePlannerVisitor;


public class Main {

    // main = точка входа: с этого метода Java начинает выполнение программы
    public static void main(String[] args) {

        // 1. Создаём структуру и добавляем разные элементы
        Trip trip = new Trip("Trip to Japan: Tokyo - Kyoto");
        trip.add(new Hotel("Shinjuku Granbell", "Tokyo", 3, 90));          // отель: 3 ночи по 90$
        trip.add(new Temple("Senso-ji", "Tokyo", 0, 2));                   // храм: бесплатно, 2 часа
        trip.add(new Restaurant("Ichiran Ramen", "Tokyo", "Tonkotsu ramen", 15)); // ресторан: чек 15$
        trip.add(new TrainTicket("Tokyo", "Kyoto", 100, 140));             // поезд: 100$, 140 минут
        trip.add(new Hotel("Kyoto Machiya Inn", "Kyoto", 2, 110));         // отель: 2 ночи по 110$
        trip.add(new Temple("Fushimi Inari", "Kyoto", 0, 3));              // храм: бесплатно, 3 часа
        trip.add(new Temple("Kinkaku-ji", "Kyoto", 4, 1));                 // храм: 4$, 1 час
        trip.add(new Restaurant("Nishiki Market", "Kyoto", "Street food", 20));   // рынок: чек 20$

        System.out.println("=== " + trip.getTitle() + " ===\n");

        // 2. Визитор #1: бюджет
        System.out.println("--- Budget ---");
        BudgetVisitor budget = new BudgetVisitor(); // создаём визитора
        trip.accept(budget);                        // запускаем обход: здесь происходит double dispatch
        System.out.println("TOTAL: " + budget.getTotal() + " USD\n");

        // 3. Визитор #2: планировщик времени
        System.out.println("--- Time planner ---");
        TimePlannerVisitor time = new TimePlannerVisitor();
        trip.accept(time);                          // та же структура, другая операция
        System.out.printf("TOTAL: %.1f hours%n%n", time.getTotalHours()); // %.1f = одно число после запятой

        // 4. Визитор #3: советы по сборам (добавлен без изменения элементов)
        System.out.println("--- Packing tips (3rd visitor, no element changed) ---");
        PackingTipsVisitor tips = new PackingTipsVisitor();
        trip.accept(tips);
        tips.getTips().forEach(tip -> System.out.println("  * " + tip)); // лямбда: печатаем каждый совет
    }
}