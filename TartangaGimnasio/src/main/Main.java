package main;

import clases.*;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Cliente c = new Cliente("12345678A", "Ane", LocalDate.of(2024, 1, 10));

        c.addEntrenamiento(LocalDate.of(2024, 2, 1), "Sentadillas", 30);
        c.addEntrenamiento(LocalDate.of(2024, 2, 3), "Flexiones", 20);
        c.addEntrenamiento(LocalDate.of(2024, 2, 5), "Burpees", 15);

        System.out.println("---CLIENTE ORIGINAL---");
        c.visualizar();

        Iterator<Map.Entry<String, Entrenamiento>> it = c.getEntrenamientos().entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Entrenamiento> entrada = it.next();
            Entrenamiento ent = entrada.getValue();
            ent.setFecha(ent.getFecha().plusDays(1));
        }

        System.out.println("---CLIENTE TRAS CAMBIO DE FECHAS---");
        c.visualizar();
    }
}
