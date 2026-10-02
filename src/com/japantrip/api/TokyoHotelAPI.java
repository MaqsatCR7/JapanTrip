package com.japantrip.api;

public class TokyoHotelAPI implements BookingAPI {
    @Override
    public void executeReservation(String details, double price) {
        System.out.println("[Tokyo Hotel] Reserving room: " + details + " | Cost: $" + price);
    }
}