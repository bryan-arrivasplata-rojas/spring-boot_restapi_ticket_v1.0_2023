package com.ticket.controller;

import com.ticket.domain.Store;
import com.ticket.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class StoreControllerTest {
    private StoreController storeController;
    private StoreService storeService;

    @BeforeEach
    public void setUp() {
        storeService = mock(StoreService.class);
        storeController = new StoreController(storeService);
    }

    @DisplayName("Test para obtener Lista de Stores")
    @Test
    public void getStores() throws Exception {
        // Given
        List<Store> mockStore = new ArrayList<>();
        mockStore.add(Store.builder().
                name("Bryan Daniell").
                ubication("Zorritos 1134")
                .build());
        mockStore.add(Store.builder().
                name("Jersson").
                ubication("Tingo maria 1011")
                .build());
        Mockito.when(storeService.listStore()).thenReturn(mockStore);
        // When
        List<Store> response = storeController.getStores();

        // Then
        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(0).getName()).isEqualTo("Bryan Daniell");
    }
    @DisplayName("Test para obtener Store")
    @Test
    public void getStore() throws Exception {
        // Given
        Long storeId = 1L; // ID del storee que deseas buscar
        Store mockStore = Store.builder()
                .name("Bryan Daniell")
                .ubication("Zorritos 1134")
                .build();
        Mockito.when(storeService.findStore(anyLong())).thenReturn(Optional.ofNullable(mockStore));

        // When
        Optional<Store> response = storeController.getStore(storeId);

        // Then
        assertThat(response).isNotNull();
        response.ifPresent(store -> assertThat(store.getName()).isEqualTo("Bryan Daniell"));
    }
    @DisplayName("Test para guardar Store")
    @Test
    public void postStore() throws Exception {
        // Given
        Store newStore = Store.builder()
                .name("Store Name Save")
                .ubication("Store Ubication Save")
                .build();
        Store savedStore = Store.builder()
                .id(1L)
                .name(newStore.getName())
                .ubication(newStore.getUbication())
                .build();
        ResponseEntity<Store> responseEntity = ResponseEntity.ok(savedStore);
        Mockito.when(storeService.saveStore(any(Store.class))).thenReturn(responseEntity);

        // When
        ResponseEntity<Store> response = storeController.postStore(newStore);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1L);
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Store Name Save");
    }
    @DisplayName("Test para actualizar Store")
    @Test
    public void putStore() throws Exception {
        // Given
        Long storeId = 1L; // ID del storee que deseas actualizar
        Store updatedStore = Store.builder()
                .id(storeId)
                .name("Store Name Update")
                .ubication("Store Ubication Update")
                .build();
        ResponseEntity<Store> responseEntity = ResponseEntity.ok(updatedStore);
        Mockito.when(storeService.updateStore(any(Long.class), any(Store.class))).thenReturn(responseEntity);

        // When
        ResponseEntity<Store> response = storeController.putStore(storeId, updatedStore);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(storeId);
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Store Name Update");
    }

    @DisplayName("Test para eliminar Storee")
    @Test
    public void deleteStore() throws Exception {
        // Given
        Long storeId = 1L;
        doNothing().when(storeService).deleteStore(storeId);

        // Then
        ResponseEntity<String> response = storeController.deleteStore(storeId);

        // When
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(storeService).deleteStore(storeId);
        assertThat(response.getBody()).isEqualTo("Store delete successfully");
    }
}
