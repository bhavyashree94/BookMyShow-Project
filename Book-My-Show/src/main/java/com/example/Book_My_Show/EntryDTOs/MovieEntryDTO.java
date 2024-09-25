package com.example.Book_My_Show.EntryDTOs;

import com.example.Book_My_Show.Enums.Genre;
import com.example.Book_My_Show.Enums.MovieLanguage;
import lombok.Data;

@Data

public class MovieEntryDTO {
    private String movieName;

    private double ratings;

    private int movieDuration;

    private Genre genre;

    private MovieLanguage movieLanguage;

}
