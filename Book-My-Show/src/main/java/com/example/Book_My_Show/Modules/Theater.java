package com.example.Book_My_Show.Modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "theater_info")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterId;

    private String theaterName;

    private String theaterLocation;

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    List<TheaterSeat>theaterSeats = new ArrayList<>();

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    List<Show>showList = new ArrayList<>();

}
