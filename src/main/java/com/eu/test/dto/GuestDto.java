package com.eu.test.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GuestDto {

    private Long id;
    private String nameGuest;
    private List<String> boosForGuestName;
}