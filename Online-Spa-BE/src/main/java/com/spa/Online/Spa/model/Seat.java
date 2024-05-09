package com.spa.Online.Spa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @Column(name = "seatid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer seatId;

    @Column(name = "seatnumber")
    Integer seatNumber;

    @Column(name = "isbooked")
    Boolean isBooked = false;
    @Column(name = "status")
    Boolean status = false;
    private Long spaId;


}
