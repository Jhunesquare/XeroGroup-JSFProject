package com.example.xerogroup.repositories;

import com.example.xerogroup.models.*;
import com.example.xerogroup.utils.ConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanRespository {
    public List<Planes> planes() {
        List<Planes> planes = new ArrayList<>();
        String query = "SELECT \n" +
                "pt.id AS IdPlanTuristico,\n" +
                "pt.titulo AS TituloPlanTuristico,\n" +
                "pt.descri AS DescripcionPlanTuristico,\n" +
                "pt.cantDias AS CantidadDias,\n" +
                "pt.estado AS EstadoPlanTuristico,\n" +
                "pt.alimen AS Alimentacion,\n" +
                "pt.url as Imagen,\n" +
                "pt.fec_modi as fec_modi,\n" +
                "pt.fec_creacion as fec_creacion,\n" +
                "pt.cantPer AS cantPer,\n" +
                "pt.costAlim AS costAlim,\n" +
                "pp.id AS IdPlanPunto,\n" +
                "a.id AS IdActividad,\n" +
                "a.id_dept AS IdDepartamentoActividad,\n" +
                "a.id_ciud AS IdCiudadActividad,\n" +
                "d.departamento AS NombreDepartamento,\n" +
                "c.ciudad AS NombreCiudad\n" +
                "FROM planturistico pt\n" +
                "JOIN planpunto pp ON pt.id = pp.id_plan\n" +
                "JOIN actividad a ON pp.id_act = a.id\n" +
                "JOIN ciudades c ON a.id_ciud = c.id\n" +
                "JOIN departamentos d ON d.id = a.id_dept;";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    planes.add(getPlanes(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return planes;
    }

    private Planes getPlanes(ResultSet rs) throws SQLException {
        Planes planes = new Planes();
        planes.setId(rs.getInt("IdPlanTuristico"));
        planes.setTitulo(rs.getString("TituloPlanTuristico"));
        planes.setDesc(rs.getString("DescripcionPlanTuristico"));
        planes.setCantDias(rs.getInt("CantidadDias"));
        planes.setEstado(rs.getBoolean("EstadoPlanTuristico"));
        planes.setAlimentacion(rs.getString("Alimentacion"));
        planes.setUrl(rs.getString("Imagen"));
        planes.setFec_modi(rs.getDate("fec_modi"));
        planes.setFec_crea(rs.getDate("fec_creacion"));
        planes.setId_act(rs.getInt("IdActividad"));
        planes.setDept(rs.getString("NombreDepartamento"));
        planes.setCiudad(rs.getString("NombreCiudad"));
        planes.setCantPer(rs.getInt("cantPer"));
        planes.setId_ciud(rs.getInt("IdCiudadActividad"));
        planes.setId_dept(rs.getInt("IdDepartamentoActividad"));
        planes.setCostoAlim(rs.getDouble("costAlim"));
        return planes;
    }

    public List<Ciudad> ciudadList(int id) {
        List<Ciudad> ciudad = new ArrayList<>();
        String query = "SELECT * FROM ciudades where departamento_id = " + id;
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    ciudad.add(getCiudades(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ciudad;
    }

    private Ciudad getCiudades(ResultSet rs) throws SQLException {
        Ciudad ciudad = new Ciudad();
        ciudad.setCiudad(rs.getString("ciudad"));
        ciudad.setIdDept(rs.getInt("departamento_id"));
        ciudad.setIdCiud(rs.getInt("id"));
        return ciudad;
    }

    public List<Departamento> deptList() {
        List<Departamento> departamentos = new ArrayList<>();
        String query = "SELECT * FROM departamentos";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    departamentos.add(getDepartamentos(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departamentos;
    }

    private Departamento getDepartamentos(ResultSet rs) throws SQLException {
        Departamento departamentos = new Departamento();
        departamentos.setDepartamento(rs.getString("departamento"));
        departamentos.setId(rs.getInt("id"));
        return departamentos;
    }

    public Ciudad getCiudad(int idDept) {
        Ciudad ciudad = null;
        String query = "SELECT * FROM ciudades where departamento_id = " + idDept;
        try (Connection conn = ConnectionDatabase.getConnection();
             Statement stmt = conn.createStatement()){
            try(ResultSet rs = stmt.executeQuery(query)){
                if(rs.next()){
                    ciudad = new Ciudad();
                    ciudad.setCiudad(rs.getString("ciudad"));
                    ciudad.setIdCiud(rs.getInt("id"));
                    ciudad.setIdDept(rs.getInt("departamento_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ciudad;
    }

    public Departamento getDept() {
        Departamento dept = null;
        String query = "SELECT * FROM departamentos";
        try (Connection conn = ConnectionDatabase.getConnection();
             Statement stmt = conn.createStatement()){
            try(ResultSet rs = stmt.executeQuery(query)){
                if(rs.next()){
                    dept = new Departamento();
                    dept.setDepartamento(rs.getString("departamento"));
                    dept.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dept;
    }

    public Tarifa getTarifa(int idPlan, String idTem) {
        Tarifa tarifa = null;
        String query = "SELECT * FROM tarifa WHERE id_planTuris = ? AND id_temp = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPlan);
            stmt.setString(2, idTem);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    tarifa = new Tarifa();
                    tarifa.setIdPlan(rs.getInt("id_planTuris"));
                    tarifa.setIdAct(rs.getInt("id_act"));
                    tarifa.setCostoAlim(rs.getDouble("otros"));
                    tarifa.setCosto(rs.getDouble("costo"));
                    tarifa.setIdTemp(rs.getString("id_temp"));
                    tarifa.setCostoAlim(rs.getDouble("otros"));
                    tarifa.setFecIni(rs.getDate("fec_ini"));
                    tarifa.setFecFin(rs.getDate("fec_fin"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tarifa;
    }

    public void planSave(Planes planes) {
        String query = "INSERT INTO planTuristico (titulo, descri, cantDias, estado, alimen, cantPer, url, costAlim) values (?,?,?,?,?,?,?,?)";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, planes.getTitulo());
            stmt.setString(2, planes.getDesc());
            stmt.setInt(3, planes.getCantDias());
            stmt.setBoolean(4, planes.getEstado());
            stmt.setString(5, planes.getAlimentacion());
            stmt.setInt(6, planes.getCantPer());
            stmt.setString(7, planes.getUrl());
            stmt.setDouble(8, planes.getCostoAlim());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actSave(Planes planes, int idDept, int idCiud) {
        String query = "INSERT INTO actividad (nombre, descri, estado, id_dept, id_ciud) values (?,?,?,?,?)";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, planes.getTitulo());
            stmt.setString(2, planes.getDesc());
            stmt.setBoolean(3, planes.getEstado());
            stmt.setInt(4, idDept);
            stmt.setInt(5, idCiud);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getActId(Planes planes){
        int id = 0;
        String query = "SELECT id FROM actividad WHERE nombre = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, planes.getTitulo().trim());
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    private int getPlanId(Planes planes){
        int id = 0;
        String query = "SELECT id FROM planTuristico WHERE titulo = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, planes.getTitulo().trim());
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void planPunSave(Planes planes) {
        String query = "INSERT INTO planPunto (id_plan, id_act) values (?,?)";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, getPlanId(planes));
            stmt.setInt(2, getActId(planes));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void tarifaSave(Planes planes, Tarifa tarifa1, Tarifa tarifa2, Tarifa tarifa3) {
        if(tarifa1.getFecIni() != null){
            setTarifa(planes, tarifa1,"Alta");
        }
        if(tarifa2.getFecIni() != null){
            setTarifa(planes, tarifa2,"Media");
        }
        if(tarifa3.getFecIni() != null){
            setTarifa(planes, tarifa3,"Baja");
        }

    }

    private void setTarifa(Planes planes, Tarifa tarifa, String temp) {
        String query = "INSERT INTO tarifa (id_planTuris, id_temp, id_act, fec_ini, fec_fin, costo, otros, estado) values (?,?,?,?,?,?,?,?)";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, getPlanId(planes));
            stmt.setString(2, temp);
            stmt.setInt(3, getActId(planes));
            stmt.setDate(4, new java.sql.Date(tarifa.getFecIni().getTime()));
            stmt.setDate(5, new java.sql.Date(tarifa.getFecFin().getTime()));
            stmt.setDouble(6, tarifa.getCosto());
            stmt.setDouble(7, planes.getCostoAlim());
            stmt.setBoolean(8, planes.getEstado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPlan(Planes planes) {
        String query = "UPDATE planTuristico SET titulo= ? , descri= ? , cantDias= ? , estado= ?, alimen= ?, cantPer= ?, url= ?, costAlim= ?, fec_modi= CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, planes.getTitulo());
            stmt.setString(2, planes.getDesc());
            stmt.setInt(3, planes.getCantDias());
            stmt.setBoolean(4, planes.getEstado());
            stmt.setString(5, planes.getAlimentacion());
            stmt.setInt(6, planes.getCantPer());
            stmt.setString(7, planes.getUrl());
            stmt.setDouble(8, planes.getCostoAlim());
            stmt.setDouble(9, planes.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editAct(Planes planes, int idDept, int idCiud) {
        String query = "UPDATE actividad SET nombre= ? , descri= ? , estado= ? , id_dept= ?, id_ciud= ?, fec_modi= CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, planes.getTitulo());
            stmt.setString(2, planes.getDesc());
            stmt.setBoolean(3, planes.getEstado());
            stmt.setInt(4, idDept);
            stmt.setInt(5, idCiud);
            stmt.setInt(6, getActId2(idDept, idCiud, planes));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getActId2(int idDept, int idCiud, Planes plan){
        int id = 0;
        String query = "SELECT id FROM actividad WHERE id_dept = ? AND id_ciud = ? AND nombre = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idDept);
            stmt.setInt(2, idCiud);
            stmt.setString(3, plan.getTitulo().trim());
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void tarifaEdit(Planes planes, Tarifa tarifa1, Tarifa tarifa2, Tarifa tarifa3) {
        if(tarifa1.getFecIni() != null){
            editTarifa(planes, tarifa1,"Alta");
        }
        if(tarifa2.getFecIni() != null){
            editTarifa(planes, tarifa2,"Media");
        }
        if(tarifa3.getFecIni() != null){
            editTarifa(planes, tarifa3,"Baja");
        }

    }
    public void editTarifa(Planes planes, Tarifa tarifa, String temp) {
        String query = "UPDATE tarifa SET fec_ini = ? , fec_fin = ? , costo = ? , otros = ?, estado = ?, fec_modi = CURRENT_TIMESTAMP WHERE id_planTuris = ? AND id_temp = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(tarifa.getFecIni().getTime()));
            stmt.setDate(2, new java.sql.Date(tarifa.getFecFin().getTime()));
            stmt.setDouble(3, tarifa.getCosto());
            stmt.setDouble(4, planes.getCostoAlim());
            stmt.setBoolean(5, planes.getEstado());
            stmt.setInt(6, getPlanId(planes));
            stmt.setString(7, temp);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Planes getPlan(Tarifa tarifa) {
        Planes plan = null;
        String query = "SELECT * FROM planTuristico WHERE id = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tarifa.getIdPlan());
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    plan = new Planes();
                    plan.setId(rs.getInt("id"));
                    plan.setUrl(rs.getString("url"));
                    plan.setTitulo(rs.getString("titulo"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plan;
    }

    public String getDepartamento(Tarifa tarifa) {
        String nombre = null;
        String query = "SELECT d.departamento AS departamento FROM actividad a\n" +
                "INNER JOIN planPunto pp ON a.id = pp.id_act\n" +
                "INNER JOIN planTuristico pt ON pp.id_plan = pt.id\n" +
                "INNER JOIN ciudades c ON a.id_ciud = c.id\n" +
                "INNER JOIN departamentos d ON a.id_dept = d.id\n" +
                "WHERE pt.id = ?;";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tarifa.getIdPlan());
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    nombre = rs.getString("departamento");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nombre;
    }

    public String getCiudad(Tarifa tarifa) {
        String nombre = null;
        String query = "SELECT c.ciudad AS ciudad FROM actividad a\n" +
                "INNER JOIN planPunto pp ON a.id = pp.id_act\n" +
                "INNER JOIN planTuristico pt ON pp.id_plan = pt.id\n" +
                "INNER JOIN ciudades c ON a.id_ciud = c.id\n" +
                "INNER JOIN departamentos d ON a.id_dept = d.id\n" +
                "WHERE pt.id = ?;";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tarifa.getIdPlan());
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    nombre = rs.getString("ciudad");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nombre;
    }
}
