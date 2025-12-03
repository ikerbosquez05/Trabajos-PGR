package clases;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cliente extends Persona {
    private LocalDate fechaAlta;
    private Map<String, Entrenamiento> entrenamientos;
    private int contador = 100;

    public Cliente(String dni, String nombre, LocalDate fechaAlta) {
        super(dni, nombre);
        this.fechaAlta = fechaAlta;
        this.entrenamientos = new LinkedHashMap<>();
    }

    public void addEntrenamiento(LocalDate fecha, String ejercicio, int reps) {
        String codigo = "WOD-" + contador++;
        entrenamientos.put(codigo, new Entrenamiento(fecha, ejercicio, reps));
    }

    @Override
    public void visualizar() {
        System.out.println("---CLIENTE---");
        System.out.println("DNI: " + dni);
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha de Alta: " + fechaAlta);

        System.out.println("--- Entrenamientos ---");
        for (Map.Entry<String, Entrenamiento> e : entrenamientos.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

    public Map<String, Entrenamiento> getEntrenamientos() {
        return entrenamientos;
    }
}
