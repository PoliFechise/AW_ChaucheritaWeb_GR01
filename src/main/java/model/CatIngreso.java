package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INGRESO")
public class CatIngreso extends Categoria {

    public CatIngreso() {
    }

    public CatIngreso(String nombre) {
        super(nombre);
    }
}
