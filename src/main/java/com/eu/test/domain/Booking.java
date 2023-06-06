package com.eu.test.domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nameBoo;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate checkIn ;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate checkOut;


    @ManyToOne
    @JoinColumn(name="guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;


}
