package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.EntryDTOs.TicketDTO;
import com.example.Book_My_Show.Services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
    private  final TicketService ticketService;
    public TicketController(TicketService ticketService)
    {
        this.ticketService=ticketService;
    }

    @PostMapping("/add-Ticket")
    ResponseEntity<String> addTicket(@RequestBody TicketDTO ticketDTO){
        try {
            String response = ticketService.addTicket(ticketDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Ticket not generated" , HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-ticket")
    ResponseEntity<String> deleteTicket(@RequestParam ("id") int ticketId)
    {
        String response = ticketService.deleteTicket(ticketId);
        return new ResponseEntity<>(response , HttpStatus.ACCEPTED);
    }

   }
