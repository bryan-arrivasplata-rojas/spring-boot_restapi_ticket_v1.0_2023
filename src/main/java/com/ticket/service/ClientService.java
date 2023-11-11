package com.ticket.service;

import com.ticket.dao.ClientDao;
import com.ticket.domain.Client;
import com.ticket.exception.ResourceNotFoundException;
import com.ticket.service.Imp.ClientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ClientServiceImp {

    @Autowired
    private ClientDao clientDao;
    @Override
    public List<Client> listClient() {
        return clientDao.findAll();
    }

    @Override
    public ResponseEntity<Client> saveClient(Client client) {
        Optional<Client> clientSaved = clientDao.findByNameAndLastname(client.getName(),client.getLastname());
        if(clientSaved.isPresent()){
            throw new ResourceNotFoundException("El client con ese nombre y apellido ya existe");
        }
        return new ResponseEntity<>(clientDao.save(client), HttpStatus.OK);
    }

    @Override
    public void deleteClient(Long id_client) {
        clientDao.deleteById(id_client);
    }

    @Override
    public Optional<Client> findClient(Long id_client) {
        return clientDao.findById(id_client);
    }

    @Override
    public ResponseEntity<Client> updateClient(Long id, Client client) {
        Optional<Client> clientToUpdate = clientDao.findById(id);

        Client existingClient = clientToUpdate.get();

        existingClient.setName(client.getName());
        existingClient.setLastname(client.getLastname());
        existingClient.setEmail(client.getEmail());
        existingClient.setNumber(client.getNumber());

        Client updatedClient = clientDao.save(existingClient);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }
}
