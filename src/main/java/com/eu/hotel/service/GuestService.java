package com.eu.hotel.service;

import com.eu.hotel.domain.Booking;
import com.eu.hotel.domain.Guest;
import com.eu.hotel.dto.GuestDto;
import com.eu.hotel.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    public void save(Guest guest) {
        guestRepository.save(guest);
    }
    private static GuestDto buildGuestDto(Guest guest) {
        return GuestDto.builder()
                .id(guest.getId())
                .nameGuest(guest.getNameGuest())
                .phoneNumber(guest.getPhoneNumber())
                .boosForGuestName(guest.getBoosForGuest().stream()
                        .map(Booking::getNameBoo)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<GuestDto> findAll() {
        return guestRepository.findAll()
                .stream()
                .map(GuestService::buildGuestDto)
                .collect(Collectors.toList());
    }

    public Optional<GuestDto> findById(Long id) {
        return guestRepository.findById(id).map(GuestService::buildGuestDto);
    }

    public Optional<GuestDto> findByPhoneNumber(String phone) {
        return guestRepository.findGuestByPhoneNumber(phone).map(GuestService::buildGuestDto);
    }

    public Guest update(Long id, Guest guest){
        Guest guestFmDb = guestRepository.findById(id).get();
        if(guest.getNameGuest()!=null){
            guestFmDb.setNameGuest(guest.getNameGuest());
        }
        if(guest.getPhoneNumber()!=null){
            guestFmDb.setPhoneNumber(guest.getPhoneNumber());
        }

        return guestFmDb;
    }

    public Optional<GuestDto> findGuestByName(String name) {
        return guestRepository.findGuestByNameGuest(name).map(GuestService::buildGuestDto);
    }






}