package com.example.reservation2.services;

import com.example.reservation2.models.BookingDate;

import java.util.Date;
import java.util.List;

public interface BookingDateService {
    BookingDate saveBookingDate(BookingDate bookingDate);


    List<BookingDate> findAllBookingDates();


}
