package orm.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EGRESO")
public class CatEgreso extends Categoria {

	private static final long serialVersionUID = 1L;

	public CatEgreso() {
    }

    public CatEgreso(String nombre) {
        super(nombre);
    }
}