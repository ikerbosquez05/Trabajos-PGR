package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Ligaesports {

	private ArrayList<String> competidores;
	private HashMap<String, Integer> partidasGanadas;

	public Ligaesports() {
		competidores = new ArrayList<>();
		partidasGanadas = new HashMap<>();
	}

	public void añadirCompetidor(String nombre) {
		if (!competidores.contains(nombre)) {
			competidores.add(nombre);
		}
	}

	public void listarCompetidores() {
		System.out.println("Competidores inscritos:");
		for (String c : competidores) {
			System.out.println("- " + c);
		}
	}

	public boolean actualizarCompetidor(String antiguo, String nuevo) {
		int index = competidores.indexOf(antiguo);
		if (index != -1) {
			competidores.set(index, nuevo);
			return true;
		}
		return false;
	}

	public boolean eliminarCompetidor(String nombre) {
		return competidores.remove(nombre);
	}

	public void eliminarCompetidores(ArrayList<String> nombres) {
		for (String nombre : nombres) {
			competidores.remove(nombre);
		}
	}

	public void registrarPartidas(String competidor, int cantidad) {
		partidasGanadas.put(competidor, cantidad);
	}

	public void listarPartidas() {
		System.out.println("Partidas ganadas:");
		for (String key : partidasGanadas.keySet()) {
			System.out.println(key + " -> " + partidasGanadas.get(key));
		}
	}

	public void actualizarPartidas(String competidor, int nuevasPartidas) {
		if (partidasGanadas.containsKey(competidor)) {
			partidasGanadas.put(competidor, nuevasPartidas);
		}
	}

	public void eliminarRegistro(String competidor) {
		partidasGanadas.remove(competidor);
	}

	public boolean compararRegistros() {
		Iterator<String> itCompetidores = competidores.iterator();

		while (itCompetidores.hasNext()) {
			String comp = itCompetidores.next();

			if (!partidasGanadas.containsKey(comp)) {
				return false;
			}
		}
		return true;
	}
}
