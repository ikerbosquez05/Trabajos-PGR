package main;

import clases.*;
import java.util.*;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static HashMap<String, Profesor> profesores = new HashMap<>();

	public static void main(String[] args) {
		int opcion;

		do {
			System.out.println("==== MENÚ ====");
			System.out.println("1. Introducir nuevo profesor");
			System.out.println("2. Añadir publicación");
			System.out.println("3. Mostrar libros galardonados de un año");
			System.out.println("4. Listado por departamento de profes con más publicaciones");
			System.out.println("5. Listado de profesores");
			System.out.println("6. Salir");
			System.out.print("Opción: ");
			opcion = Integer.parseInt(sc.nextLine());

			switch (opcion) {
			case 1:
				opcion1_nuevoProfesor();
				break;
			case 2:
				opcion2_añadirPublicacion();
				break;
			case 3:
				opcion3_librosGalardonados();
				break;
			case 4:
				opcion4_listadoDepartamentos();
				break;
			case 5:
				opcion5_listarProfes();
				break;
			case 6:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida.");
				break;
			}

		} while (opcion != 6);
	}

	public static boolean validarEmail(String email) {
		if (email == null)
			return false;
		String regex = "^[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-zA-Z]+$";
		return email.matches(regex);
	}

	public static void opcion1_nuevoProfesor() {
		System.out.print("Introduce email del profesor: ");
		String email = sc.nextLine();

		if (!validarEmail(email)) {
			System.out.println("ERROR: Email no válido.");
			return;
		}

		if (profesores.containsKey(email)) {
			System.out.println("ERROR: Ya existe un profesor con ese email.");
			return;
		}

		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Departamento: ");
		String dept = sc.nextLine();

		profesores.put(email, new Profesor(email, nombre, dept));
		System.out.println("Profesor creado correctamente.");
	}

	public static void opcion2_añadirPublicacion() {
		System.out.print("Introduce email del profesor: ");
		String email = sc.nextLine();

		if (!validarEmail(email)) {
			System.out.println("ERROR: Email no válido.");
			return;
		}

		Profesor p = profesores.get(email);
		if (p == null) {
			System.out.println("ERROR: No existe el profesor.");
			return;
		}

		System.out.println("Profesor encontrado:");
		System.out.println(p);

		String seguir;
		do {
			System.out.println("¿Qué tipo de publicación quieres añadir? (1=Libro, 2=Artículo): ");
			int tipo = Integer.parseInt(sc.nextLine());

			System.out.print("Fecha: ");
			String fecha = sc.nextLine();

			System.out.print("Título: ");
			String titulo = sc.nextLine();

			if (tipo == 1) {
				System.out.print("ISBN: ");
				String isbn = sc.nextLine();
				System.out.print("¿Premiado? (S/N): ");
				boolean premiado = sc.nextLine().equalsIgnoreCase("S");

				p.addPublicacion(new Libro(fecha, titulo, isbn, premiado));

			} else if (tipo == 2) {
				System.out.print("Medio: ");
				String medio = sc.nextLine();
				p.addPublicacion(new Articulo(fecha, titulo, medio));

			} else {
				System.out.println("Tipo no válido.");
			}

			System.out.print("¿Añadir otra publicación? (S/N): ");
			seguir = sc.nextLine();

		} while (seguir.equalsIgnoreCase("S"));
	}

	public static void opcion3_librosGalardonados() {
		System.out.print("Introduce año: ");
		String año = sc.nextLine();

		boolean encontrado = false;

		for (Profesor p : profesores.values()) {
			for (Publicacion pub : p.getPublicaciones()) {
				if (pub instanceof Libro l && l.isPremiado()) {
					String fecha = l.getFecha().replace("-", "/");
					String añoPub = fecha.substring(fecha.lastIndexOf("/") + 1);

					if (añoPub.equals(año)) {
						encontrado = true;
						System.out.println("\nFecha: " + l.getFecha());
						System.out.println("Título: " + l.getTitulo());
						System.out.println("ISBN: " + l.getIsbn());
						System.out.println("Profesor: " + p.getNombre());
						System.out.println("Departamento: " + p.getDepartamento());
					}
				}
			}
		}

		if (!encontrado) {
			System.out.println("No hay libros premiados en ese año.");
		}
	}

	public static void opcion4_listadoDepartamentos() {
		HashMap<String, ArrayList<Profesor>> mapa = new HashMap<>();

		boolean hayPublicaciones = false;

		for (Profesor p : profesores.values()) {
			if (p.getPublicaciones().size() > 0) {
				hayPublicaciones = true;
				mapa.computeIfAbsent(p.getDepartamento(), k -> new ArrayList<>()).add(p);
			}
		}

		if (!hayPublicaciones) {
			System.out.println("No existen publicaciones de ningún profesor.");
			return;
		}

		for (String dept : mapa.keySet()) {
			System.out.println("\nDepartamento: " + dept);

			ArrayList<Profesor> lista = mapa.get(dept);

			lista.sort((a, b) -> {
				int diff = b.getPublicaciones().size() - a.getPublicaciones().size();
				return diff != 0 ? diff : a.getNombre().compareToIgnoreCase(b.getNombre());
			});

			for (Profesor p : lista) {
				System.out.println(p.getNombre() + " - " + p.getPublicaciones().size());
			}
		}
	}

	public static void opcion5_listarProfes() {
		if (profesores.isEmpty()) {
			System.out.println("No hay profesores.");
			return;
		}

		for (Profesor p : profesores.values()) {
			System.out.println(p);
		}
	}
}
