package com.example.xerogroup.controllers.planes;

import com.example.xerogroup.models.*;
import com.example.xerogroup.services.PlanService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("planesSave")
@SessionScoped
public class PlanesSaveBean implements Serializable {
    Ciudad ciudad;
    Planes planes;
    PlanService planService;
    Tarifa tarifa, tarifa2, tarifa3, tarifa4;
    int idDept, idCiud;
    public PlanesSaveBean(){
        planes = new Planes();
        ciudad = new Ciudad();
        planService = new PlanService();
        tarifa = new Tarifa();
        tarifa.setAlta("Alta");
        tarifa.setBaja("Baja");
        tarifa.setMedia("Media");
        tarifa = new Tarifa();
        tarifa3 = new Tarifa();
        tarifa4 = new Tarifa();
    }

    public Tarifa getTarifa3() {
        return tarifa3;
    }

    public void setTarifa3(Tarifa tarifa3) {
        this.tarifa3 = tarifa3;
    }

    public Tarifa getTarifa4() {
        return tarifa4;
    }

    public void setTarifa4(Tarifa tarifa4) {
        this.tarifa4 = tarifa4;
    }

    public Tarifa getTarifa2(int id, String temp) {
        return planService.getTarifa(id, temp);
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public void setIdDept(int idDept){
        this.idDept = idDept;
    }

    public int getIdDept() {
        return idDept;
    }

    public int getIdCiud() {
        return idCiud;
    }

    public void setIdCiud(int idCiud) {
        this.idCiud = idCiud;
    }

    public void cargarCiudad() {
        ciudad = planService.getCiudad(idDept);
    }

    public Ciudad getCiudad(){
        return ciudad;
    }
    public Departamento getDept(){
        return planService.getDept();
    }
    public List<Ciudad> getCiudadList(){
        return planService.ciudList(idDept);
    }
    public List<Departamento> getDeptList(){
        return planService.deptList();
    }

    public Planes getPlanes() {
        return planes;
    }

    public void setPlanes(Planes planes) {
        this.planes = planes;
    }

    public String saveFrom (){
        tarifa = new Tarifa();
        tarifa3 = new Tarifa();
        tarifa4 = new Tarifa();
        idDept = idCiud = 0;
        planes = new Planes();
        return "/templates/planes/planesForm?faces-redirect=true";
    }
    public String editForm(Planes plan){
        setIdCiud(plan.getId_ciud());
        setIdDept(plan.getId_dept());
        setPlanes(plan);
        if(getTarifa2(plan.getId(), "Alta") != null){
            setTarifa(getTarifa2(plan.getId(), "Alta"));
        }
        if(getTarifa2(plan.getId(), "Media") != null){
            setTarifa3(getTarifa2(plan.getId(), "Media"));
        }
        if(getTarifa2(plan.getId(), "Baja") != null){
            setTarifa4(getTarifa2(plan.getId(), "Baja"));
        }
        return "/templates/planes/planesForm.xhtml?faces-redirect=true";
    }
    public String guardarPlan(){
        planService.savePlan(planes);
        planService.saveAct(planes, idDept, idCiud);
        planService.savePlanPunto(planes);
        planService.saveTarifa(planes, tarifa, tarifa3, tarifa4);
        return "/templates/planes/planesCards?faces-redirect=true";
    }
    public String editPlan(){
        planService.editPlan(planes);
        planService.editAct(planes, idDept, idCiud);
        planService.editTarifa(planes, tarifa, tarifa3, tarifa4);
        return "/templates/planes/planesCards?faces-redirect=true";
    }
}
