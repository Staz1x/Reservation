package com.example.reservation2.repositories;

import com.example.reservation2.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository <Booking, Long> {
}
