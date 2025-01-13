package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.bdd.BddConnection;
import model.dto.CategoriaEgresoDTO;
import model.Categoria;

public class CategoriaDAO {

	private EntityManager em;

	public CategoriaDAO() {
		this.em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();
	}
	
	// Método para guardar una nueva categoría
	public void guardarCategoria(Categoria categoria) throws SQLException {
		em.getTransaction().begin();
		em.persist(categoria);
		em.getTransaction().commit();
	}
}
