package com.ticket.dao;

import com.ticket.domain.Product;
import com.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDao extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
    Boolean existsByNameAndIdNot(String name,Long id);
}
