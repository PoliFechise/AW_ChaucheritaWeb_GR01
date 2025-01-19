package orm.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TRANSFERENCIA")
public class CatTransferencia extends Categoria {

	private static final long serialVersionUID = 1L;

	public CatTransferencia() {
    }

    public CatTransferencia(String nombre) {
        super(nombre);
    }
}