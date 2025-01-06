package model.dao;

import model.Ingreso;
import model.bdd.BddConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class IngresoDAO {
    private Connection connection;

    public IngresoDAO() {
        // Inicializar la conexión desde BddConnection
        this.connection = BddConnection.getConexion();
    }

    /**
     * Guarda un ingreso en la base de datos utilizando el procedimiento almacenado 'insertar_ingreso'.
     * @param ingreso Objeto de tipo Ingreso con los datos a guardar.
     * @throws SQLException En caso de error de conexión o ejecución de la consulta.
     */
    public void guardarIngreso(Ingreso ingreso) throws SQLException {
        String spQuery = "{CALL insertar_ingreso(?, ?, ?, ?, ?)}"; // Llamada al procedimiento almacenado

        CallableStatement cs = null;

        try {
            // Preparar la llamada al procedimiento almacenado
            cs = connection.prepareCall(spQuery);

            // Configurar los parámetros del procedimiento almacenado
            cs.setString(1, ingreso.getConcepto());        // p_concepto: VARCHAR
            cs.setFloat(2, ingreso.getValor());           // p_valor: DECIMAL(10,2)
            cs.setTimestamp(3, new java.sql.Timestamp(ingreso.getFecha().getTime())); // p_fecha: DATETIME
            cs.setString(4, ingreso.getOrigen());            // p_origen: INT
            cs.setString(5, ingreso.getDestino());        // p_destino: VARCHAR(50)

            // Ejecutar el procedimiento almacenado
            cs.execute();
        } catch (SQLException e) {
            // Manejar excepciones SQL
            throw new SQLException("Error al ejecutar el procedimiento almacenado 'insertar_ingreso': " + e.getMessage(), e);
        } finally {
            // Cerrar el CallableStatement y la conexión
            if (cs != null) {
                cs.close();
            }
            BddConnection.cerrar();
        }
    }
}
