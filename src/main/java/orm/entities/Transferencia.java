package orm.entities;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name="transferencia")
public class Transferencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "origen", nullable = false)
    private Cuenta origen;

    @ManyToOne
    @JoinColumn(name = "destino", nullable = false)
    private Cuenta destino;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoria", nullable = false)
    private CatTransferencia categoria;

    @OneToOne
    @JoinColumn(name = "movimiento_id", nullable = false)
    private Movimiento movimiento;
    
	public Transferencia() {
		
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

	public Cuenta getDestino() {
		return destino;
	}

	public void setDestino(Cuenta destino) {
		this.destino = destino;
	}

	public CatTransferencia getCategoria() {
		return categoria;
	}

	public void setCategoria(CatTransferencia categoria) {
		this.categoria = categoria;
	}
	
	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
	
	
}
