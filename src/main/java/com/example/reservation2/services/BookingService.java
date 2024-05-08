package com.example.reservation2.services;

import com.example.reservation2.models.Booking;
import com.example.reservation2.models.User;

import java.util.List;

public interface BookingService{

    Booking createBooking(Booking booking);

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    List<Booking> getBookingsByUser(User user);

    void deleteBookingById(Long id);
}
