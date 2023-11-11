package com.ticket.service;

import com.ticket.dao.StoreDao;
import com.ticket.domain.Store;
import com.ticket.domain.Store;
import com.ticket.exception.ResourceNotFoundException;
import com.ticket.service.Imp.StoreServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService implements StoreServiceImp {
    @Autowired
    private StoreDao storeDao;
    @Override
    public List<Store> listStore() {
        return storeDao.findAll();
    }

    @Override
    public ResponseEntity<Store> saveStore(Store store) {
        Optional<Store> storeSaved = storeDao.findByName(store.getName());
        if(storeSaved.isPresent()){
            throw new ResourceNotFoundException("El store con ese nombre ya existe");
        }
        return new ResponseEntity<>(storeDao.save(store), HttpStatus.OK);
    }

    @Override
    public void deleteStore(Long id_store) {
        storeDao.deleteById(id_store);
    }

    @Override
    public Optional<Store> findStore(Long id_store) {
        return storeDao.findById(id_store);
    }

    @Override
    public ResponseEntity<Store> updateStore(Long id, Store store) {
        Optional<Store> storeToUpdate = storeDao.findById(id);

        Store existingStore = storeToUpdate.get();

        existingStore.setName(store.getName());
        existingStore.setUbication(store.getUbication());

        Store updatedStore = storeDao.save(existingStore);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }
}
