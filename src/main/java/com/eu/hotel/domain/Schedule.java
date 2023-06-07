package com.eu.hotel.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate localData;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @Column
    private boolean isBooked;
}
