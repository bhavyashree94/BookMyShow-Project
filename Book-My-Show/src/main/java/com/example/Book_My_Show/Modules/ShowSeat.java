package com.example.Book_My_Show.Modules;

import com.example.Book_My_Show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "showSeat")
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    private int seatPrice;

    private String seatNo;

    private boolean seatStatus;

    @Enumerated(value = EnumType.STRING)
    SeatType seatType;

    @ManyToOne
    @JoinColumn
    Show show;
}
