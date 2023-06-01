package com.eu.test.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDto {
    private Long id;
    private String nameBoo;
    private String guestName;
    private String roomName;
}
