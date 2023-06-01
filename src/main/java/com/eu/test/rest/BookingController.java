package com.eu.test.rest;

import com.eu.test.domain.Booking;
import com.eu.test.domain.Guest;
import com.eu.test.dto.BookingDto;
import com.eu.test.dto.GuestDto;
import com.eu.test.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/bookings")
    public void save(@RequestBody Booking booking) {
        bookingService.save(booking);
    }

    @GetMapping("/bookings")
    public List<BookingDto> findAll() {
        return bookingService.findAll();
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingDto> findById(@PathVariable Long id) {
        return bookingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/bookings/rooms/{roomId}/guests/{guestId}")
    public ResponseEntity<Void> reserve(@PathVariable Long roomId, @PathVariable Long guestId) {
        bookingService.reserve(roomId, guestId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }



}
