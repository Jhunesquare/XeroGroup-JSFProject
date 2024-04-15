package com.example.xerogroup.controllers.clients;

import com.example.xerogroup.models.Client;
import com.example.xerogroup.services.ClientService;
import com.example.xerogroup.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("clientBean")
@RequestScoped
public class ClientBean implements Serializable {
    ClientService clientService;

    public ClientBean(){
        clientService = new ClientService();
    }

    public List<Client> getClientes(){
        return clientService.getClients();
    }
}
