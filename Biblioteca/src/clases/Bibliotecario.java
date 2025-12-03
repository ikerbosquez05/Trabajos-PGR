package clases;

public class Bibliotecario extends Socio {

    private final double plus = 30.0;
    private final String seccion;

    public Bibliotecario(String nombreBiblioteca, String dni, String nombreCompleto,
                         int mesAlta, int anyoAlta, int maxLibros, String seccion) {

        super(nombreBiblioteca, dni, nombreCompleto, mesAlta, anyoAlta, maxLibros);
        this.seccion = seccion;
    }

    public String getSeccion() {
        return seccion;
    }

    @Override
    public boolean esBibliotecario() {
        return true;
    }

    public double getCuotaFinal() {
        return getCuotaFinalComoSocio() + plus;
    }

    @Override
    public String toStringConCuota() {
        return String.format("Biblioteca: %s | DNI: %s | Nombre: %s | Alta: %02d/%d | Max libros: %d | Sección: %s | Cuota final: %.2f€",
                getNombreBiblioteca(), getDni(), getNombreCompleto(),
                getMesAlta(), getAnyoAlta(), getMaxLibros(),
                seccion, getCuotaFinal());
    }
}
