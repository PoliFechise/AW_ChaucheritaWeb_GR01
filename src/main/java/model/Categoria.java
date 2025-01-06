package model;

import java.io.Serializable;

public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;
	private String tipo; // Puede ser ingreso, egreso o transferencia

	public Categoria() {

	}
<<<<<<< Updated upstream
	
	public Categoria(int id, String nombre) {
=======

	public Categoria(int Id, String nombre, String tipo) {
>>>>>>> Stashed changes
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		// Validación para asegurarse de que el tipo es válido
		if (!tipo.equals("ingreso") && !tipo.equals("egreso") && !tipo.equals("transferencia")) {
			throw new IllegalArgumentException("Tipo no válido: " + tipo);
		}
		this.tipo = tipo;
	}

}