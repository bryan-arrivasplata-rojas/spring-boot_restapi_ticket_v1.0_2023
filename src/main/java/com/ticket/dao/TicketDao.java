package com.ticket.dao;

import com.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketDao extends JpaRepository<Ticket,Long> {
}
