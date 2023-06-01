package com.eu.test.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoomDto {

    private Long id;
    private String nameRoom;
    private List<String> boosForRoomName;
}