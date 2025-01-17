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
	
	EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager(); 

	public CategoriaDAO() {}

	public List<Categoria> getCategorias() throws SQLException {
		String sentenceJPQL = "SELECT c from Categoria c";
		
		Query query = em.createQuery(sentenceJPQL);
		
		return query.getResultList();
	}
	
	public List<CategoriaEgresoDTO> getCategoriasEgreso() throws SQLException {
        try {
            String jpql = "SELECT new model.dto.CategoriaEgresoDTO(c.id, c.nombre, SUM(m.valor)) " +
                          "FROM Egreso e " +
                          "JOIN e.destino c " +
                          "JOIN e.movimiento m " +
                          "WHERE TYPE(c) = CatEgreso " +
                          "GROUP BY c.id, c.nombre " +
                          "ORDER BY SUM(m.valor) DESC";

            Query query = em.createQuery(jpql);
            return query.getResultList();
        } catch (Exception e) {
            throw new SQLException("Error al obtener las categorías de egreso: " + e.getMessage(), e);
        }
    }
	
	// Método para obtener todas las categorías
	public static List<Categoria> getCategoriasSinORM() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT * FROM categoria";

		try (PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));
				//categoria.setTipo(rs.getString("tipo"));

				categorias.add(categoria);
			}
		}
		return categorias;
	}

	// Método para obtener categorías de tipo ingreso
	public List<Categoria> getCategoriasIngreso() throws SQLException {
		List<Categoria> categoriasIngreso = new ArrayList<>();
		String sql = "SELECT * FROM categoria WHERE tipo = 'ingreso'";

		try (PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));
				//categoria.setTipo(rs.getString("tipo"));

				categoriasIngreso.add(categoria);
			}
		}
		return categoriasIngreso;
	}

	// Método para obtener categorías de tipo egreso
	public List<Categoria> getCategoriasEgresoSinORM() throws SQLException {
		List<Categoria> categoriasEgreso = new ArrayList<>();
		String sql = "SELECT * FROM categoria WHERE tipo = 'egreso'";

		try (PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));
				//categoria.setTipo(rs.getString("tipo"));

				categoriasEgreso.add(categoria);
			}
		}
		return categoriasEgreso;
	}

	// Método para obtener categorías de tipo transferencia
	public List<Categoria> getCategoriasTransferencia() throws SQLException {
		List<Categoria> categoriasTransferencia = new ArrayList<>();
		String sql = "SELECT * FROM categoria WHERE tipo = 'transferencia'";

		try (PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));
				//categoria.setTipo(rs.getString("tipo"));

				categoriasTransferencia.add(categoria);
			}
		}
		return categoriasTransferencia;
	}

	// Método para obtener datos de egresos agrupados y sumados
	public static List<CategoriaEgresoDTO> obtenerCategoriasEgresoSinORM() throws SQLException {
		List<CategoriaEgresoDTO> categoriasEgreso = new ArrayList<>();
		String sql = "SELECT c.id, c.nombre AS categoria, SUM(m.valor) AS total_egreso " + "FROM categoria c "
				+ "JOIN egreso e ON c.id = e.destino " + "JOIN movimiento m ON e.movimiento_id = m.id "
				+ "WHERE c.tipo = 'egreso' " + "GROUP BY c.id, c.nombre " + "ORDER BY total_egreso DESC;";

		try (PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("categoria");
				float totalEgreso = rs.getFloat("total_egreso");

				CategoriaEgresoDTO dto = new CategoriaEgresoDTO(id, nombre, totalEgreso);
				categoriasEgreso.add(dto);
			}
		}
		return categoriasEgreso;
	}

	// Método para guardar una nueva categoría
	public void guardarCategoria(Categoria categoria) throws SQLException {
		List<Categoria> categorias = new ArrayList<Categoria>();

		String _SQL_INSERT = "INSERT INTO categoria (nombre, tipo) VALUES (?, ?)";

		PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_INSERT);
		pstmt.setString(1, categoria.getNombre());
		//pstmt.setString(2, categoria.getTipo());

		int filas = pstmt.executeUpdate();

		BddConnection.cerrar(pstmt);
		BddConnection.cerrar();

		// em.getTransaction().begin();
		// em.persist(categoria);
		// em.getTransaction().commit();
	}

	// Método para eliminar una categoría por su ID y tipo
	public void eliminarCategoria(int id, String tipo) throws SQLException {
		String sql = "DELETE FROM categoria WHERE id = ? AND tipo = ?"; // Consideramos el tipo también

		try (PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.setString(2, tipo); // Establecemos el tipo también
			pstmt.executeUpdate();
		}
	}
}
