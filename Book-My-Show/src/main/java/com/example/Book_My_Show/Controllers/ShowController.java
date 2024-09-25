package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.EntryDTOs.ShowDTO;
import com.example.Book_My_Show.Services.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShowController {
    private final ShowService showService;

    public ShowController(ShowService showService)
    {
        this.showService=showService;
    }
    @PostMapping("/add-show")
    public ResponseEntity<String> addShow(@RequestBody ShowDTO showDTO)
    {
        try {
            String response = showService.addShow(showDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Show not added" , HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-show")
    public ResponseEntity<String> deleteShow(@RequestParam ("id") int showId) {

        String response = showService.deleteShow(showId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
