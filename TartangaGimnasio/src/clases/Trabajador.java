package clases;

public class Trabajador extends Persona {
    private String usuario;
    private String contraseña;
    private String cargo;

    public Trabajador(String dni, String nombre, String usuario, String contraseña, String cargo) {
        super(dni, nombre);

        if (!cargo.equals("monitor") && !cargo.equals("recepcionista")) {
            throw new IllegalArgumentException("Cargo no válido.");
        }

        this.usuario = usuario;
        this.contraseña = contraseña;
        this.cargo = cargo;
    }

    @Override
    public void visualizar() {
        System.out.println("---Trabajador---");
        System.out.println("DNI: " + dni);
        System.out.println("Nombre: " + nombre);
        System.out.println("Usuario: " + usuario);
        System.out.println("Cargo: " + cargo);
    }
}
