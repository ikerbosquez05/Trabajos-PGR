package clases;

import java.time.LocalDate;

public abstract class Persona {
    protected String dni;
    protected String nombre;

    public Persona(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public abstract void visualizar();
}
