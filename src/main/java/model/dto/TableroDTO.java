package model.dto;

import java.math.BigDecimal;
import java.util.List;
import orm.entities.Cuenta;

public class TableroDTO {
    private List<CategoriaEgresoDTO> categoriasEgreso;
    private List<CategoriaIngresoDTO> categoriasIngreso;
    private List<CategoriaTransferenciaDTO> categoriasTransferencia;
    private List<Cuenta> cuentas;
    private BigDecimal totalIngresos;
    private BigDecimal totalTransferencias;

    // Getters y setters

    public List<CategoriaEgresoDTO> getCategoriasEgreso() {
        return categoriasEgreso;
    }

    public void setCategoriasEgreso(List<CategoriaEgresoDTO> categoriasEgreso) {
        this.categoriasEgreso = categoriasEgreso;
    }

    public List<CategoriaIngresoDTO> getCategoriasIngreso() {
        return categoriasIngreso;
    }

    public void setCategoriasIngreso(List<CategoriaIngresoDTO> categoriasIngreso) {
        this.categoriasIngreso = categoriasIngreso;
    }

    public List<CategoriaTransferenciaDTO> getCategoriasTransferencia() {
        return categoriasTransferencia;
    }

    public void setCategoriasTransferencia(List<CategoriaTransferenciaDTO> categoriasTransferencia) {
        this.categoriasTransferencia = categoriasTransferencia;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public BigDecimal getTotalTransferencias() {
        return totalTransferencias;
    }

    public void setTotalTransferencias(BigDecimal totalTransferencias) {
        this.totalTransferencias = totalTransferencias;
    }
}