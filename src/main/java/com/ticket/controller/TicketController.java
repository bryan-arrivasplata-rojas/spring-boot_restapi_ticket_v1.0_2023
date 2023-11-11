package com.ticket.controller;

import com.ticket.domain.Ticket;
import com.ticket.domain.Ticket;
import com.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService=ticketService;
    }
    @GetMapping("/tickets")
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> getTickets(){
        return ticketService.listTicket();
    }
    @GetMapping("/ticket/{id}")
    @ResponseStatus (HttpStatus.OK)
    public Optional<Ticket> getTicket(@PathVariable Long id){
        return ticketService.findTicket(id);
    }

    @PostMapping("/ticket")
    @ResponseStatus (HttpStatus.CREATED)
    public ResponseEntity<Ticket> postTicket(@RequestBody Ticket ticket){
        return ticketService.saveTicket(ticket);
    }

    @PutMapping("/ticket/{id}")
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<Ticket> putTicket(@PathVariable Long id, @RequestBody Ticket ticket){
        return ticketService.updateTicket(id,ticket);
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
        return new ResponseEntity<String>("Ticket delete successfully",HttpStatus.OK);
    }
}