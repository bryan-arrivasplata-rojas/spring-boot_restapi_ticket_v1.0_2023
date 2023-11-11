package com.ticket.dao;

import com.ticket.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreDao extends JpaRepository<Store,Long> {
    Optional<Store> findByName(String name);
}
