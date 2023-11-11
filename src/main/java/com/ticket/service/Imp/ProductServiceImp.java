package com.ticket.service.Imp;


import com.ticket.domain.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductServiceImp {
    List<Product> listProduct();
    ResponseEntity<Product> saveProduct(Product product);
    void deleteProduct(Long id_product);
    Optional<Product> findProduct(Long id_product);
    ResponseEntity<Product> updateProduct(Long id,Product product);
}
