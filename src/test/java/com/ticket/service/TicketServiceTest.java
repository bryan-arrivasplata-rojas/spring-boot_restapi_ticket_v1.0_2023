package com.ticket.service;

import com.ticket.domain.Client;
import com.ticket.domain.Product;
import com.ticket.domain.Ticket;
import com.ticket.domain.Store;
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
@Transactional
public class TicketServiceTest {
    @Autowired
    TicketService ticketService;

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;

    @DisplayName("Test para Listar tickets")
    @Test
    void listTickets(){
        //given
        Product product1 = Product.builder()
                .name("Store Name Save")
                .description("Store Ubication")
                .price(Double.parseDouble("20")).
                build();
        Client client1 = Client.builder()
                .name("Store Name Save")
                .lastname("Store Ubication")
                .email("client@gmail.com")
                .number("+51 997767771").
                build();

        ResponseEntity<Product> productsaved = productService.saveProduct(product1);
        ResponseEntity<Client> clientsaved = clientService.saveClient(client1);

        Ticket ticket = Ticket.builder()
                .product(product1)
                .client(client1).
                build();
        Ticket ticket1 = Ticket.builder()
                .product(product1)
                .client(client1).
                build();

        //When
        ticketService.saveTicket(ticket);
        ticketService.saveTicket(ticket1);
        //Then
        List<Ticket> listTickets = ticketService.listTicket();
        Assertions.assertThat(listTickets).isNotNull();
        Assertions.assertThat(listTickets.size()).isEqualTo(2);
    }
    @DisplayName("Test para guardar ticket")
    @Test
    void saveTicket(){
        //given
        Product product1 = Product.builder()
                .name("Store Name Save")
                .description("Store Ubication")
                .price(Double.parseDouble("20")).
                build();
        Client client1 = Client.builder()
                .name("Store Name Save")
                .lastname("Store Ubication")
                .email("client@gmail.com")
                .number("+51 997767771").
                build();
        Ticket ticket = Ticket.builder()
                .product(product1)
                .client(client1).
                build();
        //When
        ResponseEntity<Ticket> ticketSav = ticketService.saveTicket(ticket);
        //Then
        Assertions.assertThat(ticketSav).isNotNull();
        Assertions.assertThat(ticketSav.getBody().getId()).isGreaterThan(0);
    }
    @DisplayName("Test para eliminar ticket")
    @Test
    void deleteTicket(){
        //given
        Product product1 = Product.builder()
                .name("Store Name Save")
                .description("Store Ubication")
                .price(Double.parseDouble("20")).
                build();
        Client client1 = Client.builder()
                .name("Store Name Save")
                .lastname("Store Ubication")
                .email("client@gmail.com")
                .number("+51 997767771").
                build();
        Ticket ticket = Ticket.builder()
                .product(product1)
                .client(client1).
                build();
        ticketService.saveTicket(ticket);
        //When
        ticketService.deleteTicket(ticket.getId());
        Optional<Ticket> ticketOptional = ticketService.findTicket(ticket.getId());
        //Then
        Assertions.assertThat(ticketOptional).isEmpty();
    }
    @DisplayName("Test para encontrar ticket")
    @Test
    void findTicket(){
        //given
        Product product1 = Product.builder()
                .name("Store Name Save")
                .description("Store Ubication")
                .price(Double.parseDouble("20")).
                build();
        Client client1 = Client.builder()
                .name("Store Name Save")
                .lastname("Store Ubication")
                .email("client@gmail.com")
                .number("+51 997767771").
                build();
        Ticket ticket = Ticket.builder()
                .product(product1)
                .client(client1).
                build();
        ticketService.saveTicket(ticket);
        //When
        Optional<Ticket> clientOptional = ticketService.findTicket(ticket.getId());
        //Then
        Assertions.assertThat(clientOptional).isNotEmpty();
    }
    @DisplayName("Test para actualizar ticket")
    @Test
    void updateTicket() {
        //given
        Product product1 = Product.builder()
                .name("Store Name Save")
                .description("Store Ubication")
                .price(Double.parseDouble("20")).
                build();
        Client client1 = Client.builder()
                .name("Store Name Save")
                .lastname("Store Ubication")
                .email("client@gmail.com")
                .number("+51 997767771").
                build();
        Ticket ticket = Ticket.builder()
                .product(product1)
                .client(client1).
                build();

        ticketService.saveTicket(ticket);

        // when
        Ticket ticketToUpdate = ticketService.findTicket(ticket.getId()).orElseThrow();
        ticketToUpdate.setClient(client1);
        ticketToUpdate.setProduct(product1);

        ResponseEntity<Ticket> ticketUpdated = ticketService.updateTicket(ticket.getId(), ticketToUpdate);

        // then
        Ticket updatedTicketBody = ticketUpdated.getBody();
        if (updatedTicketBody != null) {
            Assertions.assertThat(updatedTicketBody.getProduct()).isEqualTo(product1);
            Assertions.assertThat(updatedTicketBody.getClient()).isEqualTo(client1);
        }
    }
}
