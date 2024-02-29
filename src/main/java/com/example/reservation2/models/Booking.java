package com.example.reservation2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@ToString

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;


    private long userId;

    @GeneratedValue
    private Timestamp bookingDate;


    private Date startDate;

    private Date endDate;


}
