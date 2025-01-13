package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.bdd.BddConnection;
import model.dto.CategoriaEgresoDTO;
import model.CatEgreso;
import model.CatIngreso;
import model.CatTransferencia;
import model.Categoria;
import model.TipoCategoria;

public class CategoriaDAO {

	private EntityManager em;

	public CategoriaDAO() {
		this.em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();
	}

	// Método para guardar una nueva categoría
	public void guardarCategoria(Categoria categoria) {
		em.getTransaction().begin();
		em.persist(categoria);
		em.getTransaction().commit();
	}

	// Método para listar las categorías - Consulta JPQL 
	public List<CatIngreso> obtenerCategoriasIngreso() {
	    String sentenceJPQL = "SELECT c FROM CatIngreso c";
	    Query query = em.createQuery(sentenceJPQL);
	    
	    return query.getResultList();
	}

	public List<CatEgreso> obtenerCategoriasEgreso() {
	    String sentenceJPQL = "SELECT c FROM CatEgreso c";
	    Query query = em.createQuery(sentenceJPQL);
	    
	    return query.getResultList();
	}
	
	public List<CatTransferencia> obtenerCategoriasTransferencia() {
		String sentenceJPQL = "SELECT c FROM CatTransferencia c";
		Query query = em.createQuery(sentenceJPQL);
		
		return query.getResultList();
	}
}
