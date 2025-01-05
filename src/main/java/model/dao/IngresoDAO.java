package model.dao;

import model.Ingreso;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class IngresoDAO {
    private Connection connection;

    public IngresoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean guardarIngreso(Ingreso ingreso) throws SQLException {
        String query = "{CALL insertar_ingreso(?, ?, ?, ?, ?)}";
        CallableStatement cs = connection.prepareCall(query);
        cs.setString(1, ingreso.getConcepto());
        cs.setFloat(2, ingreso.getValor());
        cs.setTimestamp(3, new java.sql.Timestamp(ingreso.getFecha().getTime()));
        cs.setInt(4, ingreso.getOrigen());
        cs.setString(5, ingreso.getDestino());
        return cs.executeUpdate() > 0;
    }
}
