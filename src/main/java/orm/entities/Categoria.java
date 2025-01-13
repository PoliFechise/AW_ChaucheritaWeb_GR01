package orm.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private Tipo tipo; // Puede ser ingreso, egreso o transferencia
	
	@OneToMany(mappedBy = "destino")
    private List<Egreso> egresos;

	public Categoria() {

	}

	public Categoria(int id, String nombre, Tipo tipo) {
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
        // Validación para asegurarse de que el tipo es válido
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo no puede ser nulo");
        }
        this.tipo = tipo;
    }
	
	 public List<Egreso> getEgresos() {
	        return egresos;
	    }

	    public void setEgresos(List<Egreso> egresos) {
	        this.egresos = egresos;
	    }
	
	public enum Tipo {
        INGRESO,
        EGRESO,
        TRANSFERENCIA
    }

}