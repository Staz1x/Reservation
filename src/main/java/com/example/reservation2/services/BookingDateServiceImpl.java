package com.example.reservation2.services;

import com.example.reservation2.models.BookingDate;
import com.example.reservation2.repositories.BookingDateRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingDateServiceImpl implements BookingDateService{
    private final BookingDateRepository bookingDateRepository;

    public BookingDateServiceImpl(BookingDateRepository bookingDateRepository) {
        this.bookingDateRepository = bookingDateRepository;
    }

    @Override
    public BookingDate saveBookingDate(BookingDate bookingDate) {
        return bookingDateRepository.save(bookingDate);
    }
}
