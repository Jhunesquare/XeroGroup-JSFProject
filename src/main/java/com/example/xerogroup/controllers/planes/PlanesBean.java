package com.example.xerogroup.controllers.planes;

import com.example.xerogroup.models.Planes;
import com.example.xerogroup.services.PlanService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.primefaces.model.ResponsiveOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("planesBean")
@SessionScoped
public class PlanesBean implements Serializable {

    private List<ResponsiveOption> responsiveOptions;

    private PlanService planService;

    public PlanesBean() {
        planService = new PlanService();
        responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new ResponsiveOption("1024px", 3, 3));
        responsiveOptions.add(new ResponsiveOption("768px", 2, 2));
        responsiveOptions.add(new ResponsiveOption("560px", 1, 1));
    }

    public List<Planes> getPlanes() {
        return planService.getPlanes();
    }

    public List<ResponsiveOption> getResponsiveOptions() {
        return responsiveOptions;
    }

    public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
        this.responsiveOptions = responsiveOptions;
    }

    public String getEstado(Boolean estado) {
        if (estado){
            return "Activo";
        }else{
            return "Inactivo";
        }
    }
}
