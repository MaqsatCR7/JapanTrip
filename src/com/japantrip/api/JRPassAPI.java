package com.japantrip.api;

public class JRPassAPI implements BookingAPI {
    @Override
    public void executeReservation(String details, double price) {
        System.out.println("[JR Bullet Train] Reserving Shinkansen pass: " + details + " | Cost: $" + price);
    }
}