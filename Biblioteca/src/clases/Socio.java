package clases;

import java.time.Year;

public class Socio {

    private final String nombreBiblioteca;
    private final String dni;
    private final String nombreCompleto;
    private final int mesAlta;
    private final int anyoAlta;
    private final int maxLibros;

    private final double cuotaBase = 12.0;

    public Socio(String nombreBiblioteca, String dni, String nombreCompleto,
                 int mesAlta, int anyoAlta, int maxLibros) {

        this.nombreBiblioteca = nombreBiblioteca;
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.mesAlta = mesAlta;
        this.anyoAlta = anyoAlta;
        this.maxLibros = maxLibros;
    }

    public String getDni() { return dni; }
    public String getNombreCompleto() { return nombreCompleto; }
    public int getMesAlta() { return mesAlta; }
    public int getAnyoAlta() { return anyoAlta; }
    public int getMaxLibros() { return maxLibros; }
    public String getNombreBiblioteca() { return nombreBiblioteca; }

    public int getAniosEnBiblioteca() {
        int actual = Year.now().getValue();
        return actual - anyoAlta;
    }

    public double getCuotaFinalComoSocio() {
        double extra = 0;
        if (maxLibros > 3) {
            extra = (maxLibros - 3) * 1.0;
        }
        double cuota = cuotaBase + extra;

        if (getAniosEnBiblioteca() >= 8)
            cuota -= 2;

        return cuota;
    }

    public boolean esBibliotecario() {
        return false;
    }

    public String toStringConCuota() {
        return String.format("Biblioteca: %s | DNI: %s | Nombre: %s | Alta: %02d/%d | Max libros: %d | Cuota final: %.2f€",
                nombreBiblioteca, dni, nombreCompleto, mesAlta, anyoAlta, maxLibros, getCuotaFinalComoSocio());
    }
}
