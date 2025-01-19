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
import orm.entities.*;

public class CategoriaDAO {

	private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

	public CategoriaDAO() {
	}

	public List<Categoria> obtenerCategorias() {
        String jpql = "SELECT c FROM Categoria c";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

	public List<Categoria> obtenerCategoriasEgreso() {
        String jpql = "SELECT c FROM CatEgreso c";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    public List<Categoria> obtenerCategoriasIngreso() {
        String jpql = "SELECT c FROM CatIngreso c";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    public List<Categoria> obtenerCategoriasTransferencia() {
        String jpql = "SELECT c FROM CatTransferencia c";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
	// Método para guardar una nueva categoría
	public void guardar(Categoria categoria) throws SQLException {
		List<Categoria> categorias = new ArrayList<Categoria>();

		String _SQL_INSERT = "INSERT INTO categoria (nombre, tipo) VALUES (?, ?)";

		PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_INSERT);
		pstmt.setString(1, categoria.getNombre());
		// pstmt.setString(2, categoria.getTipo());

		int filas = pstmt.executeUpdate();

		BddConnection.cerrar(pstmt);
		BddConnection.cerrar();

		// em.getTransaction().begin();
		// em.persist(categoria);
		// em.getTransaction().commit();
	}

	// Método para eliminar una categoría por su ID 
	public void eliminar(int id) {
	}
		
}
