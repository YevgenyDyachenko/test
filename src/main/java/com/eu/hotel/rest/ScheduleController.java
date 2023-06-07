package com.eu.hotel.rest;

import com.eu.hotel.domain.Room;
import com.eu.hotel.service.ScheduleService;
import com.eu.hotel.dto.ScheduleDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    @GetMapping("/schedule")
    public List<ScheduleDto> findAll() {
        return scheduleService.findAll();
    }


    @GetMapping("/schedule/find")
    public ResponseEntity<ScheduleDto> findByParam(@RequestParam Room room, @RequestParam LocalDate localDate) {
        return scheduleService.findByRoomAndLocalDate(room, localDate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/schedule/{id}")
    public void deleteById(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);

    }

    @DeleteMapping("/schedule/del")
    public void deleteById(@RequestParam Room room, @RequestParam LocalDate localDate) {
        scheduleService.deleteByRoomAndLocalDate(room, localDate);

    }

}
