package model.dto;

public class CategoriaEgresoDTO {
	private int id;
    private String nombre;
    private float totalEgreso;

    public CategoriaEgresoDTO(int id, String nombre, float totalEgreso) {
        this.id = id;
    	this.nombre = nombre;
        this.totalEgreso = totalEgreso;
    }

    public String getNombre() {
        return nombre;
    }

    public float getTotalEgreso() {
        return totalEgreso;
    }
    
    public int getId() {
    	return id;
    }
}

