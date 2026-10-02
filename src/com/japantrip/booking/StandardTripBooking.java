package com.japantrip.booking;

import com.japantrip.api.BookingAPI;

public class StandardTripBooking extends TripBooking {

    public StandardTripBooking(BookingAPI bookingApi) {
        super(bookingApi);
    }

    @Override
    public void confirmBooking(String tripDetails, double basePrice) {
        System.out.println("--- Processing Standard Japan Trip ---");
        bookingApi.executeReservation(tripDetails, basePrice);
    }
}