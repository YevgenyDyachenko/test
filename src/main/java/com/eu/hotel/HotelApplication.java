package com.eu.hotel;


import com.eu.hotel.service.BookingService;
import com.eu.hotel.service.GuestService;
import com.eu.hotel.domain.Guest;
import com.eu.hotel.domain.Room;
import com.eu.hotel.service.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class HotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }

   // @Bean
    public CommandLineRunner initialCreate(RoomService roomService,
                                           BookingService bookingService, GuestService guestService) {
        return (args) -> {
            Room room1 = new Room(1L, "FirstRoom", null, null);
            Guest guest1 = new Guest(1L, "Thompson", null);

            guestService.save(guest1);
            roomService.save(room1);
            bookingService.reserve(1L, 1L, LocalDate.of(2016, 9, 23), LocalDate.of(2016, 9, 24) );

            Room room2 = new Room(2L, "SecondRoom", null, null);
            Guest guest2 = new Guest(2L, "Nicholson", null);
            guestService.save(guest2);
            roomService.save(room2);
            bookingService.reserve(2L, 2L, LocalDate.of(2017, 10, 2), LocalDate.of(2017, 10, 4));



            LocalDate date1 =  LocalDate.of(2023, 8, 5);
            String endDate = "2023/08/07";
            LocalDate date2 = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            Room room3 = new Room(3L, "ThirdRoom", null, null);
            Guest guest3 = new Guest(3L, "Holmes", null);
            guestService.save(guest3);
            roomService.save(room3);
            bookingService.reserve(3L, 3L, date1 , date2);



        };


    }
}
