package com.example.HotelBooking.services;

import com.example.HotelBooking.dtos.BookingDTO;
import com.example.HotelBooking.dtos.Response;
import org.jspecify.annotations.Nullable;

public interface BookingService {
   Response getAllBookings();

   Response createBooking(BookingDTO bookingDTO);

     Response findBookingByReferenceNo(String reference);

   Response updateBooking(BookingDTO bookingDTO);
}
