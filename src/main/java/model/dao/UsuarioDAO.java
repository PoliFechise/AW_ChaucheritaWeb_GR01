package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import orm.entities.*;
import model.bdd.BddConnection;

public class UsuarioDAO {

	private EntityManager entityManager = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager(); 
	
	public UsuarioDAO() {

	}
	
	public List<Usuario> getUsuarios() {
        String jpql = "SELECT u FROM Usuario u";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        return query.getResultList();
    }

    public Usuario autenticarPersona(String usuario, String contrasena) {
        String jpql = "SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.contrasena = :contrasena";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("usuario", usuario);
        query.setParameter("contrasena", contrasena);
        
        List<Usuario> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    public Usuario getUsuarioById(int idUsuario) {
        return entityManager.find(Usuario.class, idUsuario);
    }

	public List<Usuario> getUsuariosSinORM() throws SQLException {
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

	public Usuario autenticarPersonaSinORM(String usuario, String contrasena) throws SQLException {
		
		for (Usuario usr : getUsuarios()) {
			if (usr.getUsuario().equals(usuario) && usr.getContrasena().equals(contrasena)) {
				return usr;
			}
		}
		
		return null;
	}
	
	public Usuario getUsuarioByIdSinORM(int idUsuario) throws SQLException {
		for (Usuario usr : getUsuarios()) {
			if (usr.getId().equals(idUsuario))
				return usr;
		}
		return null;
	}
	
}