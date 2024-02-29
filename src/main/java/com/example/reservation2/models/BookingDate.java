package com.example.reservation2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Entity
@ToString

public class BookingDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingDateId;


    private Date date;

    @ManyToOne
    private Booking booking;

    @ManyToOne
    private Room room;
}
