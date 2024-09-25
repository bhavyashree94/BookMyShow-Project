package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.EntryDTOs.MovieEntryDTO;
import com.example.Book_My_Show.Modules.Movie;

public class MovieConverters {
    public static Movie covertEntryDtoToEntity(MovieEntryDTO movieEntryDTO)
    {
        Movie movie = Movie.builder().movieName(movieEntryDTO.getMovieName())
                .movieDuration(movieEntryDTO.getMovieDuration())
                .movieLanguage(movieEntryDTO.getMovieLanguage())
                .genre(movieEntryDTO.getGenre())
                .ratings(movieEntryDTO.getRatings()).build();
        return movie;
    }
}
