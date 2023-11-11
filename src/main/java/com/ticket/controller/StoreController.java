package com.ticket.controller;

import com.ticket.domain.Store;
import com.ticket.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService=storeService;
    }
    @GetMapping("/stores")
    @ResponseStatus(HttpStatus.OK)
    public List<Store> getStores(){
        return storeService.listStore();
    }

    @GetMapping("/store/{id}")
    @ResponseStatus (HttpStatus.OK)
    public Optional<Store> getStore(@PathVariable Long id){
        return storeService.findStore(id);
    }

    @PostMapping("/store")
    @ResponseStatus (HttpStatus.CREATED)
    public ResponseEntity<Store> postStore(@RequestBody Store store){
        return storeService.saveStore(store);
    }

    @PutMapping("/store/{id}")
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<Store> putStore(@PathVariable Long id, @RequestBody Store store){
        return storeService.updateStore(id,store);
    }

    @DeleteMapping("/store/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable Long id){
        storeService.deleteStore(id);
        return new ResponseEntity<String>("Store delete successfully",HttpStatus.OK);
    }
}
