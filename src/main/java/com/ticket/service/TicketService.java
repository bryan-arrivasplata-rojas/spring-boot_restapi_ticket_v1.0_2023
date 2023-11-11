package com.ticket.service;

import com.ticket.dao.TicketDao;
import com.ticket.domain.Ticket;
import com.ticket.service.Imp.TicketServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements TicketServiceImp {
    @Autowired
    private TicketDao ticketDao;
    @Override
    public List<Ticket> listTicket() {
        return ticketDao.findAll();
    }

    @Override
    public ResponseEntity<Ticket> saveTicket(Ticket ticket) {
        return new ResponseEntity<>(ticketDao.save(ticket), HttpStatus.OK);
    }

    @Override
    public void deleteTicket(Long id_ticket) {
        ticketDao.deleteById(id_ticket);
    }

    @Override
    public Optional<Ticket> findTicket(Long id_ticket) {
        return ticketDao.findById(id_ticket);
    }

    @Override
    public ResponseEntity<Ticket> updateTicket(Long id, Ticket ticket) {
        return ticketDao.findById(id).map(ticketSaved -> {
            ticketSaved.setProduct(ticket.getProduct());
            ticketSaved.setClient(ticket.getClient());

            Ticket ticketSave = ticketDao.save(ticketSaved);
            return new ResponseEntity<>(ticketSave,HttpStatus.OK);
        }).orElseGet(()->ResponseEntity.notFound().build());
    }
}
