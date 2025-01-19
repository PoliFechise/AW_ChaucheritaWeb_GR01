package orm.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "ingreso")
public class Ingreso implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "origen", nullable = false)
	private CatIngreso origen;

	@ManyToOne
	@JoinColumn(name = "destino", nullable = false)
	private Cuenta destino;

	@OneToOne
	@JoinColumn(name = "movimiento_id", nullable = false)
	private Movimiento movimiento;

	public Ingreso() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CatIngreso getOrigen() {
		return origen;
	}

	public void setOrigen(CatIngreso origen) {
		this.origen = origen;
	}

	public Cuenta getDestino() {
		return destino;
	}

	public void setDestino(Cuenta destino) {
		this.destino = destino;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
	
	@Override
    public String toString() {
        return "Ingreso{" +
                "id=" + id +
                ", origen=" + (origen != null ? origen.getNombre() : "null") +
                ", destino=" + (destino != null ? destino.getNombre() : "null") +
                ", movimiento=" + (movimiento != null ? "id=" + movimiento.getId() + ", concepto=" + movimiento.getConcepto() + ", valor=" + movimiento.getValor() : "null") +
                '}';
    }
}
