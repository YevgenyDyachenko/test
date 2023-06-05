package com.eu.test.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nameRoom;
    @Column
    private Boolean isAvailable =true;

    @ElementCollection
    private List<LocalDate> bookedDates;

    @OneToMany(mappedBy = "room")
    private List<Booking> boosForRoom;

    @OneToMany(mappedBy = "room")
    private List<Availability> availability;

}
