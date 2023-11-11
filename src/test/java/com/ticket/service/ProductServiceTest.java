package com.ticket.service;

import com.ticket.domain.Product;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Autowired
    StoreService storeService;

    @DisplayName("Test para Listar Productos")
    @Test
    @Transactional
    void listProducts(){
        //given
        Store store1 = Store.builder()
                .name("Store Name Save")
                .ubication("Store Ubication").
                build();

        ResponseEntity<Store> storesaved = storeService.saveStore(store1);


        Product product = Product.builder()
                .name("Product Name")
                .description("Product Description")
                .price(Double.parseDouble("100"))
                .store(storesaved.getBody()).
                build();
        Product product1 = Product.builder()
                .name("Product Name2")
                .description("Product Description")
                .price(Double.parseDouble("100"))
                .store(storesaved.getBody()).
                build();

        //When
        productService.saveProduct(product);
        productService.saveProduct(product1);
        //Then
        List<Product> listProducts = productService.listProduct();
        Assertions.assertThat(listProducts).isNotNull();
        Assertions.assertThat(listProducts.size()).isEqualTo(2);
    }
    @DisplayName("Test para guardar product")
    @Test
    @Transactional
    void saveProduct(){
        //given
        Store store1 = Store.builder()
                .name("Store Name Save")
                .ubication("Store Ubication").
                build();
        ResponseEntity<Store> storesaved = storeService.saveStore(store1);
        Product product1 = Product.builder()
                .name("Prod Name Save")
                .description("Prod Description Save")
                .price(Double.parseDouble("50"))
                .store(storesaved.getBody()).
                build();
        //When
        ResponseEntity<Product> productSav = productService.saveProduct(product1);
        //Then
        Assertions.assertThat(productSav).isNotNull();
        Assertions.assertThat(productSav.getBody().getId()).isGreaterThan(0);
    }
    @DisplayName("Test para pruducto duplicado al guardar product")
    @Test
    @Transactional
    void saveProductDuplicate(){
        //given
        Store store1 = Store.builder()
                .name("Store Name Save")
                .ubication("Store Ubication").
                build();
        ResponseEntity<Store> storesaved = storeService.saveStore(store1);
        Product product1 = Product.builder()
                .name("Prod Name Find")
                .description("Prod Description Find")
                .price(Double.parseDouble("50"))
                .store(storesaved.getBody()).
                build();
        //When
        try{
            productService.saveProduct(product1);
            productService.saveProduct(product1);
        }catch (ResourceNotFoundException e) {
            //Then
            Assertions.assertThat(e.getMessage()).isEqualTo("El product con ese nombre ya existe"); // Verifica el mensaje de la excepción
        }
    }
    @DisplayName("Test para eliminar producto")
    @Test
    @Transactional
    void deleteProduct(){
        //given
        Store store1 = Store.builder()
                .name("Store Name Save")
                .ubication("Store Ubication").
                build();
        Product product1 = Product.builder()
                .name("Prod Name Delete")
                .description("Prod Description Delete")
                .price(Double.parseDouble("50"))
                .store(store1).
                build();
        productService.saveProduct(product1);
        //When
        productService.deleteProduct(product1.getId());
        Optional<Product> clientOptional = productService.findProduct(product1.getId());
        //Then
        Assertions.assertThat(clientOptional).isEmpty();
    }
    @DisplayName("Test para encontrar producto")
    @Test
    @Transactional
    void findProduct(){
        //given
        Store store1 = Store.builder()
                .name("Store Name Save")
                .ubication("Store Ubication").
                build();
        Product product1 = Product.builder()
                .name("Prod Name Delete")
                .description("Prod Description Delete")
                .price(Double.parseDouble("50"))
                .store(store1).
                build();
        productService.saveProduct(product1);
        //When
        Optional<Product> clientOptional = productService.findProduct(product1.getId());
        //Then
        Assertions.assertThat(clientOptional).isNotEmpty();
    }
    @DisplayName("Test para actualizar product")
    @Test
    @Transactional
    void updateProduct() {
        // given
        Store store1 = Store.builder()
                .name("Store Name Save")
                .ubication("Store Ubication")
                .build();

        Product product1 = Product.builder()
                .name("Prod Name")  // Cambiado el nombre para la prueba de actualización
                .description("Prod Description")  // Cambiado la descripción para la prueba de actualización
                .price(Double.parseDouble("50"))
                .store(store1)
                .build();

        productService.saveProduct(product1);

        // when
        Product productToUpdate = productService.findProduct(product1.getId()).orElseThrow();
        productToUpdate.setName("Prod Name Updated");
        productToUpdate.setDescription("Prod Description Updated");
        productToUpdate.setPrice(Double.parseDouble("100"));
        productToUpdate.setStore(store1);

        ResponseEntity<Product> productUpdated = productService.updateProduct(product1.getId(), productToUpdate);

        // then
        Product updatedProductBody = productUpdated.getBody();
        if (updatedProductBody != null) {
            Assertions.assertThat(updatedProductBody.getName()).isEqualTo("Prod Name Updated");
            Assertions.assertThat(updatedProductBody.getDescription()).isEqualTo("Prod Description Updated");
            Assertions.assertThat(updatedProductBody.getPrice()).isEqualTo(Double.parseDouble("100"));
            Assertions.assertThat(updatedProductBody.getStore()).isEqualTo(store1);
        }
    }
}
