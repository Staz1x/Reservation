package com.example.reservation2.services;

import com.example.reservation2.models.Booking;
import com.example.reservation2.models.Room;
import com.example.reservation2.models.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookingService{

    Booking createBooking(Long userId, Long roomId, LocalDate startDate, LocalDate endDate);

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    List<Booking> getBookingsByUser(User user);

    boolean isRoomAvailable(Long roomId, LocalDate startDate, LocalDate endDate);

    void deleteBookingById(Long id);

    List<Booking> findBookingsByRoomIdAndStartDateBetweenOrEndDateBetween(Long roomId, LocalDate startDate, LocalDate endDate, LocalDate startDate2, LocalDate endDate2);

    List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate);

}
