package com.eu.hotel.rest;


import com.eu.hotel.domain.Room;
import com.eu.hotel.dto.RoomDto;
import com.eu.hotel.service.RoomService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {


    private final RoomService roomService;

    @PostMapping("/rooms")
    public void save(@RequestBody Room room) {
        roomService.save(room);
    }

    @GetMapping("/rooms")
    public List<RoomDto> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomDto> findById(@PathVariable Long id) {
        return roomService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
