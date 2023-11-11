package com.ticket.service.Imp;

import com.ticket.domain.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TicketServiceImp {
    List<Ticket> listTicket();
    ResponseEntity<Ticket> saveTicket(Ticket ticket);
    void deleteTicket(Long id_ticket);
    Optional<Ticket> findTicket(Long id_ticket);
    ResponseEntity<Ticket> updateTicket(Long id,Ticket ticket);
}
