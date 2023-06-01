package com.eu.test.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "room")
    private List<Booking> boosForRoom;

}
