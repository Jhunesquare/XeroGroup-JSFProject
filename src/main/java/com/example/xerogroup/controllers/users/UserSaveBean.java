package com.example.xerogroup.controllers.users;

import com.example.xerogroup.models.User;
import com.example.xerogroup.repositories.UserRepository;
import com.example.xerogroup.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Named("saveBean")
@SessionScoped
public class UserSaveBean implements Serializable {

    UserService userService;
    User vendedor;


    public UserSaveBean(){
        userService = new UserService();
        vendedor = new User();
    }

    public User getVendedor() {
        return vendedor;
    }

    public void setVendedor(User vendedor) {
        this.vendedor = vendedor;
    }

    public String newVend(){
        vendedor = new User();
        return "/templates/vendedor/vendForm?faces-redirect=true";
    }


    public String saveUser() {
        Optional<User> user, user1;
        user = userService.getVend(vendedor.getCedula());
        user1 = userService.getByCorreo(vendedor.getCorreo());
        if(user.isPresent() || user1.isPresent()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cedula o correo repetido."));
            return null;
        }else{
            userService.saveVend(vendedor);
            return "/templates/vendedor/vendList.xhtml?faces-redirect=true";
        }
    }

    public String deleUser(String cedula){
        userService.deleteVend(cedula.trim());
        return "/templates/vendedor/vendList?faces-redirect=true";
    }

    public String editUser(){
        userService.editVend(vendedor);
        System.out.println("Vendedor:" + vendedor.getNombre());
        return "/templates/vendedor/vendList.xhtml?faces-redirect=true";
    }

    public String editForm(User usuario){
        setVendedor(usuario);
        return "/templates/vendedor/vendForm?faces-redirect=true";
    }
}
