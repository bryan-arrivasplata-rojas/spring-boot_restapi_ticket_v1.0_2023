package com.ticket.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {
    @Test
    public void testId(){
        Store store = new Store();
        store.setId(1L);
        assertEquals(1L,store.getId());
    }

    @Test
    public void testName(){
        Store store = new Store();
        store.setName("Concierto Lopez");
        assertEquals("Concierto Lopez",store.getName());
    }

    @Test
    public void testUbication(){
        Store store = new Store();
        store.setUbication("Av. las Flores");
        assertEquals("Av. las Flores",store.getUbication());
    }
    @Test
    public void testEqualsAndHash() {
        Store store1 = Store.builder().id(1L).name("St1").ubication("Ubic1").build();
        Store store2 = Store.builder().id(1L).name("St1").ubication("Ubic1").build();
        Store store3 = Store.builder().id(2L).name("St1").ubication("Ubic1").build();

        Store store4 = Store.builder().id(null).name("St1").ubication("Ubic1").build();
        Store store5 = Store.builder().id(null).name("St1").ubication("Ubic1").build();
        Store store6 = Store.builder().id(1L).name("St2").ubication("Ubic1").build();
        Store store7 = Store.builder().id(1L).name("St1").ubication("Ubic2").build();
        Store store8 = Store.builder().id(2L).name("St2").ubication("Ubic2").build();
        assertEquals(store1, store2);
        assertNotEquals(store1, store3);
        assertNotEquals(store1, store6);
        assertNotEquals(store1, store7);
        assertNotEquals(store1, store8);
        assertNotEquals(store1, null);
        Object differentObject = new Object();
        assertNotEquals(store1, differentObject);
        assertNotEquals(store1, store4);
        assertEquals(store4, store5);

        assertEquals(store1.hashCode(), store2.hashCode());
    }
}
