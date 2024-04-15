package com.example.xerogroup.services;

import com.example.xerogroup.models.Client;
import com.example.xerogroup.models.User;
import com.example.xerogroup.repositories.ClientRepository;
import com.example.xerogroup.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class ClientService {
    ClientRepository clientRepository;

    public ClientService(){
        clientRepository = new ClientRepository();
    }

    public List<Client> getClients(){
        return clientRepository.clients();
    }

    public Optional<Client> getById(String cedula) { return clientRepository.getById(cedula); }

    public Optional<Client> getByCorreo(String correo) { return clientRepository.getByUsername(correo); }

    public void saveClient(Client cliente, User vendedor) { clientRepository.saveClient(cliente, vendedor); }

    public void editClient(Client cliente) { clientRepository.editUser(cliente);}

    public void deleteClient(String cedula) { clientRepository.deleteClient(cedula);}

    public List<String> clientCedulas() { return clientRepository.clientCed(); }
}
