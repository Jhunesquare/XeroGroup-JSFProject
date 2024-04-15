package com.example.xerogroup.controllers.users;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@Named("userList")
@SessionScoped
public class UserListBean implements Serializable {
    public void userList(String page) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
        handler.handleNavigation(facesContext, null, page);
    }
}
