package com.eu.test.domain;
import jakarta.persistence.*;
import lombok.*;
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


    @ManyToOne
    @JoinColumn(name="guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;


}
