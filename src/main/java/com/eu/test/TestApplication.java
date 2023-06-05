package com.eu.test;


import com.eu.test.domain.Guest;
import com.eu.test.domain.Room;
import com.eu.test.service.BookingService;
import com.eu.test.service.GuestService;
import com.eu.test.service.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialCreate(RoomService roomService,
                                           BookingService bookingService, GuestService guestService) {
        return (args) -> {
            Room room1 = new Room(1L, "FirstRoom", false, null, null);
            Guest guest1 = new Guest(1L, "Thompson", null);

            guestService.save(guest1);
            roomService.save(room1);
            bookingService.reserve(1L, 1L, LocalDate.of(2016, 9, 23), LocalDate.of(2016, 9, 24) );

            Room room2 = new Room(2L, "SecondRoom", false, null, null);
            Guest guest2 = new Guest(2L, "Nicholson", null);
            guestService.save(guest2);
            roomService.save(room2);
            bookingService.reserve(2L, 2L, LocalDate.of(2017, 10, 2), LocalDate.of(2017, 10, 10));

            Room room3 = new Room(3L, "ThirdRoom", false, null, null);
            Guest guest3 = new Guest(3L, "Holmes", null);
            guestService.save(guest3);
            roomService.save(room3);
            bookingService.reserve(3L, 3L, LocalDate.of(2023, 8, 5), LocalDate.of(2023, 8, 8));

        };


    }
}
