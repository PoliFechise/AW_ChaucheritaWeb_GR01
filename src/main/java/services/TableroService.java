package services;

import java.sql.SQLException;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.dto.*;
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

    public TableroDTO obtenerDatosTableroPorFechas(String fechaInicio, String fechaFin) throws SQLException {
        TableroDTO tableroDTO = new TableroDTO();

        // Obtener categorías de egreso
        List<CategoriaEgresoDTO> categoriasEgreso = obtenerCategoriasEgresoPorFechas(fechaInicio, fechaFin);
        tableroDTO.setCategoriasEgreso(categoriasEgreso);
        
        // Obtener categorías de ingreso
        List<CategoriaIngresoDTO> categoriasIngreso = obtenerCategoriasIngresoPorFechas(fechaInicio, fechaFin);
        tableroDTO.setCategoriasIngreso(categoriasIngreso);

        // Obtener categorías de transferencia
        List<CategoriaTransferenciaDTO> categoriasTransferencia = obtenerCategoriasTransferenciaPorFechas(fechaInicio, fechaFin);
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

    private List<CategoriaEgresoDTO> obtenerCategoriasEgresoPorFechas(String fechaInicio, String fechaFin) throws SQLException {
        try {
            String jpql = "SELECT new model.dto.CategoriaEgresoDTO(c.id, c.nombre, SUM(m.valor)) " +
                          "FROM Egreso e " +
                          "JOIN e.destino c " +
                          "JOIN e.movimiento m " +
                          "WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin " +
                          "GROUP BY c.id, c.nombre " +
                          "ORDER BY SUM(m.valor) DESC";

            Query query = em.createQuery(jpql);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            return query.getResultList();
        } catch (Exception e) {
            throw new SQLException("Error al obtener las categorías de egreso por fechas: " + e.getMessage(), e);
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

    private List<CategoriaIngresoDTO> obtenerCategoriasIngresoPorFechas(String fechaInicio, String fechaFin) throws SQLException {
        try {
            String jpql = "SELECT new model.dto.CategoriaIngresoDTO(c.id, c.nombre, SUM(m.valor)) " +
                          "FROM Ingreso i " +
                          "JOIN i.destino c " +
                          "JOIN i.movimiento m " +
                          "WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin " +
                          "GROUP BY c.id, c.nombre " +
                          "ORDER BY SUM(m.valor) DESC";

            Query query = em.createQuery(jpql);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            return query.getResultList();
        } catch (Exception e) {
            throw new SQLException("Error al obtener las categorías de ingreso por fechas: " + e.getMessage(), e);
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

    private List<CategoriaTransferenciaDTO> obtenerCategoriasTransferenciaPorFechas(String fechaInicio, String fechaFin) throws SQLException {
        try {
            String jpql = "SELECT new model.dto.CategoriaTransferenciaDTO(c.id, c.nombre, SUM(m.valor)) " +
                          "FROM Transferencia t " +
                          "JOIN t.destino c " +
                          "JOIN t.movimiento m " +
                          "WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin " +
                          "GROUP BY c.id, c.nombre " +
                          "ORDER BY SUM(m.valor) DESC";

            Query query = em.createQuery(jpql);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            return query.getResultList();
        } catch (Exception e) {
            throw new SQLException("Error al obtener las categorías de transferencia por fechas: " + e.getMessage(), e);
        }
    }

    private List<Cuenta> obtenerCuentas() {
        List<Cuenta> cuentas = em.createQuery("SELECT c FROM Cuenta c", Cuenta.class).getResultList();
        System.out.println("Cuentas obtenidas: " + cuentas); // Log para verificar
        return cuentas;
    }
}