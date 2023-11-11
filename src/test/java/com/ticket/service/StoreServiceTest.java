package com.ticket.service;

import com.ticket.domain.Store;
import com.ticket.exception.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class StoreServiceTest {
    @Autowired
    StoreService storeService;

    @DisplayName("Test para Listar Storees")
    @Test
    @Transactional
    void listStores(){
        //given
        Store store = Store.builder()
                .name("Store Name")
                .ubication("Store Ubication").
                build();
        Store store1 = Store.builder()
                .name("Store Name1")
                .ubication("Store Ubication 1").
                build();
        //When
        storeService.saveStore(store);
        storeService.saveStore(store1);
        //Then
        List<Store> listStores = storeService.listStore();
        Assertions.assertThat(listStores).isNotNull();
        Assertions.assertThat(listStores.size()).isEqualTo(2);
    }
    @DisplayName("Test para guardar store")
    @Test
    @Transactional
    void saveStore(){
        //given
        Store store1 = Store.builder()
                .name("Store Name Save")
                .ubication("Store Ubication").
                build();
        //When
        ResponseEntity<Store> storesaved = storeService.saveStore(store1);
        //Then
        Assertions.assertThat(storesaved).isNotNull();
        Assertions.assertThat(storesaved.getBody().getId()).isGreaterThan(0);
    }
    @DisplayName("Test para store duplicado al guardar store")
    @Test
    @Transactional
    void saveStoreDuplicate(){
        //given
        Store store1 = Store.builder()
                .name("Store Name Find")
                .ubication("Store Ubication").
                build();
        //When
        try{
            storeService.saveStore(store1);
            storeService.saveStore(store1);
        }catch (ResourceNotFoundException e) {
            //Then
            Assertions.assertThat(e.getMessage()).isEqualTo("El store con ese nombre ya existe"); // Verifica el mensaje de la excepción
        }
    }
    @DisplayName("Test para eliminar store")
    @Test
    @Transactional
    void deleteStore(){
        //given
        Store store1 = Store.builder()
                .name("Store Name Delete")
                .ubication("Store Ubication").
                build();
        storeService.saveStore(store1);
        //When
        storeService.deleteStore(store1.getId());
        Optional<Store> storeOptional = storeService.findStore(store1.getId());
        //Then
        Assertions.assertThat(storeOptional).isEmpty();
    }
    @DisplayName("Test para encontrar store")
    @Test
    @Transactional
    void findStore(){
        //given
        Store store1 = Store.builder()
                .name("Store Name Find")
                .ubication("Store Ubication").
                build();
        storeService.saveStore(store1);
        //When
        Optional<Store> storeOptional = storeService.findStore(store1.getId());
        //Then
        Assertions.assertThat(storeOptional).isNotEmpty();
    }
    @DisplayName("Test para actualizar store")
    @Test
    @Transactional
    void updateStore() {
        //given
        Store store1 = Store.builder()
                .name("Store Name Upd")
                .ubication("Store Ubication").
                build();
        storeService.saveStore(store1);
        //When
        Store storeToUpdate = storeService.findStore(store1.getId()).get();

        storeToUpdate.setName("Store Name Update");
        storeToUpdate.setUbication("Store Ubication Update");
        ResponseEntity<Store> storeUpdated = storeService.updateStore(store1.getId(), storeToUpdate);
        //Then
        Store storeBody = storeUpdated.getBody();
        if (storeBody != null) {
            Assertions.assertThat(storeBody.getName()).isEqualTo("Store Name Update");
            Assertions.assertThat(storeBody.getUbication()).isEqualTo("Store Ubication Update");
        }
    }
}

