package com.example.xerogroup.repositories;

import com.example.xerogroup.models.Tarifa;
import com.example.xerogroup.utils.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CompraRepository {
    public void comprar(Tarifa tarifa, String cliente, String vendedor) {
        String query = "INSERT INTO compra (id_clien, id_vend, costo_planes, otros, total) values (?,?,?,?,?)";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente);
            stmt.setString(2, vendedor);
            stmt.setDouble(3, tarifa.getCosto());
            stmt.setDouble(4, tarifa.getCostoAlim());
            stmt.setDouble(5, (tarifa.getCostoAlim() + tarifa.getCosto()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
