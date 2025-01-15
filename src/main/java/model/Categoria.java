package model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Nombre")
	private String nombre;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ingreso', 'egreso', 'transferencia')")
	private String tipo; // Puede ser ingreso, egreso o transferencia

	public Categoria() {

	}

	public Categoria(int id, String nombre) {
	}

	public Categoria(int Id, String nombre, String tipo) {

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