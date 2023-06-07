package com.eu.hotel.domain;
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
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nameGuest;

    @OneToMany(mappedBy = "guest")
    private List<Booking> boosForGuest;


}
