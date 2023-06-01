package com.eu.test.service;

import com.eu.test.domain.Booking;
import com.eu.test.domain.Guest;
import com.eu.test.domain.Room;
import com.eu.test.dto.GuestDto;
import com.eu.test.dto.RoomDto;
import com.eu.test.repository.GuestRepository;
import com.eu.test.repository.RoomRepository;
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