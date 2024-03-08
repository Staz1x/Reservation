package com.example.reservation2.controllers;

import com.example.reservation2.services.BookingService;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class BookingController {

    private BookingService bookingService;



    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;

    }

}
