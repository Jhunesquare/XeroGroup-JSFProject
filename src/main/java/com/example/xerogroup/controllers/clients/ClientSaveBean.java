package com.example.xerogroup.controllers.clients;

import com.example.xerogroup.controllers.LoginBean;
import com.example.xerogroup.models.Client;
import com.example.xerogroup.models.User;
import com.example.xerogroup.repositories.ClientRepository;
import com.example.xerogroup.services.ClientService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Optional;

@Named("clientSave")
@SessionScoped
public class ClientSaveBean implements Serializable {
    ClientService clientService;
    Client cliente;

    public ClientSaveBean(){
        clientService = new ClientService();
        cliente = new Client();
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public String newClient(){
        cliente = new Client();
        return "/templates/cliente/clientForm?faces-redirect=true";
    }

    public String saveClient(User vendedor) {
        Optional<Client> user, user1;
        user = clientService.getById(cliente.getCedula());
        user1 = clientService.getByCorreo(cliente.getCorreo());
        if(user.isPresent() || user1.isPresent()){
            System.out.println(vendedor.getCedula() + " - " + vendedor.getNombre());
            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cedula o correo repetido"));
            return null;
        }else{
            clientService.saveClient(cliente, vendedor);
            return "/templates/cliente/clientList?faces-redirect=true";
        }
    }

    public String deleClient(String cedula){
        clientService.deleteClient(cedula.trim());
        return "/templates/cliente/clientList.xhtml?faces-redirect=true";
    }

    public String editClient(){
        clientService.editClient(cliente);
        return "/templates/cliente/clientList.xhtml?faces-redirect=true";
    }

    public String editForm(Client cliente){
        setCliente(cliente);
        return "/templates/cliente/clientForm?faces-redirect=true";
    }
}
