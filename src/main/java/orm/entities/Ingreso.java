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
	@JoinColumn(name = "origen")
	private Categoria origen;

	@ManyToOne
	@JoinColumn(name = "destino")
	private Cuenta destino;

	@OneToOne
	@JoinColumn(name = "movimiento_id")
	private Movimiento movimiento;

	public Ingreso() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Categoria getOrigen() {
		return origen;
	}

	public void setOrigen(Categoria origen) {
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
	
	

}
