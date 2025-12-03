package principal;

import java.util.Scanner;

public class Utilidades {

    public static int pedirEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine();
            try {
                return Integer.parseInt(linea);
            } catch (Exception e) {
                System.out.println("Valor inválido. Introduce un número entero.");
            }
        }
    }

    public static int pedirEnteroEntre(Scanner sc, String msg, int min, int max) {
        while (true) {
            int valor = pedirEntero(sc, msg);
            if (valor >= min && valor <= max) return valor;
            System.out.println("Debe estar entre " + min + " y " + max);
        }
    }

    public static double pedirDouble(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String linea = sc.nextLine();
            try {
                return Double.parseDouble(linea);
            } catch (Exception e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }
}
