package com.northern.hotel.services.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.northern.hotel.services.data.RoomRepository;
import com.northern.hotel.services.entity.Room;
import com.northern.hotel.services.entity.RoomModel;
import com.northern.hotel.services.exception.HotelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:3000/")
public class RoomServiceController {
    private final RoomRepository repository;

    public RoomServiceController(RoomRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Room> getAllRooms(){
       return new ArrayList<>(this.repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody RoomModel model){
        Room room = this.repository.save(model.translateModelToRoom());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(room.getId()).toUri();
        return ResponseEntity.created(location).body(room);
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable Long id){
        Optional<Room> room = this.repository.findById(id);
        if(room.isPresent()){
            return room.get();
        }
        throw new HotelNotFoundException("Room not found with id: " + id);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody RoomModel model){
        Optional<Room> existing = this.repository.findById(id);
        if(!existing.isPresent()){
            throw new HotelNotFoundException("Room not found with id: " + id);
        }
        Room room = model.translateModelToRoom();
        room.setId(id);
        return this.repository.save(room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteRoom(@PathVariable Long id){
        Optional<Room> existing = this.repository.findById(id);
        if(!existing.isPresent()){
            throw new HotelNotFoundException("Room not found with id: " + id);
        }
        this.repository.deleteById(id);
    }
}
