package com.eu.test.service;

import com.eu.test.dto.ScheduleDto;
import com.eu.test.domain.Schedule;
import com.eu.test.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }
    private static ScheduleDto buildScheduleDto(Schedule schedule) {
        return ScheduleDto.builder()
                .id(schedule.getId())
                .roomName(schedule.getRoom().getNameRoom())
                .localData(schedule.getLocalData())
                .isBooked(schedule.isBooked())
                .build();
    }
    public List<ScheduleDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleService::buildScheduleDto)
                .collect(Collectors.toList());
    }

}
