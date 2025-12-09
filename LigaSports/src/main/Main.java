package main;

import clases.*;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {

		Ligaesports liga = new Ligaesports();

		// Competidores
		liga.añadirCompetidor("Alice");
		liga.añadirCompetidor("Bob");
		liga.añadirCompetidor("Carlos");

		liga.listarCompetidores();

		liga.actualizarCompetidor("Carlos", "Carl");
		liga.listarCompetidores();

		ArrayList<String> eliminarVarios = new ArrayList<>();
		eliminarVarios.add("Alice");
		eliminarVarios.add("Bob");

		liga.eliminarCompetidores(eliminarVarios);

		liga.listarCompetidores();

		// Partido
		liga.registrarPartidas("Carl", 5);
		liga.registrarPartidas("Alice", 3);
		liga.listarPartidas();

		liga.actualizarPartidas("Carl", 10);
		liga.listarPartidas();

		liga.eliminarRegistro("Alice");
		liga.listarPartidas();

		// Comparar registro
		System.out.println("Comparando registros (esperado TRUE):");
		System.out.println(liga.compararRegistros());

		// Caso falso
		liga.añadirCompetidor("JugadorFantasma");
		System.out.println("Comparando registros (esperado FALSE):");
		System.out.println(liga.compararRegistros());
	}
}
