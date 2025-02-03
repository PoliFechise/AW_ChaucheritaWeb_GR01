package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import model.dto.CategoriaEgresoDTO;
import model.dto.CategoriaIngresoDTO;
import model.dto.CategoriaTransferenciaDTO;
import orm.entities.*;

public class CategoriaDAO {

	private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

	public CategoriaDAO() {
	}

	// Método para listar las categorías de egreso
	public List<Categoria> obtenerCategoriasEgreso() {
		String jpql = "SELECT catEgreso FROM CatEgreso catEgreso";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	// Método para listar las categorías de ingreso
	public List<Categoria> obtenerCategoriasIngreso() {
		String jpql = "SELECT catIngreso FROM CatIngreso catIngreso";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	// Método para listar las categorías de transferencia
	public List<Categoria> obtenerCategoriasTransferencia() {
		String jpql = "SELECT catTransferencia FROM CatTransferencia catTransferencia";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	// Método para guardar una nueva categoría
	public void guardar(Categoria categoria) {
		try {
			em.getTransaction().begin();
			em.persist(categoria);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}

	// Método para actualizar una categoría
	public void actualizar(int id, String nombre) {
		try {
			em.getTransaction().begin();
			Categoria categoria = em.find(Categoria.class, id);
			if (categoria != null) {
				categoria.setNombre(nombre);
				em.merge(categoria);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}

	// Método para eliminar una categoría
	public void eliminar(int id) {
	    EntityTransaction transaction = em.getTransaction();
	    try {
	        transaction.begin();

	        // Eliminar referencias en la tabla egreso
	        Query queryEgreso = em.createQuery("DELETE FROM Egreso e WHERE e.destino.id = :categoriaId");
	        queryEgreso.setParameter("categoriaId", id);
	        queryEgreso.executeUpdate();

	        // Eliminar referencias en la tabla transferencia
	        Query queryTransferencia = em.createQuery("DELETE FROM Transferencia t WHERE t.categoria.id = :categoriaId");
	        queryTransferencia.setParameter("categoriaId", id);
	        queryTransferencia.executeUpdate();

	        // Eliminar referencias en la tabla ingreso
	        Query queryIngreso = em.createQuery("DELETE FROM Ingreso i WHERE i.origen.id = :categoriaId");
	        queryIngreso.setParameter("categoriaId", id);
	        queryIngreso.executeUpdate();

	        // Eliminar la categoría
	        Categoria categoria = em.find(Categoria.class, id);
	        if (categoria != null) {
	            em.remove(categoria);
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw new RuntimeException("Error al eliminar la categoría", e);
	    }
	}

	// Metodos para obtener la categoria por su id

	public CatIngreso encontrarCategoriaIngresoPorId(Integer id) {

		return em.find(CatIngreso.class, id);

	}

	public CatEgreso encontrarCategoriaEgresoPorId(Integer id) {

		return em.find(CatEgreso.class, id);

	}

	public CatTransferencia encontrarCategoriaTransferenciaPorId(Integer id) {

		return em.find(CatTransferencia.class, id);
	}

	public List<CategoriaEgresoDTO> obtenerCategoriasEgresoYSumaValor() throws SQLException {
		try {
			String jpql = "SELECT new model.dto.CategoriaEgresoDTO(c.id, c.nombre, SUM(m.valor)) " + "FROM Egreso e "
					+ "JOIN e.destino c " + "JOIN e.movimiento m " + "GROUP BY c.id, c.nombre "
					+ "ORDER BY SUM(m.valor) DESC";

			Query query = em.createQuery(jpql);
			return query.getResultList();
		} catch (Exception e) {
			throw new SQLException("Error al obtener las categorías de egreso: " + e.getMessage(), e);
		}
	}

	public List<CategoriaEgresoDTO> obtenerCategoriasEgresoYSumaValorPorFechas(LocalDateTime fechaInicio,
			LocalDateTime fechaFin) throws SQLException {
		try {
			String jpql = "SELECT new model.dto.CategoriaEgresoDTO(c.id, c.nombre, SUM(m.valor)) " + "FROM Egreso e "
					+ "JOIN e.destino c " + "JOIN e.movimiento m " + "WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin "
					+ "GROUP BY c.id, c.nombre " + "ORDER BY SUM(m.valor) DESC";

			Query query = em.createQuery(jpql);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			return query.getResultList();
		} catch (Exception e) {
			throw new SQLException("Error al obtener las categorías de egreso por fechas: " + e.getMessage(), e);
		}
	}

	public List<CategoriaIngresoDTO> obtenerCategoriasIngresoYSumaValor() throws SQLException {
		try {
			String jpql = "SELECT new model.dto.CategoriaIngresoDTO(ci.id, ci.nombre, SUM(m.valor)) "
					+ "FROM Ingreso i " + "JOIN i.origen ci " + "JOIN i.movimiento m " + "GROUP BY ci.id, ci.nombre "
					+ "ORDER BY SUM(m.valor) DESC";

			Query query = em.createQuery(jpql);
			List<CategoriaIngresoDTO> resultado = query.getResultList();
			System.out.println("Resultado de la consulta JPQL: " + resultado);

			return query.getResultList();
		} catch (Exception e) {
			throw new SQLException("Error al obtener las categorías de ingreso: " + e.getMessage(), e);
		}
	}

	public List<CategoriaIngresoDTO> obtenerCategoriasIngresoYSumaValorPorFechas(LocalDateTime fechaInicio,
			LocalDateTime fechaFin) throws SQLException {
		try {
			String jpql = "SELECT new model.dto.CategoriaIngresoDTO(ci.id, ci.nombre, SUM(m.valor)) "
					+ "FROM Ingreso i " + "JOIN i.origen ci " + "JOIN i.movimiento m "
					+ "WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin " + "GROUP BY ci.id, ci.nombre "
					+ "ORDER BY SUM(m.valor) DESC";

			Query query = em.createQuery(jpql);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			return query.getResultList();
		} catch (Exception e) {
			throw new SQLException("Error al obtener las categorías de ingreso por fechas: " + e.getMessage(), e);
		}
	}

	public List<CategoriaTransferenciaDTO> obtenerCategoriasTransferenciaYSumaValor() throws SQLException {
		try {
			String jpql = "SELECT new model.dto.CategoriaTransferenciaDTO(ct.id, ct.nombre, SUM(m.valor)) "
					+ "FROM Transferencia t " + "JOIN t.categoria ct " + "JOIN t.movimiento m "
					+ "GROUP BY ct.id, ct.nombre " + "ORDER BY SUM(m.valor) DESC";

			Query query = em.createQuery(jpql);
			return query.getResultList();
		} catch (Exception e) {
			throw new SQLException("Error al obtener las categorías de transferencia: " + e.getMessage(), e);
		}
	}

	public List<CategoriaTransferenciaDTO> obtenerCategoriasTransferenciaYSumaValorPorFechas(LocalDateTime fechaInicio,
			LocalDateTime fechaFin) throws SQLException {
		try {
			String jpql = "SELECT new model.dto.CategoriaTransferenciaDTO(ct.id, ct.nombre, SUM(m.valor)) "
					+ "FROM Transferencia t " + "JOIN t.categoria ct " + "JOIN t.movimiento m "
					+ "WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin " + "GROUP BY ct.id, ct.nombre "
					+ "ORDER BY SUM(m.valor) DESC";

			Query query = em.createQuery(jpql);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			return query.getResultList();
		} catch (Exception e) {
			throw new SQLException("Error al obtener las categorías de transferencia por fechas: " + e.getMessage(), e);
		}
	}
}
