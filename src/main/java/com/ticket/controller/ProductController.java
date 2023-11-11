package com.ticket.controller;

import com.ticket.domain.Product;
import com.ticket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService=productService;
    }
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts(){
        return productService.listProduct();
    }
    @GetMapping("/product/{id}")
    @ResponseStatus (HttpStatus.OK)
    public Optional<Product> getProduct(@PathVariable Long id){
        return productService.findProduct(id);
    }

    @PostMapping("/product")
    @ResponseStatus (HttpStatus.CREATED)
    public ResponseEntity<Product> postProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PutMapping("/product/{id}")
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<Product> putProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Product delete successfully",HttpStatus.OK);
    }
}
