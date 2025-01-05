package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.bdd.BddConnection;

public class UsuarioDAO {

	public UsuarioDAO() {

	}

	public List<Usuario> getUsuarios() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String _SQL_GET_ALL = "SELECT * FROM usuario";

		PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_GET_ALL);
		ResultSet rs = pstmt.executeQuery();

		// Iterar el result set para leer los datos
		while (rs.next()) {
			Usuario u = new Usuario();
			u.setId(rs.getInt("id"));
			u.setNombre(rs.getString("nombre"));
			u.setContrasena(rs.getString("contrasena"));
			u.setUsuario(rs.getString("usuario"));

			usuarios.add(u);
		}
		BddConnection.cerrar(pstmt);
		BddConnection.cerrar(rs);
		BddConnection.cerrar();

		return usuarios;
	}

	public Usuario autenticarPersona(String usuario, String contrasena) throws SQLException {
		
		for (Usuario usr : getUsuarios()) {
			if (usr.getUsuario().equals(usuario) && usr.getContrasena().equals(contrasena)) {
				return usr;
			}
		}
		
		return null;
	}
	
	public Usuario getUsuarioById(int idUsuario) throws SQLException {
		for (Usuario usr : getUsuarios()) {
			if (usr.getId().equals(idUsuario))
				return usr;
		}
		return null;
	}
	
}