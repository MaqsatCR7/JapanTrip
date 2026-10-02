package com.japantrip;

import com.japantrip.api.BookingAPI;
import com.japantrip.api.JapanAirlinesAPI;
import com.japantrip.api.JRPassAPI;
import com.japantrip.api.TokyoHotelAPI;
import com.japantrip.booking.StandardTripBooking;
import com.japantrip.booking.TripBooking;
import com.japantrip.booking.VipTripBooking;

public class Main {
    public static void main(String[] args) {
        BookingAPI jalFlight = new JapanAirlinesAPI();
        BookingAPI jrPass = new JRPassAPI();
        BookingAPI hotel = new TokyoHotelAPI();

        // 1. Standard Trip Booking with Flight
        TripBooking trip = new StandardTripBooking(jalFlight);
        trip.confirmBooking("Astana to Haneda Airport", 1100.0);

        // Dynamic switching at runtime (Switching API implementation without modifying Abstraction)
        System.out.println("\n[Client] Switching to JR Pass for internal transportation...");
        trip.setBookingApi(jrPass);
        trip.confirmBooking("7-Day Whole Japan Shinkansen Pass", 300.0);

        // 2. VIP Trip Booking with Hotel
        System.out.println("\n[Client] Creating VIP Booking...");
        TripBooking vipTrip = new VipTripBooking(hotel);
        vipTrip.confirmBooking("Shinjuku Luxury Suite 5 Nights", 2000.0);
    }
}