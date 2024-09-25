package com.example.Book_My_Show.Repositories;

import com.example.Book_My_Show.Modules.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository< Ticket,Integer> {
}
