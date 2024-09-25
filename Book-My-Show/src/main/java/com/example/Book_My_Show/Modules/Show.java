package com.example.Book_My_Show.Modules;

import com.example.Book_My_Show.Enums.ShowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "showInfo")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showId;

    private String showName;

    private LocalDate showDate;

    private LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    ShowType showType;

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    List<Ticket>ticketList = new ArrayList<>();

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    List<ShowSeat>showSeats = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Theater theater;

    @ManyToOne
    @JoinColumn
    Movie movie;
}
