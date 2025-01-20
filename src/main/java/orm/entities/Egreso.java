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
	@JoinColumn(name = "origen", nullable = false)
	private Cuenta origen;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "destino", nullable = false)
	private CatEgreso destino;

	@OneToOne
	@JoinColumn(name = "movimiento_id", nullable = false)
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

	public CatEgreso getDestino() {
		return destino;
	}

	public void setDestino(CatEgreso destino) {
		this.destino = destino;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
	
	
}
