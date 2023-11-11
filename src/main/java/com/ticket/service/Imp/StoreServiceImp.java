package com.ticket.service.Imp;

import com.ticket.domain.Store;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StoreServiceImp {
    List<Store> listStore();
    ResponseEntity<Store> saveStore(Store store);
    void deleteStore(Long id_store);
    Optional<Store> findStore(Long id_store);
    ResponseEntity<Store> updateStore(Long id,Store store);
}
