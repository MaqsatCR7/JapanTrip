package com.japantrip.booking;

import com.japantrip.api.BookingAPI;

public class VipTripBooking extends TripBooking {

    public VipTripBooking(BookingAPI bookingApi) {
        super(bookingApi);
    }

    @Override
    public void confirmBooking(String tripDetails, double basePrice) {
        System.out.println("--- Processing VIP Japan Trip (15% Discount Applied) ---");
        double discountedPrice = basePrice * 0.85;
        bookingApi.executeReservation(tripDetails + " [VIP Included]", discountedPrice);
    }
}