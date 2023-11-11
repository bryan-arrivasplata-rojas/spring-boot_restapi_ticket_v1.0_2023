package com.ticket.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductTest {
    @Test
    public void testId(){
        Product product = new Product();
        product.setId(1L);
        assertEquals(1L,product.getId());
    }
    @Test
    public void testName(){
        Product product = new Product();
        product.setName("Concierto la Paz");
        assertEquals("Concierto la Paz",product.getName());
    }
    @Test
    public void testDescription(){
        Product product = new Product();
        product.setDescription("El concierto mas grande de la Paz");
        assertEquals("El concierto mas grande de la Paz",product.getDescription());
    }
    @Test
    public void testPrice(){
        Product product = new Product();
        product.setPrice(Double.parseDouble("100"));
        assertEquals(Double.parseDouble("100"),product.getPrice());
    }
    @Test
    public void testStore(){
        Product product = new Product();
        product.setStore(Store.builder().id(1L).build());
        assertEquals(Store.builder().id(1L).build(),product.getStore());
    }
    @Test
    public void testEqualsAndHash() {
        Store store1 = Store.builder().id(1L).build();
        Store store2 = Store.builder().id(2L).build();
        Product product1 = Product.builder().id(1L).name("St1").description("Desc1").price(Double.parseDouble("100")).store(store1).build();
        Product product2 = Product.builder().id(1L).name("St1").description("Desc1").price(Double.parseDouble("100")).store(store1).build();
        Product product3 = Product.builder().id(2L).name("St1").description("Desc1").price(Double.parseDouble("100")).store(store1).build();
        Product product4 = Product.builder().id(null).name("St1").description("Desc1").price(Double.parseDouble("100")).store(store1).build();
        Product product5 = Product.builder().id(null).name("St1").description("Desc1").price(Double.parseDouble("100")).store(store1).build();
        Product product6 = Product.builder().id(1L).name("St2").description("Desc1").price(Double.parseDouble("100")).store(store1).build();
        Product product7 = Product.builder().id(1L).name("St1").description("Desc1").price(Double.parseDouble("105")).store(store2).build();
        Product product8 = Product.builder().id(2L).name("St2").description("Desc1").price(Double.parseDouble("105")).store(store2).build();
        assertEquals(product1, product2);
        assertNotEquals(product1,product3);
        assertNotEquals(product1, product6);
        assertNotEquals(product1, product7);
        assertNotEquals(product1, product8);
        Object differentObject = new Object();
        assertNotEquals(product1, differentObject);
        assertNotEquals(product1, product4);
        assertEquals(product4,product5);

        assertNotEquals(product1.hashCode(), product4.hashCode());
    }
}
