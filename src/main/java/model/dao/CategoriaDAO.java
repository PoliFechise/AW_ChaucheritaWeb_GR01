package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import model.dto.CategoriaEgresoDTO;
import orm.entities.*;

public class CategoriaDAO {

	private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

	public CategoriaDAO() {
	}

	// Método para listar las categorías de egreso
	public List<Categoria> obtenerCategoriasEgreso() {
		String jpql = "SELECT c FROM CatEgreso c";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	// Método para listar las categorías de ingreso
	public List<Categoria> obtenerCategoriasIngreso() {
		String jpql = "SELECT c FROM CatIngreso c";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	// Método para listar las categorías de transferencia
	public List<Categoria> obtenerCategoriasTransferencia() {
		String jpql = "SELECT c FROM CatTransferencia c";
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
	public void actualizar(Categoria categoria) {
		try {
			em.getTransaction().begin();
			em.merge(categoria);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}

	// Método para eliminar una categoría
	public void eliminar(int id) {
	    em.getTransaction().begin();
	    Categoria categoria = em.find(Categoria.class, id);

	    // Eliminar referencias en la tabla egreso
	    Query queryEgreso = em.createQuery("DELETE FROM Egreso e WHERE e.destino.id = :categoriaId");
	    queryEgreso.setParameter("categoriaId", id);
	    queryEgreso.executeUpdate();

	    // Eliminar referencias en la tabla transferencia
	    Query queryTransferencia = em.createQuery("DELETE FROM Transferencia t WHERE t.destino.id = :categoriaId");
	    queryTransferencia.setParameter("categoriaId", id);
	    queryTransferencia.executeUpdate();

	    // Eliminar referencias en la tabla ingreso
	    Query queryIngreso = em.createQuery("DELETE FROM Ingreso i WHERE i.origen.id = :categoriaId");
	    queryIngreso.setParameter("categoriaId", id);
	    queryIngreso.executeUpdate();

	    em.remove(categoria);
	    em.getTransaction().commit();
	}
}
