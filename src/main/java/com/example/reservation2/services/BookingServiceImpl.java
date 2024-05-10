package com.example.reservation2.services;

import com.example.reservation2.models.Booking;
import com.example.reservation2.models.BookingDate;
import com.example.reservation2.models.User;
import com.example.reservation2.repositories.BookingDateRepository;
import com.example.reservation2.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingServiceImpl implements BookingService{


    private BookingRepository bookingRepository;

    private BookingDateRepository bookingDateRepository;
    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingDateRepository bookingDateRepository){
        this.bookingRepository = bookingRepository;
        this.bookingDateRepository = bookingDateRepository;
    }



    @Override
    public Booking createBooking(Booking booking) {
        // Spara bokningen först
        booking = bookingRepository.save(booking);


        return booking;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }
}
