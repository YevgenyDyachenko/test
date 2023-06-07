package com.eu.hotel.rest;

import com.eu.hotel.domain.Guest;
import com.eu.hotel.dto.GuestDto;
import com.eu.hotel.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @PostMapping("/guests")
    public void save(@RequestBody Guest guest) {
        guestService.save(guest);
    }

    @GetMapping("/guests")
    public List<GuestDto> findAll() {
        return guestService.findAll();
    }

    @GetMapping("/guests/{id}")
    public ResponseEntity<GuestDto> findById(@PathVariable Long id) {
        return guestService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
