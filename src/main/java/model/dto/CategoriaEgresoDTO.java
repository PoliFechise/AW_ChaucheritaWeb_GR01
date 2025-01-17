package model.dto;

import java.math.BigDecimal;

public class CategoriaEgresoDTO {
    private int id;
    private String nombre;
    private BigDecimal sumaValor;

    // Constructor que EclipseLink necesita
    public CategoriaEgresoDTO(int id, String nombre, BigDecimal sumaValor) {
        this.id = id;
        this.nombre = nombre;
        this.totalEgreso = sumaValor;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSumaValor() {
        return sumaValor;
    }

    public void setSumaValor(BigDecimal sumaValor) {
        this.sumaValor = sumaValor;
    }
}