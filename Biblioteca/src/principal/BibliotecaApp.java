package principal;

import clases.*;
import java.util.*;

public class BibliotecaApp {

    public static void main(String[] args) {

        final String NOMBRE_BIBLIOTECA = "Lectura Viva";
        List<Socio> socios = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        boolean salir = false;

        while (!salir) {

            System.out.println("---Biblioteca---"" + NOMBRE_BIBLIOTECA + "\"---");
            System.out.println("1) Introducir socio/bibliotecario");
            System.out.println("2) Ver todos los socios");
            System.out.println("3) Ver bibliotecarios");
            System.out.println("4) Ver bibliotecarios de una sección");
            System.out.println("5) Buscar por nombre");
            System.out.println("6) Buscar por cuota mínima");
            System.out.println("7) Bibliotecarios con X años o más");
            System.out.println("8) Dar de baja por DNI");
            System.out.println("9) Salir");

            System.out.print("Opción: ");
            String op = sc.nextLine();

            switch (op) {
                case "1" -> introducirSocio(socios, NOMBRE_BIBLIOTECA, sc);
                case "2" -> verTodos(socios);
                case "3" -> verBibliotecarios(socios);
                case "4" -> bibliotecariosSeccion(socios, sc);
                case "5" -> buscarPorNombre(socios, sc);
                case "6" -> buscarPorCuota(socios, sc);
                case "7" -> bibliotecariosPorAnios(socios, sc);
                case "8" -> darDeBaja(socios, sc);
                case "9" -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    public static void introducirSocio(List<Socio> lista, String nombreBiblio, Scanner sc) {

        System.out.print("¿Es bibliotecario? (s/n): ");
        boolean esB = sc.nextLine().trim().equalsIgnoreCase("s");

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        for (Socio s : lista) {
            if (s.getDni().equalsIgnoreCase(dni)) {
                System.out.println("Ya existe un socio con ese DNI.");
                return;
            }
        }

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();

        int mes = Utilidades.pedirEnteroEntre(sc, "Mes alta (1-12): ", 1, 12);
        int anyo = Utilidades.pedirEntero(sc, "Año alta: ");
        int maxLibros = Utilidades.pedirEntero(sc, "Máx libros: ");

        if (esB) {
            System.out.print("Sección: ");
            String sec = sc.nextLine();
            lista.add(new Bibliotecario(nombreBiblio, dni, nombre, mes, anyo, maxLibros, sec));
        } else {
            lista.add(new Socio(nombreBiblio, dni, nombre, mes, anyo, maxLibros));
        }

        System.out.println("Añadido correctamente.");
    }

    public static void verTodos(List<Socio> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay socios.");
            return;
        }
        for (Socio s : lista) {
            System.out.println(s.toStringConCuota());
        }
    }

    public static void verBibliotecarios(List<Socio> lista) {
        boolean hay = false;
        for (Socio s : lista) {
            if (s.esBibliotecario()) {
                System.out.println(s.toStringConCuota());
                hay = true;
            }
        }
        if (!hay)
            System.out.println("No hay bibliotecarios.");
    }

    public static void bibliotecariosSeccion(List<Socio> lista, Scanner sc) {
        System.out.print("Introduce sección: ");
        String sec = sc.nextLine().trim();

        List<String> nombres = new ArrayList<>();

        for (Socio s : lista) {
            if (s.esBibliotecario()) {
                Bibliotecario b = (Bibliotecario) s;
                if (b.getSeccion().equalsIgnoreCase(sec))
                    nombres.add(b.getNombreCompleto());
            }
        }

        if (nombres.isEmpty()) {
            System.out.println("No hay bibliotecarios en esa sección.");
        } else {
            System.out.println("Bibliotecarios de la sección " + sec);
            for (String n : nombres)
                System.out.println("- " + n);
        }
    }

    public static void buscarPorNombre(List<Socio> lista, Scanner sc) {
        System.out.print("Introduce texto del nombre: ");
        String texto = sc.nextLine().toLowerCase();

        List<Socio> encontrados = new ArrayList<>();

        for (Socio s : lista) {
            if (s.getNombreCompleto().toLowerCase().contains(texto))
                encontrados.add(s);
        }

        if (encontrados.isEmpty()) {
            System.out.println("No hay coincidencias.");
            return;
        }

        for (Socio s : encontrados) {
            System.out.printf("DNI: %s | Años: %d | Bibliotecario: %s%n",
                    s.getDni(),
                    s.getAniosEnBiblioteca(),
                    s.esBibliotecario() ? "Sí" : "No");
        }
    }

    public static void buscarPorCuota(List<Socio> lista, Scanner sc) {
        double cuota = Utilidades.pedirDouble(sc, "Introduce cuota mínima: ");

        boolean encontrado = false;

        for (Socio s : lista) {
            double cf = s.esBibliotecario()
                    ? ((Bibliotecario) s).getCuotaFinal()
                    : s.getCuotaFinalComoSocio();

            if (cf >= cuota) {
                System.out.printf("%s | Cuota: %.2f | Bibliotecario: %s%n",
                        s.getNombreCompleto(), cf, s.esBibliotecario() ? "Sí" : "No");
                encontrado = true;
            }
        }

        if (!encontrado)
            System.out.println("Nadie alcanza esa cuota.");
    }

    public static void bibliotecariosPorAnios(List<Socio> lista, Scanner sc) {
        int años = Utilidades.pedirEntero(sc, "Años mínimos: ");
        boolean hay = false;

        for (Socio s : lista) {
            if (s.esBibliotecario() && s.getAniosEnBiblioteca() >= años) {
                Bibliotecario b = (Bibliotecario) s;
                System.out.printf("- %s | Años: %d | Sección: %s%n",
                        b.getNombreCompleto(), b.getAniosEnBiblioteca(), b.getSeccion());
                hay = true;
            }
        }

        if (!hay)
            System.out.println("Ningún bibliotecario cumple esa condición.");
    }

    public static void darDeBaja(List<Socio> lista, Scanner sc) {
        System.out.print("DNI a eliminar: ");
        String dni = sc.nextLine();

        Iterator<Socio> it = lista.iterator();

        while (it.hasNext()) {
            if (it.next().getDni().equalsIgnoreCase(dni)) {
                it.remove();
                System.out.println("Eliminado.");
                return;
            }
        }

        System.out.println("No existe ese DNI.");
    }
}
