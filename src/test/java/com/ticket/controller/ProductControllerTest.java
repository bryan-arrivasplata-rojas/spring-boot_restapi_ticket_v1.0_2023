package com.ticket.controller;

import com.ticket.domain.Product;
import com.ticket.domain.Store;
import com.ticket.service.ProductService;
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

public class ProductControllerTest {
    private ProductController productController;
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @DisplayName("Test para obtener Lista de Products")
    @Test
    public void getProducts() throws Exception {
        // Given
        List<Product> mockProduct = new ArrayList<>();
        Store storeTest = Store.builder().id(1L).build();
        mockProduct.add(Product.builder().
                name("Teatral Joven")
                .description("Descripcion teatral")
                .price(Double.parseDouble("100"))
                .store(storeTest)
                .build());
        mockProduct.add(Product.builder().
                name("Teatral Menores")
                .description("Descripcion teatral para menor de 12 años")
                .price(Double.parseDouble("50"))
                .store(storeTest)
                .build());
        Mockito.when(productService.listProduct()).thenReturn(mockProduct);
        // When
        List<Product> response = productController.getProducts();

        // Then
        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(0).getName()).isEqualTo("Teatral Joven");
    }
    @DisplayName("Test para obtener Product")
    @Test
    public void getProduct() throws Exception {
        // Given
        Store storeTest = Store.builder().id(1L).build();
        Long productId = 1L; // ID del producte que deseas buscar
        Product mockProduct = Product.builder()
                .name("Teatral Joven")
                .description("Descripcion teatral")
                .price(Double.parseDouble("100"))
                .store(storeTest)
                .build();
        Mockito.when(productService.findProduct(anyLong())).thenReturn(Optional.ofNullable(mockProduct));

        // When
        Optional<Product> response = productController.getProduct(productId);

        // Then
        assertThat(response).isNotNull();
        response.ifPresent(product -> assertThat(product.getName()).isEqualTo("Teatral Joven"));
    }
    @DisplayName("Test para guardar Product")
    @Test
    public void postProduct() throws Exception {
        // Given
        Store storeTest = Store.builder().id(1L).build();
        Product newProduct = Product.builder()
                .name("Teatral Joven")
                .description("Descripcion teatral")
                .price(Double.parseDouble("100"))
                .store(storeTest)
                .build();
        Product savedProduct = Product.builder()
                .id(1L)
                .name(newProduct.getName())
                .description(newProduct.getDescription())
                .price(newProduct.getPrice())
                .store(newProduct.getStore())
                .build();
        ResponseEntity<Product> responseEntity = ResponseEntity.ok(savedProduct);
        Mockito.when(productService.saveProduct(any(Product.class))).thenReturn(responseEntity);

        // When
        ResponseEntity<Product> response = productController.postProduct(newProduct);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1L);
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Teatral Joven");
    }
    @DisplayName("Test para actualizar Product")
    @Test
    public void putProduct() throws Exception {
        // Given
        Store storeTest = Store.builder().id(1L).build();
        Long productId = 1L; // ID del producte que deseas actualizar
        Product updatedProduct = Product.builder()
                .id(productId)
                .name("Teatral Joven")
                .description("Descripcion teatral")
                .price(Double.parseDouble("100"))
                .store(storeTest)
                .build();
        ResponseEntity<Product> responseEntity = ResponseEntity.ok(updatedProduct);
        Mockito.when(productService.updateProduct(any(Long.class), any(Product.class))).thenReturn(responseEntity);

        // When
        ResponseEntity<Product> response = productController.putProduct(productId, updatedProduct);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(productId);
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Teatral Joven");
    }

    @DisplayName("Test para eliminar Product")
    @Test
    public void deleteProduct() throws Exception {
        // Given
        Long productId = 1L;
        doNothing().when(productService).deleteProduct(productId);

        // Then
        ResponseEntity<String> response = productController.deleteProduct(productId);

        // When
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(productService).deleteProduct(productId);
        assertThat(response.getBody()).isEqualTo("Product delete successfully");
    }
}
