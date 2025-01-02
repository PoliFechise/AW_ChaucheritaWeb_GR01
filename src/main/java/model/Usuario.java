package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	private String contrasena;
	private String usuario;
	
	private static List<Usuario> listaUsuarios = null;

	public Usuario() {
	}
	
	

	public Usuario(Integer id, String nombre, String contrasena, String usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.usuario = usuario;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String clave) {
		this.contrasena = clave;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
