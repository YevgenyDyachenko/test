package com.eu.test.service;

import com.eu.test.repository.BookingRepository;
import com.eu.test.domain.Booking;
import com.eu.test.domain.Guest;
import com.eu.test.domain.Room;
import com.eu.test.dto.BookingDto;

import com.eu.test.repository.GuestRepository;
import com.eu.test.repository.RoomRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final BookingRepository bookingRepository;



    private final ScheduleService scheduleService;





    private static BookingDto buildBookingDto(Booking booking) {

        var guestName = booking.getGuest().getNameGuest();
        var roomName = booking.getRoom().getNameRoom();

        return BookingDto.builder()
                .id(booking.getId())
                .nameBoo(booking.getNameBoo())
                .roomName(roomName)
                .guestName(guestName)
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
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


    public void reserve(Long roomId, Long guestId, LocalDate start, LocalDate end) {
        Optional<Guest> optionalGuest = guestRepository.findById(guestId);
        Optional<Room> optionalRoom = roomRepository.findById(roomId);



        if (optionalGuest.isPresent() && optionalRoom.isPresent()) {

            var guest = guestRepository.findById(guestId).get();
            var room = roomRepository.findById(roomId).get();

            if(start.isAfter(LocalDate.now())|start.isEqual(LocalDate.now())&&scheduleService.checkAvailability(room, start, end)){
                Booking booking = new Booking();
                booking.setRoom(room);
                booking.setGuest(guest);
                booking.setNameBoo(room.getNameRoom() + "-" + guest.getNameGuest());
                booking.setCheckIn(start);
                booking.setCheckOut(end);
                bookingRepository.save(booking);

                //creates objects in schedule table
                scheduleService.saveRangeOfDates(room, start, end);

            } else {
                throw new EntityExistsException("Can't book on this period");
            }


        } else {
            throw new NoSuchElementException("No elements");
        }

    }




    public void deleteBooking(Long bookingId){

        LocalDate start = bookingRepository.findById(bookingId).get().getCheckIn();
        LocalDate end = bookingRepository.findById(bookingId).get().getCheckOut();
        Room room = bookingRepository.findById(bookingId).get().getRoom();
        List<LocalDate> listDates= scheduleService.listDatesInRange(start, end);
                scheduleService.deleteListDates(listDates, room);

        bookingRepository.deleteById(bookingId);
    }

    public void updateBooking(Long oldBookingId, Long roomId, Long guestId, LocalDate start, LocalDate end ){
        deleteBooking(oldBookingId);
        reserve(roomId, guestId, start, end);

    }






}
