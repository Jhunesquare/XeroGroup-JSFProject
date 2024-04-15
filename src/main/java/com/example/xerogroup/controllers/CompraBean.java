package com.example.xerogroup.controllers;

import com.example.xerogroup.models.Client;
import com.example.xerogroup.models.Planes;
import com.example.xerogroup.models.Tarifa;
import com.example.xerogroup.services.ClientService;
import com.example.xerogroup.services.CompraService;
import com.example.xerogroup.services.PlanService;
import jakarta.el.MethodExpression;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("compraBean")
@SessionScoped
public class CompraBean implements Serializable {

    ClientService clientService;
    PlanService planService;
    Planes plan;
    CompraService compraService;
    List<Tarifa> carrito;
    String cliente;
    List<String> cedClientes;

    public CompraBean(){
        carrito = new ArrayList<>();
        plan = new Planes();
        cedClientes = new ArrayList<>();
        clientService = new ClientService();
        planService = new PlanService();
        compraService = new CompraService();
    }

    public Planes getPlan2(Tarifa tarifa){
        return planService.getPlan(tarifa);
    }

    public String getDept(Tarifa tarifa){
        return planService.getDepartamento(tarifa);
    }

    public String getCiud(Tarifa tarifa){
        return  planService.getCiudad2(tarifa);
    }

    public List<String> getCedClientes() {
        return clientService.clientCedulas();
    }

    public List<Tarifa> getCarrito() {
        return carrito;
    }

    public void añadirCarrito(Tarifa tarifa) {
        carrito.add(tarifa);
    }

    public Planes getPlan() {
        return plan;
    }

    public void compraView(String page, Planes plan) throws IOException {
        this.plan = plan;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
        handler.handleNavigation(facesContext, null, page);
    }

    public void carritoPage(String page) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
        handler.handleNavigation(facesContext, null, page);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String deletCarrito(Tarifa tarifa){
        carrito.remove(tarifa);
        return "/templates/carrito.xhtml?faces-redirect=true";
    }

    public String comprar(Tarifa tarifa, String vendedor) {
        compraService.comprar(tarifa, cliente, vendedor);
        carrito.remove(tarifa);
        return "/templates/carrito.xhtml?faces-redirect=true";
    }
}
