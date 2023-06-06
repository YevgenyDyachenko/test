package com.eu.test.service;

import com.eu.test.domain.Booking;
import com.eu.test.domain.Guest;
import com.eu.test.domain.Room;
import com.eu.test.domain.Schedule;
import com.eu.test.dto.BookingDto;

import com.eu.test.repository.BookingRepository;
import com.eu.test.repository.GuestRepository;
import com.eu.test.repository.RoomRepository;
import com.eu.test.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final BookingRepository bookingRepository;

    private final ScheduleRepository scheduleRepository;

    private final ScheduleService scheduleService;


    private final GuestService guestService;

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
            Booking booking = new Booking();
            booking.setRoom(room);
            booking.setGuest(guest);
            booking.setNameBoo(room.getNameRoom() + "-" + guest.getNameGuest());
                       booking.setCheckIn(start);
            booking.setCheckOut(end);
            bookingRepository.save(booking);

            //creates a list of Dates that have been booked
//            List<LocalDate> rangeOfDatesInBooking =
//            scheduleService.listDatesInRange(start, end);


            //creates objects of Schedule for this list of dates
//            for (LocalDate date: rangeOfDatesInBooking ) {
//                System.out.println(date);
//                Schedule sch1 = new Schedule();
//                sch1.setLocalData(date);
//                sch1.setRoom(room);
//                sch1.setBooked(true);
//                ScheduleService scheduleService1 = new ScheduleService(scheduleRepository);
//                scheduleService1.save(sch1);
//            }
        //    scheduleService.saveListDates(rangeOfDatesInBooking, room);

            scheduleService.saveRangeOfDates(room, start, end);


        } else {
            throw new NoSuchElementException("No elements");
        }

    }






}
