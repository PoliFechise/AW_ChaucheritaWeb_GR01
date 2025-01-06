package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bdd.BddConnection;
import model.dto.CategoriaEgresoDTO;
import model.Categoria;

public class CategoriaDAO {

	public CategoriaDAO() {
	}

	// Método para obtener todas las categorías
	public static List<Categoria> getCategorias() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT * FROM categoria";

		try (PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));
				categoria.setTipo(rs.getString("tipo"));

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
				categoria.setTipo(rs.getString("tipo"));

				categoriasIngreso.add(categoria);
			}
		}
		return categoriasIngreso;
	}

	// Método para obtener categorías de tipo egreso
	public List<Categoria> getCategoriasEgreso() throws SQLException {
		List<Categoria> categoriasEgreso = new ArrayList<>();
		String sql = "SELECT * FROM categoria WHERE tipo = 'egreso'";

		try (PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));
				categoria.setTipo(rs.getString("tipo"));

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
				categoria.setTipo(rs.getString("tipo"));

				categoriasTransferencia.add(categoria);
			}
		}
		return categoriasTransferencia;
	}

	// Método para obtener datos de egresos agrupados y sumados
	public static List<CategoriaEgresoDTO> obtenerCategoriasEgreso() throws SQLException {
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
		String sql = "INSERT INTO categoria(nombre, tipo) VALUES (?, ?)";

		try (PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sql)) {
			pstmt.setString(1, categoria.getNombre());
			pstmt.setString(2, categoria.getTipo());
			pstmt.executeUpdate();
		}
	}
}
