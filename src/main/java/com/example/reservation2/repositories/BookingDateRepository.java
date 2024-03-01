package com.example.reservation2.repositories;

import com.example.reservation2.models.BookingDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDateRepository extends JpaRepository<BookingDate, Long> {
}
