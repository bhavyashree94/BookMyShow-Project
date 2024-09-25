package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.EntryDTOs.TheaterDTO;
import com.example.Book_My_Show.Modules.Theater;

public class TheaterConverter {
    public static Theater covertEntryDtoToEntity(TheaterDTO theaterDTO)
    {
        Theater theater = Theater.builder().theaterName(theaterDTO.getTheaterName())
                .theaterLocation(theaterDTO.getTheaterLocation()).build();
        return theater;
    }
}
