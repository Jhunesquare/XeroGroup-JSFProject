package com.example.xerogroup.controllers;

import com.example.xerogroup.controllers.clients.ClientSaveBean;
import com.example.xerogroup.models.User;
import com.example.xerogroup.repositories.UserRepository;
import com.example.xerogroup.services.UserService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    UserService userService;
    User usuario, vendedor;

    public LoginBean(){
        userService = new UserService();
        usuario = new User();
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public User getVendedor() { return vendedor;}

    public Boolean getRol() {
        if(vendedor.getRol().trim().equals("admin")){
            return true;
        }else {
            return false;
        }
    }
    public void validate() throws IOException {
        Optional<User> user;
        user = userService.getByUsername(usuario.getCorreo(), usuario.getPassword());
        vendedor = userService.vendedor(usuario.getCorreo());
        if(user.isPresent()){
            FacesContext.getCurrentInstance().getExternalContext().redirect("templates/planes/planesCards.faces");
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales incorrectas", "Por favor, inténtelo de nuevo."));
        }
    }
    public void logout(String page) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
        handler.handleNavigation(facesContext, null, page);
        usuario = new User();
    }
}
