package com.eu.hotel.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class RoomDto {

    private Long id;
    private String nameRoom;
    private List<String> boosForRoomName;
    private List<LocalDate> datesReserved;
}