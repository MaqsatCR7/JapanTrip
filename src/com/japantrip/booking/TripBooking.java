package com.japantrip.booking;

import com.japantrip.api.BookingAPI;

public abstract class TripBooking {
    protected BookingAPI bookingApi;

    protected TripBooking(BookingAPI bookingApi) {
        this.bookingApi = bookingApi;
    }

    public void setBookingApi(BookingAPI bookingApi) {
        this.bookingApi = bookingApi;
    }

    public abstract void confirmBooking(String tripDetails, double basePrice);
}