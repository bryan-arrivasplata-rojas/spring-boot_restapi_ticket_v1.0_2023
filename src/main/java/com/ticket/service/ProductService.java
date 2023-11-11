package com.ticket.service;

import com.ticket.dao.ProductDao;
import com.ticket.domain.Client;
import com.ticket.domain.Product;
import com.ticket.domain.Store;
import com.ticket.exception.ResourceNotFoundException;
import com.ticket.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> listProduct() {
        return productDao.findAll();
    }

    @Override
    public ResponseEntity<Product> saveProduct(Product product) {
        Optional<Product> productSaved = productDao.findByName(product.getName());
        if(productSaved.isPresent()){
            throw new ResourceNotFoundException("El product con ese nombre ya existe");
        }else{
            return new ResponseEntity<>(productDao.save(product), HttpStatus.OK);
        }
    }

    @Override
    public void deleteProduct(Long id_product) {
        productDao.deleteById(id_product);
    }

    @Override
    public Optional<Product> findProduct(Long id_product) {
        return productDao.findById(id_product);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Long id, Product product) {
        return productDao.findById(id)
                .map(productSaved -> {
                    productSaved.setName(product.getName());
                    productSaved.setDescription(product.getDescription());
                    productSaved.setPrice(product.getPrice());
                    productSaved.setStore(product.getStore());

                    Product productSave = productDao.save(productSaved);
                    return new ResponseEntity<>(productSave, HttpStatus.OK);
                }).orElseGet(()->ResponseEntity.notFound().build());
    }
}