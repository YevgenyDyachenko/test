package com.eu.test.rest;

import com.eu.test.dto.ScheduleDto;

import com.eu.test.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    @GetMapping("/schedule")
    public List<ScheduleDto> findAll() {
        return scheduleService.findAll();
    }
}
