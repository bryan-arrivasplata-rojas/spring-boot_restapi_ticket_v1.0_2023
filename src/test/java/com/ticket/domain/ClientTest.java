package com.ticket.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClientTest {
    @Test
    public void testId() {
        Client client = new Client();
        client.setId(1L);
        assertEquals(1L, client.getId());
    }

    @Test
    public void testName() {
        Client client = new Client();
        client.setName("Bryan Daniell");
        assertEquals("Bryan Daniell", client.getName());
    }

    @Test
    public void testLastname() {
        Client client = new Client();
        client.setLastname("Arrivasplata Rojas");
        assertEquals("Arrivasplata Rojas", client.getLastname());
    }

    @Test
    public void testEmail() {
        Client client = new Client();
        client.setEmail("bryanarrivasplata.rojas@gmail.com");
        assertEquals("bryanarrivasplata.rojas@gmail.com", client.getEmail());
    }

    @Test
    public void testNumber() {
        Client client = new Client();
        client.setNumber("+51 997767771");
        assertEquals("+51 997767771", client.getNumber());
    }
    @Test
    public void testEqualsAndHash() {
        Client client1 = Client.builder().id(1L).name("St1").lastname("Arrivasplata Rojas").email("bryanarrivasplata.rojas@gmail.com").number("+51 997767771").build();
        Client client2 = Client.builder().id(1L).name("St1").lastname("Arrivasplata Rojas").email("bryanarrivasplata.rojas@gmail.com").number("+51 997767771").build();
        Client client3 = Client.builder().id(2L).name("St2").lastname("Arrivasplata Rojas").email("bryanarrivasplata.rojas@gmail.com").number("+51 997767771").build();
        Client client4 = Client.builder().id(null).name("St1").lastname("Arrivasplata Rojas").email("bryanarrivasplata.rojas@gmail.com").number("+51 997767771").build();
        Client client5 = Client.builder().id(null).name("St1").lastname("Arrivasplata Rojas").email("bryanarrivasplata.rojas@gmail.com").number("+51 997767771").build();
        Client client6 = Client.builder().id(1L).name("St2").lastname("Arrivasplata Rojas").email("bryanarrivasplata.rojas@gmail.com").number("+51 997767771").build();
        Client client7 = Client.builder().id(1L).name("St1").lastname("Arrivasplata Rojas2").email("bryanarrivasplata.rojas@gmail.com2").number("+51 9977677712").build();
        Client client8 = Client.builder().id(2L).name("St2").lastname("Arrivasplata Rojas2").email("bryanarrivasplata.rojas@gmail.com2").number("+51 9977677712").build();
        assertEquals(client1, client2);
        assertNotEquals(client1, client3);
        assertNotEquals(client1, client6);
        assertNotEquals(client1, client7);
        assertNotEquals(client1, client8);
        assertNotEquals(client1, null);
        Object differentObject = new Object();
        assertNotEquals(client1, differentObject);
        assertNotEquals(client1, client4);
        assertEquals(client4, client5);

        assertEquals(client1.hashCode(), client2.hashCode());
    }
}
