package orm.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "concepto")
	private String concepto;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "fecha")
	private LocalDateTime fecha;
	
	@ManyToOne
	@JoinColumn(name = "cuenta")
	private Cuenta cuenta;
	
	@OneToOne(mappedBy = "movimiento")
    private Egreso egreso;

    @OneToOne(mappedBy = "movimiento")
    private Ingreso ingreso;

    @OneToOne(mappedBy = "movimiento")
    private Transferencia transferencia;
	
	public Movimiento() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Egreso getEgreso() {
		return egreso;
	}

	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}

	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

	public Transferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}
	
	@Override
    public String toString() {
        String tipoMovimiento = "Desconocido";
        if (ingreso != null) {
            tipoMovimiento = "Ingreso";
        } else if (egreso != null) {
            tipoMovimiento = "Egreso";
        } else if (transferencia != null) {
            tipoMovimiento = "Transferencia";
        }
        
        return "Movimiento{" +
                "id=" + id +
                ", concepto='" + concepto + '\'' +
                ", valor=" + valor +
                ", fecha=" + fecha +
                ", cuenta=" + (cuenta != null ? cuenta.getNombre() : "null") +
                ", tipo=" + tipoMovimiento +
                '}';
    }
}
