package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.EntryDTOs.TheaterDTO;
import com.example.Book_My_Show.Services.TheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheaterController {
    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService)
    {
        this.theaterService=theaterService;
    }
    @PostMapping("/add-theater")
    public ResponseEntity<String> addTheater(@RequestBody TheaterDTO theaterDTO){
        try {
        String response = theaterService.addTheater(theaterDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
        catch (Exception e)
    {
        return new ResponseEntity<>("Theater not added" , HttpStatus.BAD_REQUEST);
    }
}
}
