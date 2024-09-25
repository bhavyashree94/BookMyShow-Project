package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.EntryDTOs.TicketDTO;
import com.example.Book_My_Show.Modules.Ticket;

public class TicketConverter {
    public static Ticket covertEntryDtoToEntity(TicketDTO ticketDTO)
    {
        Ticket ticket = new Ticket();
        return ticket;
    }
}
