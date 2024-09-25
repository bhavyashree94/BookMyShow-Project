package com.example.Book_My_Show.EntryDTOs;

import lombok.Data;

@Data
public class TheaterDTO {
    private String theaterName;

    private String theaterLocation;

    private int classicSeatCount;

    private int premiumSeatCount;
}
