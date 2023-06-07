package com.eu.hotel.service;

import com.eu.hotel.domain.Schedule;
import com.eu.hotel.dto.RoomDto;
import com.eu.hotel.repository.RoomRepository;
import com.eu.hotel.domain.Booking;

import com.eu.hotel.domain.Room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public void save(Room room) {
        roomRepository.save(room);
    }
    private static RoomDto buildRoomDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .nameRoom(room.getNameRoom())
                .boosForRoomName(room.getBoosForRoom().stream()
                        .map(Booking::getNameBoo)
                        .collect(Collectors.toList()))
                .datesReserved(room.getReservedDates().stream()
                        .map(Schedule::getLocalData)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<RoomDto> findAll() {
        return roomRepository.findAll()
                .stream()
                .map(RoomService::buildRoomDto)
                .collect(Collectors.toList());
    }

    public Optional<RoomDto> findById(Long id) {
       return  roomRepository.findById(id).map(RoomService::buildRoomDto);
    }



}