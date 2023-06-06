package com.eu.test.service;

import com.eu.test.domain.Room;
import com.eu.test.dto.RoomDto;
import com.eu.test.dto.ScheduleDto;
import com.eu.test.domain.Schedule;
import com.eu.test.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
   // private final ScheduleService scheduleService;
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

    public Optional<ScheduleDto> findByRoomAndLocalDate(Room room, LocalDate localDate) {
        return  scheduleRepository.findScheduleByRoomAndLocalData(room, localDate).map(ScheduleService::buildScheduleDto);
    }

    //creates a list of Dates in a given range
    public List<LocalDate> listDatesInRange(LocalDate start, LocalDate end){

        return Stream.iterate(start, d -> d.isBefore(end), d -> d.plusDays(1))
                .collect(Collectors.toList());
    }

    //creates objects of Schedule for a list of dates
    public void saveListDates(List<LocalDate> dates, Room room) {
        for (LocalDate date: dates ) {
            System.out.println(date);
            Schedule sch1 = new Schedule();
            sch1.setLocalData(date);
            sch1.setRoom(room);
            sch1.setBooked(true);
            ScheduleService scheduleService1 = new ScheduleService(scheduleRepository);
            scheduleService1.save(sch1);
            scheduleRepository.save(sch1);
        }


        }
       //combine two previous methods
        public void saveRangeOfDates(Room room, LocalDate start, LocalDate end){
        ScheduleService scheduleService1 = new ScheduleService(scheduleRepository);
            scheduleService1.saveListDates(scheduleService1.listDatesInRange(start, end), room);


    }


}
