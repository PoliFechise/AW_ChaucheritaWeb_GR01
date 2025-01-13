package orm.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="contrasena")
	private String contrasena;
	
	@Column(name="usuario")
	private String usuario;
	
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
