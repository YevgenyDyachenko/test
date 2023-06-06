package com.eu.test.repository;


import com.eu.test.domain.Room;
import com.eu.test.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findScheduleByRoomAndLocalData(Room room, LocalDate localDate);
}

