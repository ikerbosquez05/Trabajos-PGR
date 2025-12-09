package clases;

public class Libro extends Publicacion {
	private String isbn;
	private boolean premiado;

	public Libro(String fecha, String titulo, String isbn, boolean premiado) {
		super(fecha, titulo);
		this.isbn = isbn;
		this.premiado = premiado;
	}

	public String getIsbn() {
		return isbn;
	}

	public boolean isPremiado() {
		return premiado;
	}

	@Override
	public String toString() {
		return "LIBRO -> " + super.toString() + ", ISBN: " + isbn + ", Premiado: " + (premiado ? "Sí" : "No");
	}
}
