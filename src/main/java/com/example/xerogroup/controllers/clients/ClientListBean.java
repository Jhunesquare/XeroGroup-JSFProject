package com.example.xerogroup.controllers.clients;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jdk.jfr.Name;

import java.io.IOException;
import java.io.Serializable;

@Named("clientList")
@SessionScoped
public class ClientListBean implements Serializable {
    public void clientList(String page) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
        handler.handleNavigation(facesContext, null, page);
    }
}
