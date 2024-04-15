package com.example.xerogroup.controllers.users;

import com.example.xerogroup.models.User;
import com.example.xerogroup.repositories.UserRepository;
import com.example.xerogroup.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.util.LangUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import static java.lang.Integer.getInteger;

@Named("userBean")
@RequestScoped
public class UserBean implements Serializable {
    private UserService userService;

    public UserBean(){
        userService = new UserService();
    }

    public List<User> getUsers(){
        return userService.vend();
    }
}
