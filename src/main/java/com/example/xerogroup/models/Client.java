package com.example.xerogroup.models;

import java.util.Date;

public class Client {
    private String cedula, cedVend, nombre, correo, tel_perCont, vendedor, nom_cont, tel1, tel2;
    private Date  fec_nac, fec_modi, fec_crea;
    private int cantCompras;

    public int getCantCompras() {
        return cantCompras;
    }

    public void setCantCompras(int cantCompras) {
        this.cantCompras = cantCompras;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCedVend() {
        return cedVend;
    }

    public void setCedVend(String cedVend) {
        this.cedVend = cedVend;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTel_perCont() {
        return tel_perCont;
    }

    public void setTel_perCont(String tel_perCont) {
        this.tel_perCont = tel_perCont;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getNom_cont() {
        return nom_cont;
    }

    public void setNom_cont(String nom_cont) {
        this.nom_cont = nom_cont;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public Date getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(Date fec_nac) {
        this.fec_nac = fec_nac;
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
