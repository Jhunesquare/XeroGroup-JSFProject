package com.example.xerogroup.repositories;

import com.example.xerogroup.models.Client;
import com.example.xerogroup.models.User;
import com.example.xerogroup.utils.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository {

    public Optional<Client> getById(String cedula) {
        Client client = null;
        String query = "SELECT * FROM cliente as u WHERE u.cedula = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, cedula);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    client = new Client();
                    client.setCedula(rs.getString("cedula"));
                    client.setNombre(rs.getString("nombre"));
                    client.setCorreo(rs.getString("correo"));
                    client.setFec_nac(rs.getDate("fec_nac"));
                    client.setTel1(rs.getString("tel1"));
                    client.setTel2(rs.getString("tel2"));
                    client.setFec_modi(rs.getDate("fec_modi"));
                    client.setFec_crea(rs.getDate("fec_creacion"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(client);
    }

    public Optional<Client> getByUsername(String correo) {
        Client client = null;
        String query = "SELECT * FROM cliente as u WHERE u.correo = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, correo);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    client = new Client();
                    client.setCedula(rs.getString("cedula"));
                    client.setNombre(rs.getString("nombre"));
                    client.setCorreo(rs.getString("correo"));
                    client.setFec_nac(rs.getDate("fec_nac"));
                    client.setTel1(rs.getString("tel1"));
                    client.setTel2(rs.getString("tel2"));
                    client.setFec_modi(rs.getDate("fec_modi"));
                    client.setFec_crea(rs.getDate("fec_creacion"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(client);
    }

    public List<Client> clients() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT c.*, SUM(CASE WHEN co.id_clien IS NULL THEN 0 ELSE 1 END) as cantidadcompras \n" +
                "FROM cliente c \n" +
                "LEFT JOIN compra co ON co.id_clien = c.cedula \n" +
                "GROUP BY c.cedula";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    clients.add(getClients(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    private Client getClients(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setCedula(rs.getString("cedula"));
        client.setCedVend(rs.getString("cedVend"));
        client.setNombre(rs.getString("nombre"));
        client.setCorreo(rs.getString("correo"));
        client.setTel_perCont(rs.getString("tel_per_conta"));
        client.setVendedor(rs.getString("vendedor"));
        client.setNom_cont(rs.getString("nom_cont"));
        client.setFec_nac(rs.getDate("fec_nac"));
        client.setTel1(rs.getString("tel1"));
        client.setTel2(rs.getString("tel2"));
        client.setFec_modi(rs.getDate("fec_modi"));
        client.setFec_crea(rs.getDate("fec_creacion"));
        client.setCantCompras(rs.getInt("cantidadcompras"));
        return client;
    }

    public void saveClient(Client cliente,User vendedor){
        String query = "INSERT INTO cliente (cedula, cedVend, nombre, correo, tel_per_conta, vendedor, nom_cont, fec_nac, tel1, tel2) values (?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente.getCedula());
            stmt.setString(2, vendedor.getCedula());
            stmt.setString(3, cliente.getNombre());
            stmt.setString(4, cliente.getCorreo());
            stmt.setString(5, cliente.getTel_perCont());
            stmt.setString(6, vendedor.getNombre());
            stmt.setString(7, cliente.getNom_cont());
            stmt.setDate(8, new java.sql.Date(cliente.getFec_nac().getTime()));
            stmt.setString(9, cliente.getTel1());
            stmt.setString(10, cliente.getTel2());
            stmt.executeUpdate();
            if(!vendedor.getEstado()){
                PreparedStatement st = conn.prepareStatement("UPDATE usuario SET estado = true WHERE cedula = ?");
                st.setString(1, vendedor.getCedula());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteClient(String cliente){
        String query = "DELETE FROM cliente WHERE cedula = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editUser(Client client){
        String query = "UPDATE cliente SET nombre= ? , correo= ? , tel_per_conta= ?, nom_cont= ?, fec_nac= ? , tel1= ?, tel2= ?, fec_modi= CURRENT_TIMESTAMP WHERE cedula = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, client.getNombre());
            stmt.setString(2, client.getCorreo());
            stmt.setString(3, client.getTel_perCont());
            stmt.setString(4, client.getNom_cont());
            stmt.setDate(5, new java.sql.Date(client.getFec_nac().getTime()));
            stmt.setString(6, client.getTel1());
            stmt.setString(7, client.getTel2());
            stmt.setString(8, client.getCedula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> clientCed() {
        List<String> cedulas = new ArrayList<>();
        String query = "SELECT cedula FROM cliente";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    cedulas.add(rs.getString("cedula"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cedulas;
    }
}
