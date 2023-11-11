package com.ticket.controller;

import com.ticket.domain.Client;
import com.ticket.domain.Product;
import com.ticket.domain.Ticket;
import com.ticket.service.TicketService;
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

public class TicketControllerTest {
    private TicketController ticketController;
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        ticketService = mock(TicketService.class);
        ticketController = new TicketController(ticketService);
    }

    @DisplayName("Test para obtener Lista de Tickets")
    @Test
    public void getTickets() throws Exception {
        // Given
        List<Ticket> mockTicket = new ArrayList<>();
        Client clientTest = Client.builder().id(1L).build();
        Product productTest = Product.builder().id(1L).build();
        mockTicket.add(Ticket.builder().
                client(clientTest)
                .product(productTest)
                .build());
        mockTicket.add(Ticket.builder().
                client(clientTest)
                .product(productTest)
                .build());
        Mockito.when(ticketService.listTicket()).thenReturn(mockTicket);
        // When
        List<Ticket> response = ticketController.getTickets();

        // Then
        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(0).getClient()).isEqualTo(clientTest);
    }
    @DisplayName("Test para obtener Ticket")
    @Test
    public void getTicket() throws Exception {
        // Given
        Client clientTest = Client.builder().id(1L).build();
        Product productTest = Product.builder().id(1L).build();
        Long ticketId = 1L; // ID del tickete que deseas buscar
        Ticket mockTicket = Ticket.builder()
                .client(clientTest)
                .product(productTest)
                .build();
        Mockito.when(ticketService.findTicket(anyLong())).thenReturn(Optional.ofNullable(mockTicket));

        // When
        Optional<Ticket> response = ticketController.getTicket(ticketId);

        // Then
        assertThat(response).isNotNull();
        response.ifPresent(ticket -> assertThat(ticket.getClient()).isEqualTo(clientTest));
    }
    @DisplayName("Test para guardar Ticket")
    @Test
    public void postTicket() throws Exception {
        // Given
        Client clientTest = Client.builder().id(1L).build();
        Product productTest = Product.builder().id(1L).build();
        Ticket newTicket = Ticket.builder()
                .client(clientTest)
                .product(productTest)
                .build();
        Ticket savedTicket = Ticket.builder()
                .id(1L)
                .client(newTicket.getClient())
                .product(newTicket.getProduct())
                .build();
        ResponseEntity<Ticket> responseEntity = ResponseEntity.ok(savedTicket);
        Mockito.when(ticketService.saveTicket(any(Ticket.class))).thenReturn(responseEntity);

        // When
        ResponseEntity<Ticket> response = ticketController.postTicket(newTicket);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1L);
        assertThat(Objects.requireNonNull(response.getBody()).getClient()).isEqualTo(clientTest);
    }
    @DisplayName("Test para actualizar Ticket")
    @Test
    public void putTicket() throws Exception {
        // Given
        Client clientTest = Client.builder().id(1L).build();
        Product productTest = Product.builder().id(1L).build();
        Long ticketId = 1L; // ID del tickete que deseas actualizar
        Ticket updatedTicket = Ticket.builder()
                .id(ticketId)
                .client(clientTest)
                .product(productTest)
                .build();
        ResponseEntity<Ticket> responseEntity = ResponseEntity.ok(updatedTicket);
        Mockito.when(ticketService.updateTicket(any(Long.class), any(Ticket.class))).thenReturn(responseEntity);

        // When
        ResponseEntity<Ticket> response = ticketController.putTicket(ticketId, updatedTicket);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(ticketId);
        assertThat(Objects.requireNonNull(response.getBody()).getClient()).isEqualTo(clientTest);
    }

    @DisplayName("Test para eliminar Ticket")
    @Test
    public void deleteTicket() throws Exception {
        // Given
        Long ticketId = 1L;
        doNothing().when(ticketService).deleteTicket(ticketId);

        // Then
        ResponseEntity<String> response = ticketController.deleteTicket(ticketId);

        // When
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(ticketService).deleteTicket(ticketId);
        assertThat(response.getBody()).isEqualTo("Ticket delete successfully");
    }
}
