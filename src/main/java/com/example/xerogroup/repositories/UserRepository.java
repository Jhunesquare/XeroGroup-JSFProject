package com.example.xerogroup.repositories;

import com.example.xerogroup.models.User;
import com.example.xerogroup.utils.ConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    public void saveUser(User vendedor){
        String query = "INSERT INTO usuario (cedula, nombre, correo, password, tel1, tel2, fec_nac) values (?,?,?,?,?,?,?)";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vendedor.getCedula());
            stmt.setString(2, vendedor.getNombre());
            stmt.setString(3, vendedor.getCorreo());
            stmt.setString(4, vendedor.getPassword());
            stmt.setString(5, vendedor.getTel1());
            stmt.setString(6, vendedor.getTel2());
            stmt.setDate(7, new java.sql.Date(vendedor.getFec_nac().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> getUser(String cedula) {
        User user = null;
        String query = "SELECT * FROM usuario as u WHERE u.cedula = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, cedula);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    user = new User();
                    user.setCedula(rs.getString("cedula"));
                    user.setNombre(rs.getString("nombre"));
                    user.setCorreo(rs.getString("correo"));
                    user.setPassword(rs.getString("password"));
                    user.setFec_nac(rs.getDate("fec_nac"));
                    user.setTel1(rs.getString("tel1"));
                    user.setTel2(rs.getString("tel2"));
                    user.setFec_modi(rs.getDate("fec_modi"));
                    user.setFec_crea(rs.getDate("fec_creacion"));
                    user.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> getByUsername(String username, String password) {
        User user = null;
        String query = "SELECT * FROM usuario as u WHERE u.correo = ? AND u.password = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, username);
            stmt.setString(2, password);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    user = new User();
                    user.setCedula(rs.getString("cedula"));
                    user.setNombre(rs.getString("nombre"));
                    user.setCorreo(rs.getString("correo"));
                    user.setPassword(rs.getString("password"));
                    user.setFec_nac(rs.getDate("fec_nac"));
                    user.setTel1(rs.getString("tel1"));
                    user.setTel2(rs.getString("tel2"));
                    user.setFec_modi(rs.getDate("fec_modi"));
                    user.setFec_crea(rs.getDate("fec_creacion"));
                    user.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> getByCorreo(String username) {
        User user = null;
        String query = "SELECT * FROM usuario as u WHERE u.correo = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, username);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    user = new User();
                    user.setCedula(rs.getString("cedula"));
                    user.setNombre(rs.getString("nombre"));
                    user.setCorreo(rs.getString("correo"));
                    user.setPassword(rs.getString("password"));
                    user.setFec_nac(rs.getDate("fec_nac"));
                    user.setTel1(rs.getString("tel1"));
                    user.setTel2(rs.getString("tel2"));
                    user.setFec_modi(rs.getDate("fec_modi"));
                    user.setFec_crea(rs.getDate("fec_creacion"));
                    user.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(user);
    }

    public List<User> users() {
        List<User> users = new ArrayList<>();
        String query = "SELECT v.*, SUM(CASE WHEN c.cedula IS NULL THEN 0 ELSE 1 END) as cantidadclientes \n" +
                "FROM usuario v \n" +
                "LEFT JOIN cliente c ON c.cedVend = v.cedula \n" +
                "WHERE v.rol = 'vend' GROUP BY v.cedula";
        try (Connection conn = ConnectionDatabase.getConnection();) {
            Statement stmt = conn.createStatement();
            try(ResultSet rs = stmt.executeQuery(query)){
                while (rs.next()) {
                    users.add(getUser(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setCedula(rs.getString("cedula"));
        user.setNombre(rs.getString("nombre"));
        user.setCorreo(rs.getString("correo"));
        user.setPassword(rs.getString("password"));
        user.setFec_nac(rs.getDate("fec_nac"));
        user.setTel1(rs.getString("tel1"));
        user.setTel2(rs.getString("tel2"));
        user.setFec_modi(rs.getDate("fec_modi"));
        user.setFec_crea(rs.getDate("fec_creacion"));
        user.setRol(rs.getString("rol"));
        user.setEstado(rs.getBoolean("estado"));
        user.setCantClientes(rs.getInt("cantidadclientes"));
        return user;
    }

    public void deleteUser(String vendedor){
        String query = "DELETE FROM usuario WHERE cedula = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vendedor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editUser(User vendedor){
        String query = "UPDATE usuario SET nombre= ? , correo= ? , fec_nac= ? , tel1= ?, tel2= ?, estado= ?, fec_modi= CURRENT_TIMESTAMP WHERE cedula = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vendedor.getNombre());
            stmt.setString(2, vendedor.getCorreo());
            stmt.setDate(3, new java.sql.Date(vendedor.getFec_nac().getTime()));
            stmt.setString(4, vendedor.getTel1());
            stmt.setString(5, vendedor.getTel2());
            stmt.setBoolean(6, vendedor.getEstado());
            stmt.setString(7, vendedor.getCedula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getVendedor(String correo) {
        User user = null;
        String query = "SELECT * FROM usuario as u WHERE u.correo = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, correo);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    user = new User();
                    user.setCedula(rs.getString("cedula"));
                    user.setNombre(rs.getString("nombre"));
                    user.setCorreo(rs.getString("correo"));
                    user.setPassword(rs.getString("password"));
                    user.setFec_nac(rs.getDate("fec_nac"));
                    user.setTel1(rs.getString("tel1"));
                    user.setTel2(rs.getString("tel2"));
                    user.setFec_modi(rs.getDate("fec_modi"));
                    user.setFec_crea(rs.getDate("fec_creacion"));
                    user.setRol(rs.getString("rol"));
                    user.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
