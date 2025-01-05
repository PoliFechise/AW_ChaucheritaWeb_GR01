package model;

import java.util.Date;

public class Ingreso {
    private String concepto;
    private float valor;
    private Date fecha;
    private int origen;
    private String destino;

    public Ingreso(String concepto, float valor, Date fecha, int origen, String destino) {
        this.concepto = concepto;
        this.valor = valor;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
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

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
