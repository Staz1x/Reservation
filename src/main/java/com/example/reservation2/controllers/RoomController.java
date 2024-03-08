package com.example.reservation2.controllers;

import com.example.reservation2.models.Room;
import com.example.reservation2.services.RoomService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;


    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping("/")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping("/")
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable Long id) {
        roomService.deleteRoomById(id);
    }


}
