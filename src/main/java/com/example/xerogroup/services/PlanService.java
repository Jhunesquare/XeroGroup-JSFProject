package com.example.xerogroup.services;

import com.example.xerogroup.models.Ciudad;
import com.example.xerogroup.models.Departamento;
import com.example.xerogroup.models.Planes;
import com.example.xerogroup.models.Tarifa;
import com.example.xerogroup.repositories.PlanRespository;

import java.util.List;

public class PlanService {
    PlanRespository planRespository;
    public PlanService() { planRespository = new PlanRespository(); }
    public List<Planes> getPlanes() { return  planRespository.planes(); }
    public Ciudad getCiudad(int id) { return planRespository.getCiudad(id); }
    public Departamento getDept() { return planRespository.getDept(); }
    public List<Ciudad> ciudList(int id) { return planRespository.ciudadList(id); }
    public List<Departamento> deptList() { return planRespository.deptList(); }
    public Tarifa getTarifa(int idPlan, String idTem) { return planRespository.getTarifa(idPlan, idTem); }
    public void savePlan(Planes planes) { planRespository.planSave(planes); }

    public void saveAct(Planes planes, int idDept, int idCiud) { planRespository.actSave(planes, idDept, idCiud); }

    public void savePlanPunto(Planes planes) { planRespository.planPunSave(planes); }

    public void saveTarifa(Planes planes, Tarifa tarifa1, Tarifa tarifa2, Tarifa tarifa3) { planRespository.tarifaSave(planes, tarifa1, tarifa2, tarifa3); }

    public void editPlan(Planes planes) { planRespository.editPlan(planes); }

    public void editAct(Planes planes, int idDept, int idCiud) { planRespository.editAct(planes, idDept, idCiud); }

    public void editTarifa(Planes planes, Tarifa tarifa, Tarifa tarifa2, Tarifa tarifa3) { planRespository.tarifaEdit(planes, tarifa, tarifa2, tarifa3); }

    public Planes getPlan(Tarifa tarifa) { return planRespository.getPlan(tarifa); }

    public String getDepartamento(Tarifa tarifa) { return planRespository.getDepartamento(tarifa); }

    public String getCiudad2(Tarifa tarifa) { return planRespository.getCiudad(tarifa); }
}
