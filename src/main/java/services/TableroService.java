package services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.dto.CategoriaEgresoDTO;
import model.dto.CategoriaIngresoDTO;
import model.dto.CategoriaTransferenciaDTO;
import model.dto.TableroDTO;
import orm.entities.Cuenta;

public class TableroService {

    private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

    public TableroService() {
    }

    public TableroDTO obtenerDatosTablero() throws SQLException {
        TableroDTO tableroDTO = new TableroDTO();

        // Obtener categorías de egreso
        List<CategoriaEgresoDTO> categoriasEgreso = obtenerCategoriasEgreso();
        tableroDTO.setCategoriasEgreso(categoriasEgreso);
        
        // Obtener categorías de ingreso
        List<CategoriaIngresoDTO> categoriasIngreso = obtenerCategoriasIngreso();
        tableroDTO.setCategoriasIngreso(categoriasIngreso);

        // Obtener categorías de transferencia
        List<CategoriaTransferenciaDTO> categoriasTransferencia = obtenerCategoriasTransferencia();
        tableroDTO.setCategoriasTransferencia(categoriasTransferencia);

        // Obtener cuentas
        List<Cuenta> cuentas = obtenerCuentas();
        tableroDTO.setCuentas(cuentas);

        return tableroDTO;
    }

    private List<CategoriaEgresoDTO> obtenerCategoriasEgreso() throws SQLException {
        try {
            String jpql = "SELECT new model.dto.CategoriaEgresoDTO(c.id, c.nombre, SUM(m.valor)) " +
                          "FROM Egreso e " +
                          "JOIN e.destino c " +
                          "JOIN e.movimiento m " +
                          "GROUP BY c.id, c.nombre " +
                          "ORDER BY SUM(m.valor) DESC";

            Query query = em.createQuery(jpql);
            return query.getResultList();
        } catch (Exception e) {
            throw new SQLException("Error al obtener las categorías de egreso: " + e.getMessage(), e);
        }
    }

    private List<CategoriaIngresoDTO> obtenerCategoriasIngreso() throws SQLException {
        try {
            String jpql = "SELECT new model.dto.CategoriaIngresoDTO(c.id, c.nombre, SUM(m.valor)) " +
                          "FROM Ingreso i " +
                          "JOIN i.destino c " +
                          "JOIN i.movimiento m " +
                          "GROUP BY c.id, c.nombre " +
                          "ORDER BY SUM(m.valor) DESC";

            Query query = em.createQuery(jpql);
            return query.getResultList();
        } catch (Exception e) {
            throw new SQLException("Error al obtener las categorías de ingreso: " + e.getMessage(), e);
        }
    }

    private List<CategoriaTransferenciaDTO> obtenerCategoriasTransferencia() throws SQLException {
        try {
            String jpql = "SELECT new model.dto.CategoriaTransferenciaDTO(c.id, c.nombre, SUM(m.valor)) " +
                          "FROM Transferencia t " +
                          "JOIN t.destino c " +
                          "JOIN t.movimiento m " +
                          "GROUP BY c.id, c.nombre " +
                          "ORDER BY SUM(m.valor) DESC";

            Query query = em.createQuery(jpql);
            return query.getResultList();
        } catch (Exception e) {
            throw new SQLException("Error al obtener las categorías de transferencia: " + e.getMessage(), e);
        }
    }

    private List<Cuenta> obtenerCuentas() {
        List<Cuenta> cuentas = em.createQuery("SELECT c FROM Cuenta c", Cuenta.class).getResultList();
        System.out.println("Cuentas obtenidas: " + cuentas); // Log para verificar
        return cuentas;
    }
}