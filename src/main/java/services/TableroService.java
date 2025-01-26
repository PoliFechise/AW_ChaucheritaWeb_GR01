package services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.dao.CategoriaDAO;
import model.dao.CuentaDAO;
import model.dto.*;
import orm.entities.Cuenta;

public class TableroService {
    private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();
    private CategoriaDAO categoriaDAO;
    private CuentaDAO cuentaDAO;

    public TableroService() {
    	this.categoriaDAO = new CategoriaDAO();
    	this.cuentaDAO = new CuentaDAO();
    }

    public TableroDTO obtenerDatosTablero() throws SQLException {
        TableroDTO tableroDTO = new TableroDTO();

        // Obtener categorías de egreso
        List<CategoriaEgresoDTO> categoriasEgreso = categoriaDAO.obtenerCategoriasEgresoYSumaValor();
        tableroDTO.setCategoriasEgreso(categoriasEgreso);
        System.out.println("Categorías de Egreso: " + categoriasEgreso); // Log de categorías de egreso
        
        // Obtener categorías de ingreso
        List<CategoriaIngresoDTO> categoriasIngreso = categoriaDAO.obtenerCategoriasIngresoYSumaValor();
        tableroDTO.setCategoriasIngreso(categoriasIngreso);
        System.out.println("Categorías de Ingreso: " + categoriasIngreso);

        // Obtener categorías de transferencia
        List<CategoriaTransferenciaDTO> categoriasTransferencia = categoriaDAO.obtenerCategoriasTransferenciaYSumaValor();
        tableroDTO.setCategoriasTransferencia(categoriasTransferencia);
        System.out.println("Categorías de Transferencia: " + categoriasTransferencia);

        // Obtener cuentas
        List<Cuenta> cuentas = cuentaDAO.obtenerCuentas();
        tableroDTO.setCuentas(cuentas);

        return tableroDTO;
    }

    public TableroDTO obtenerDatosTableroPorFechas(String fechaInicioStr, String fechaFinStr) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fechaInicio = LocalDate.parse(fechaInicioStr, formatter).atStartOfDay();
        LocalDateTime fechaFin = LocalDate.parse(fechaFinStr, formatter).atTime(23, 59, 59);

        TableroDTO tableroDTO = new TableroDTO();

        // Obtener categorías de egreso
        List<CategoriaEgresoDTO> categoriasEgreso = categoriaDAO.obtenerCategoriasEgresoYSumaValorPorFechas(fechaInicio, fechaFin);
        tableroDTO.setCategoriasEgreso(categoriasEgreso);
        
        // Obtener categorías de ingreso
        List<CategoriaIngresoDTO> categoriasIngreso = categoriaDAO.obtenerCategoriasIngresoYSumaValorPorFechas(fechaInicio, fechaFin);
        tableroDTO.setCategoriasIngreso(categoriasIngreso);

        // Obtener categorías de transferencia
        List<CategoriaTransferenciaDTO> categoriasTransferencia = categoriaDAO.obtenerCategoriasTransferenciaYSumaValorPorFechas(fechaInicio, fechaFin);
        tableroDTO.setCategoriasTransferencia(categoriasTransferencia);

        // Obtener cuentas
        List<Cuenta> cuentas = cuentaDAO.obtenerCuentas();
        tableroDTO.setCuentas(cuentas);

        return tableroDTO;
    }
}