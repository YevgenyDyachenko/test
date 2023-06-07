package com.eu.hotel.dto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookingDto {
    private Long id;
    private String nameBoo;
    private String guestName;
    private String roomName;
    private LocalDate checkIn ;
    private LocalDate checkOut ;
}
