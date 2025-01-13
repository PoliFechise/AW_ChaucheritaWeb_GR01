package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TRANSFERENCIA")
public class CatTransferencia extends Categoria {

    public CatTransferencia() {
    }

    public CatTransferencia(String nombre) {
        super(nombre);
    }
}
