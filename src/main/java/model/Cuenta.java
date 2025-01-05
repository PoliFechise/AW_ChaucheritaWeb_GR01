package model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private String numero;
	private BigDecimal saldo;
	
	public Cuenta() {
		
	}
	
	public Cuenta(Integer id, String nombre, String numero, BigDecimal saldo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numero = numero;
		this.saldo = saldo;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	
	

}
