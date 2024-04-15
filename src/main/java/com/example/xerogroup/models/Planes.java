package com.example.xerogroup.models;

import java.util.Date;

public class Planes {
    private int id, cantDias, id_act, cantPer, id_dept, id_ciud;
    private String titulo, desc, alimentacion, url, dept, ciudad;
    private Boolean estado;
    private Date fec_modi, fec_crea;
    private double costoAlim;

    public double getCostoAlim() {
        return costoAlim;
    }

    public void setCostoAlim(double costoAlim) {
        this.costoAlim = costoAlim;
    }

    public int getCantPer() {
        return cantPer;
    }

    public void setCantPer(int cantPer) {
        this.cantPer = cantPer;
    }

    public int getId_act() {
        return id_act;
    }

    public void setId_act(int id_act) {
        this.id_act = id_act;
    }

    public int getId_dept() {
        return id_dept;
    }

    public void setId_dept(int id_dept) {
        this.id_dept = id_dept;
    }

    public int getId_ciud() {
        return id_ciud;
    }

    public void setId_ciud(int id_ciud) {
        this.id_ciud = id_ciud;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantDias() {
        return cantDias;
    }

    public void setCantDias(int cantDias) {
        this.cantDias = cantDias;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(String alimentacion) {
        this.alimentacion = alimentacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFec_modi() {
        return fec_modi;
    }

    public void setFec_modi(Date fec_modi) {
        this.fec_modi = fec_modi;
    }

    public Date getFec_crea() {
        return fec_crea;
    }

    public void setFec_crea(Date fec_crea) {
        this.fec_crea = fec_crea;
    }
}
