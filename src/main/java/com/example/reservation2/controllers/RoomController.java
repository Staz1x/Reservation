package com.example.reservation2.controllers;

import com.example.reservation2.Exceptions.RoomNotFoundException;
import com.example.reservation2.models.Room;
import com.example.reservation2.services.RoomService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private RoomService roomService;


    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping("/")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

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
