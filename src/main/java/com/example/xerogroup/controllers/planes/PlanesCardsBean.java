package com.example.xerogroup.controllers.planes;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@Named("planesCards")
@SessionScoped
public class PlanesCardsBean implements Serializable {
    public void planesCards(String page) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
        handler.handleNavigation(facesContext, null, page);
    }
}
