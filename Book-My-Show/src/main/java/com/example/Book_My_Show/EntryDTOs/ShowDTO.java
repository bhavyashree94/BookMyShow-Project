package com.example.Book_My_Show.EntryDTOs;

import com.example.Book_My_Show.Enums.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowDTO {

    private LocalDate localDate;

    private ShowType showType;

    private LocalTime localTime;

    private int theaterId;

    private int movieId;

    private int classicSeatsPrice;

    private  int premiumSeatsPrice;
}
