package com.example.reservation2.repositories;

import com.example.reservation2.models.Booking;
import com.example.reservation2.models.Room;
import com.example.reservation2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository <Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByRoom(Room room);


}
