package com.example.xerogroup.models;

import java.util.Date;

public class Tarifa {
    private int idPlan, idAct;
    private String alta, baja, media, idTemp;
    private double costo, costoAlim;
    private Date fecIni, fecFin;
    public Tarifa(){
        alta = "Alta";
        baja = "Baja";
        media = "Media";
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getIdAct() {
        return idAct;
    }

    public void setIdAct(int idAct) {
        this.idAct = idAct;
    }

    public String getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(String idTemp) {
        this.idTemp = idTemp;
    }

    public double getCostoAlim() {
        return costoAlim;
    }

    public void setCostoAlim(double costoAlim) {
        this.costoAlim = costoAlim;
    }

    public String getAlta() {
        return alta;
    }

    public void setAlta(String alta) {
        this.alta = alta;
    }

    public String getBaja() {
        return baja;
    }

    public void setBaja(String baja) {
        this.baja = baja;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFecIni() {
        return fecIni;
    }

    public void setFecIni(Date fecIni) {
        this.fecIni = fecIni;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }
}
