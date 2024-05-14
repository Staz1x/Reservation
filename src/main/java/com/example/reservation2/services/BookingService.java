package com.example.reservation2.services;

import com.example.reservation2.models.Booking;
import com.example.reservation2.models.User;

import java.time.LocalDate;
import java.util.List;

public interface BookingService{

    Booking createBooking(Long userId, Long roomId, LocalDate startDate, LocalDate endDate);

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    List<Booking> getBookingsByUser(User user);

    public boolean isRoomAvailable(Long roomId, LocalDate startDate, LocalDate endDate);

    void deleteBookingById(Long id);

    List<Booking> findBookingsByRoomIdAndStartDateBetweenOrEndDateBetween(Long roomId, LocalDate startDate, LocalDate endDate, LocalDate startDate2, LocalDate endDate2);



}
