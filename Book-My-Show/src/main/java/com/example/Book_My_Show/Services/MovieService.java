package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Converters.MovieConverters;
import com.example.Book_My_Show.EntryDTOs.MovieEntryDTO;
import com.example.Book_My_Show.Modules.Movie;
import com.example.Book_My_Show.Repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository)
    {
        this.movieRepository=movieRepository;
    }

    public String addMovie(MovieEntryDTO movieEntryDTO) {
        Movie movie = MovieConverters.covertEntryDtoToEntity(movieEntryDTO);
        movieRepository.save(movie);
        return "Movie added Successfully";
    }
}
