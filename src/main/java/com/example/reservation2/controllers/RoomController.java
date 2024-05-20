package com.example.reservation2.controllers;

import com.example.reservation2.Exceptions.RoomNotFoundException;
import com.example.reservation2.models.Room;
import com.example.reservation2.services.BookingService;
import com.example.reservation2.services.RoomService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private RoomService roomService;

    private BookingService bookingService;


    public RoomController(RoomService roomService, BookingService bookingService){
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    @GetMapping("/")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/available/{startDate}/{endDate}")
    public List<Room> getAvailableRooms(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                        @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
    {return bookingService.findAvailableRooms(startDate, endDate);}

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);

        if(room == null){
            throw new RoomNotFoundException("Can not found room" + id);
        }
        return room;
    }

    @PostMapping("/")
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);

        if(room == null){
            throw new RoomNotFoundException("Can not found room number " + id);
        }
        roomService.deleteRoomById(id);
    }
}
