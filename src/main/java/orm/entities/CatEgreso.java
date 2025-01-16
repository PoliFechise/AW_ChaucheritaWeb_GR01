package orm.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EGRESO")
public class CatEgreso extends Categoria {

    public CatEgreso() {
    }

    public CatEgreso(String nombre) {
        super(nombre);
    }
}