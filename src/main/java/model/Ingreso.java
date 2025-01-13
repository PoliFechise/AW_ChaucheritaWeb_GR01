package model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ingreso")
public class Ingreso implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String concepto;
	
	@Column
    private float valor;
	
	@Column
    private Date fecha;
	
	@Column(name="categoriaId")
    private String origen;
	
	@Column(name="cuentaDestino")
    private String destino;

    public Ingreso(String concepto, float valor, Date fecha, String origen, String destino) {
        this.concepto = concepto;
        this.valor = valor;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
    }
    
    public Ingreso() {
    	
    }

    // Getters y setters
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
