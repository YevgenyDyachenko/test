package com.eu.hotel.repository;

import com.eu.hotel.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findGuestByPhoneNumber(String phoneNumber);
}
