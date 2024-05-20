package com.example.reservation2.repositories;

import com.example.reservation2.models.BookingDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDateRepository extends JpaRepository<BookingDate, Long> {

    void deleteBookingDateByBooking_BookingId(Long Id);


}
