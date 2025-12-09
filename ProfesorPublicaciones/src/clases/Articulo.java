package clases;

public class Articulo extends Publicacion {
	private String medio;

	public Articulo(String fecha, String titulo, String medio) {
		super(fecha, titulo);
		this.medio = medio;
	}

	public String getMedio() {
		return medio;
	}

	@Override
	public String toString() {
		return "ARTÍCULO -> " + super.toString() + ", Medio: " + medio;
	}
}
