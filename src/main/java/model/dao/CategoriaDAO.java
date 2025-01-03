package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Usuario;
import model.bdd.BddConnection;
import model.dto.CategoriaEgresoDTO;

public class CategoriaDAO {
	
	public CategoriaDAO() {
		
	}
	
	// Metodo para obtener datos de tabla categoria_egreso
	public static List<Categoria> getCategorias() throws SQLException {
		List<Categoria> categorias = new ArrayList<Categoria>();
		String _SQL_GET_ALL = "SELECT * FROM categoria_egreso";

		PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_GET_ALL);
		ResultSet rs = pstmt.executeQuery();

		// Iterar el result set para leer los datos
		while (rs.next()) {
			Categoria c = new Categoria();
			c.setId(rs.getInt("id"));
			c.setNombre(rs.getString("nombre"));

			categorias.add(c);
		}
		BddConnection.cerrar(pstmt);
		BddConnection.cerrar(rs);
		BddConnection.cerrar();

		return categorias;
	}
	
	// Metodo para obtener datos de tabla categoria_ingreso
	public static List<Categoria> getCategoriasIngreso() throws SQLException {
	    List<Categoria> categoriasIngreso = new ArrayList<Categoria>();
	    String sqlGetCategoriasIngreso = "SELECT * FROM categoria_ingreso";

	    PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sqlGetCategoriasIngreso);
	    ResultSet rs = pstmt.executeQuery();

	    // Iterar el result set para leer los datos
	    while (rs.next()) {
	        Categoria categoriaIngreso = new Categoria();
	        categoriaIngreso.setId(rs.getInt("id"));
	        categoriaIngreso.setNombre(rs.getString("nombre"));

	        categoriasIngreso.add(categoriaIngreso);
	    }

	    BddConnection.cerrar(pstmt);
	    BddConnection.cerrar(rs);
	    BddConnection.cerrar();

	    return categoriasIngreso;
	}

	
	// Metodo para obtener datos de tabla categoria_transferencia
	public static List<Categoria> getCategoriasTransferencia() throws SQLException {
	    List<Categoria> categoriasTransferencia = new ArrayList<Categoria>();
	    String sqlGetCategoriasTransferencia = "SELECT * FROM categoria_transferencia";

	    PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sqlGetCategoriasTransferencia);
	    ResultSet rs = pstmt.executeQuery();

	    while (rs.next()) {
	        Categoria categoriaTransferencia = new Categoria();
	        categoriaTransferencia.setId(rs.getInt("id"));
	        categoriaTransferencia.setNombre(rs.getString("nombre"));

	        categoriasTransferencia.add(categoriaTransferencia);
	    }
	    BddConnection.cerrar(pstmt);
	    BddConnection.cerrar(rs);
	    BddConnection.cerrar();

	    return categoriasTransferencia;
	}

	
	public static List<CategoriaEgresoDTO> obtenerCategoriasEgreso() throws SQLException {
	    List<CategoriaEgresoDTO> categoriasEgreso = new ArrayList<>();
	    String sql = "SELECT c.id, c.nombre AS categoria, SUM(m.valor) AS total_egreso " +
	                 "FROM categoria_egreso c " +
	                 "JOIN egreso e ON c.id = e.destino " +
	                 "JOIN movimiento m ON e.movimiento_id = m.id " +
	                 "GROUP BY c.nombre " +
	                 "ORDER BY total_egreso DESC;";

	    PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();

	    while (rs.next()) {
	    	int id = rs.getInt("id");
	        String nombre = rs.getString("categoria");
	        float totalEgreso = rs.getFloat("total_egreso");

	        CategoriaEgresoDTO dto = new CategoriaEgresoDTO(id, nombre, totalEgreso);
	        categoriasEgreso.add(dto);
	    }

	    BddConnection.cerrar(pstmt);
	    BddConnection.cerrar(rs);
	    BddConnection.cerrar();

	    return categoriasEgreso;
	}

}