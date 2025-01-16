package orm.entities;

import jakarta.persistence.*;

@Entity
@Table(name="transferencia")
public class Transferencia {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "origen", nullable = false)
    private Cuenta origen;

    @ManyToOne
    @JoinColumn(name = "destino", nullable = false)
    private Cuenta destino;

    @ManyToOne
    @JoinColumn(name = "categoria", nullable = false)
    private Categoria categoria;

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
