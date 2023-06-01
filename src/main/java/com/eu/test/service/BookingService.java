package com.eu.test.service;

import com.eu.test.domain.Booking;
import com.eu.test.dto.BookingDto;

import com.eu.test.repository.BookingRepository;
import com.eu.test.repository.GuestRepository;
import com.eu.test.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final BookingRepository bookingRepository;

    private static BookingDto buildBookingDto(Booking booking) {

        var guestName = booking.getGuest().getNameGuest();
        var roomName = booking.getRoom().getNameRoom();

        return BookingDto.builder()
                .id(booking.getId())
                .nameBoo(booking.getNameBoo())
                .roomName(roomName)
                .guestName(guestName)
                .build();
    }

    public List<BookingDto> findAll() {
        return bookingRepository.findAll().stream()
                .map(BookingService::buildBookingDto)
                .collect(Collectors.toList());
    }

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    public Optional<BookingDto> findById(Long id) {
        return bookingRepository.findById(id).map(BookingService::buildBookingDto);
    }

    public void reserve(Long roomId, Long guestId) {
        var guest = guestRepository.findById(guestId).get();
        var room = roomRepository.findById(roomId).get();
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setGuest(guest);
        booking.setNameBoo(room.getNameRoom() + "-"+ guest.getNameGuest());
        bookingRepository.save(booking);
    }







}
