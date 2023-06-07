package com.eu.hotel.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GuestDto {

    private Long id;
    private String nameGuest;
    private String phoneNumber;
    private List<String> boosForGuestName;
}