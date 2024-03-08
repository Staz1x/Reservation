package com.example.reservation2.services;

import com.example.reservation2.models.Booking;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingServiceImpl implements BookingService{
    @Override
    public Booking createBooking(Booking booking) {
        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
        return null;
    }

    @Override
    public Booking getBookingById(Long id) {
        return null;
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return null;
    }

    @Override
    public void deleteBookingById(Long id) {

    }
}
