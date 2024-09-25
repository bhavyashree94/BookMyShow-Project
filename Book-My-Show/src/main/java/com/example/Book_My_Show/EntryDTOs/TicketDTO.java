package com.example.Book_My_Show.EntryDTOs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketDTO {

    private int showId;

    private int userId;

    List<String> requestedSeat = new ArrayList<>();
}
