package com.eu.test;

import com.eu.test.domain.Booking;
import com.eu.test.domain.Guest;
import com.eu.test.domain.Room;
import com.eu.test.service.BookingService;
import com.eu.test.service.GuestService;
import com.eu.test.service.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

   // @Bean
    public CommandLineRunner initialCreate(RoomService roomService,
                                           BookingService bookingService, GuestService guestService) {
        return (args) -> {
            Room room1 = new Room(1L, "FirstRoom", null);
            Guest guest1 = new Guest(1L, "Thompson", null);

            guestService.save(guest1);
            roomService.save(room1);
            bookingService.reserve(1l, 1l);

            Room room2 = new Room(2L, "SecondRoom", null);
            Guest guest2 = new Guest(2L, "Nicholson", null);
            guestService.save(guest2);
            roomService.save(room2);
            bookingService.reserve(2l, 2l);

            Room room3 = new Room(3L, "ThirdRoom", null);
            Guest guest3 = new Guest(3L, "Holmes", null);
            guestService.save(guest3);
            roomService.save(room3);
            bookingService.reserve(3l, 3l);

        };


    }
}
