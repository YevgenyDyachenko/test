package com.eu.test.dto;

import com.eu.test.domain.Room;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ScheduleDto {
    private Long id;
    private String roomName;
    private LocalDate localData;
    private boolean isBooked;

}
