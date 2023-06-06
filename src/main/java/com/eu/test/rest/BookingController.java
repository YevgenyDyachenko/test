package com.eu.test.rest;

import com.eu.test.domain.Booking;
import com.eu.test.dto.BookingDto;
import com.eu.test.service.BookingService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/bookings/rooms/{roomId}/guests/{guestId}/start/{start}/end/{end}")
    public ResponseEntity<Void> reserve(@PathVariable Long roomId, @PathVariable Long guestId, @PathVariable LocalDate start, @PathVariable LocalDate end) {
        bookingService.reserve(roomId, guestId, start , end );

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/bookings/{id}")
    public void deleteById(@PathVariable Long id) {
        bookingService.deleteBooking(id);

    }

    @PostMapping("/bookings/update/{oldBookingId}/rooms/{roomId}/guests/{guestId}/start/{start}/end/{end}")
    public ResponseEntity<Void> update(@PathVariable Long oldBookingId, @PathVariable Long roomId, @PathVariable Long guestId, @PathVariable LocalDate start, @PathVariable LocalDate end) {
        bookingService.updateBooking(oldBookingId, roomId, guestId, start , end );

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }





}
