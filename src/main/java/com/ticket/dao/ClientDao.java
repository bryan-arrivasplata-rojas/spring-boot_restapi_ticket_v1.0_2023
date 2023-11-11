package com.ticket.dao;

import com.ticket.domain.Client;
import com.ticket.domain.Store;
import oracle.ons.Cli;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDao extends JpaRepository<Client,Long> {
    Optional<Client> findByNameAndLastname(String name, String lastname);
}
