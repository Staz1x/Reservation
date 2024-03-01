package com.example.reservation2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@Entity
@ToString
@Table(name = "booking_dates")
@Getter
@Setter
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
