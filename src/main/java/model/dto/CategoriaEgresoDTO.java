package model.dto;

import java.math.BigDecimal;

public class CategoriaEgresoDTO {
    private int id;
    private String nombre;
    private BigDecimal totalEgreso;

    // Constructor que EclipseLink necesita
    public CategoriaEgresoDTO(int id, String nombre, BigDecimal totalEgreso) {
        this.id = id;
        this.nombre = nombre;
        this.totalEgreso = totalEgreso;
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

    public BigDecimal getTotalEgreso() {
        return totalEgreso;
    }

    public void setTotalEgreso(BigDecimal sumaValor) {
        this.totalEgreso = sumaValor;
    }
}