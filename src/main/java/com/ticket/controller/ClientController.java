package com.ticket.controller;

import com.ticket.domain.Client;
import com.ticket.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClients(){
        return clientService.listClient();
    }
    @GetMapping("/client/{id}")
    @ResponseStatus (HttpStatus.OK)
    public Optional<Client> getClient(@PathVariable Long id){
        return clientService.findClient(id);
    }

    @PostMapping("/client")
    @ResponseStatus (HttpStatus.CREATED)
    public ResponseEntity<Client> postClient(@RequestBody Client client){
        return clientService.saveClient(client);
    }

    @PutMapping("/client/{id}")
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<Client> putClient(@PathVariable Long id, @RequestBody Client client){
        return clientService.updateClient(id,client);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return new ResponseEntity<String>("Client delete successfully",HttpStatus.OK);
    }
}
