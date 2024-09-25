package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.EntryDTOs.MovieEntryDTO;
import com.example.Book_My_Show.Services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService)
    {
        this.movieService=movieService;
    }
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDTO movieEntryDTO)
    {
        try {
            String response = movieService.addMovie(movieEntryDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Movie not added",HttpStatus.BAD_REQUEST);
        }
    }
}
