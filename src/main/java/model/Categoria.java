package model;

import java.io.Serializable;

public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int Id;
	private String nombre;
	
	public Categoria() {
		
	}
	
	public Categoria(int Id, String nombre) {
		super();
		this.Id = Id;
		this.nombre = nombre;
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}