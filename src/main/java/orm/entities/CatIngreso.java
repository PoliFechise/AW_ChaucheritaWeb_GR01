package orm.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INGRESO")
public class CatIngreso extends Categoria {

	private static final long serialVersionUID = 1L;

	public CatIngreso() {
    }

    public CatIngreso(String nombre) {
        super(nombre);
    }
}