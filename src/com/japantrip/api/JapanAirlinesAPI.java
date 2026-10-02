package com.japantrip.api;

public class JapanAirlinesAPI implements BookingAPI {
    @Override
    public void executeReservation(String details, double price) {
        System.out.println("[JAL Flight] Reserving flight to Tokyo: " + details + " | Cost: $" + price);
    }
}