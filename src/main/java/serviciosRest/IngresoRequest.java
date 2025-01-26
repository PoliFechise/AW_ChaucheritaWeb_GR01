package serviciosRest;

import orm.entities.CatIngreso;
import orm.entities.Cuenta;
import orm.entities.Ingreso;
import orm.entities.Movimiento;

public class IngresoRequest {
	
    private Ingreso ingreso;
    private Movimiento movimiento;
    private Cuenta cuenta;
    private CatIngreso catIngreso;
    private String fecha;

    public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public CatIngreso getCatIngreso() {
		return catIngreso;
	}

	public void setCatIngreso(CatIngreso catIngreso) {
		this.catIngreso = catIngreso;
	}

	public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
