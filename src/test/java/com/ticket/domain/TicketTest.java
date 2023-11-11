package com.ticket.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TicketTest {
    @Test
    public void testId() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        assertEquals(1L, ticket.getId());
    }
    @Test
    public void testClient() {
        Ticket ticket = new Ticket();
        ticket.setClient(Client.builder().id(1L).build());
        assertEquals(Client.builder().id(1L).build(),ticket.getClient());
    }
    @Test
    public void testProduct() {
        Ticket ticket = new Ticket();
        ticket.setProduct(Product.builder().id(1L).build());
        assertEquals(Product.builder().id(1L).build(),ticket.getProduct());
    }
    @Test
    public void testEqualsAndHash() {
        Store store1 = Store.builder().id(1L).build();
        Product product1 = Product.builder().id(1L).name("P1").description("D1").price(Double.parseDouble("100")).store(store1).build();
        Product product2 = Product.builder().id(2L).name("P1").description("D1").price(Double.parseDouble("100")).store(store1).build();
        Client client1 = Client.builder().id(1L).name("N1").lastname("L1").email("E1").number("N1").build();
        Client client2 = Client.builder().id(2L).name("N1").lastname("L1").email("E1").number("N1").build();
        Ticket ticket1 = Ticket.builder().id(1L).client(client1).product(product1).build();
        Ticket ticket2 = Ticket.builder().id(1L).client(client1).product(product1).build();
        Ticket ticket3 = Ticket.builder().id(2L).client(client1).product(product1).build();
        Ticket ticket4 = Ticket.builder().id(null).client(client1).product(product1).build();
        Ticket ticket5 = Ticket.builder().id(null).client(client1).product(product1).build();
        Ticket ticket6 = Ticket.builder().id(1L).client(client2).product(product1).build();
        Ticket ticket7 = Ticket.builder().id(1L).client(client1).product(product2).build();
        Ticket ticket8 = Ticket.builder().id(2L).client(client2).product(product2).build();
        assertEquals(ticket1, ticket2);
        assertNotEquals(ticket1,ticket3);
        assertNotEquals(ticket1, ticket6);
        assertNotEquals(ticket1, ticket7);
        assertNotEquals(ticket1, ticket8);
        Object differentObject = new Object();
        assertNotEquals(ticket1, differentObject);
        assertNotEquals(ticket1, ticket4);
        assertEquals(ticket4,ticket5);

        assertNotEquals(ticket1.hashCode(), ticket4.hashCode());
    }

}
