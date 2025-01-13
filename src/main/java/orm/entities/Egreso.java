package orm.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "egreso")
public class Egreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "origen")
	private Cuenta origen;

	@ManyToOne
	@JoinColumn(name = "destino")
	private Categoria destino;

	@OneToOne
	@JoinColumn(name = "movimiento_id")
	private Movimiento movimiento;

	public Egreso() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cuenta getOrigen() {
		return origen;
	}

	public void setOrigen(Cuenta origen) {
		this.origen = origen;
	}

	public Categoria getDestino() {
		return destino;
	}

	public void setDestino(Categoria destino) {
		this.destino = destino;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
	
	
}
