
import Controlador.ControladorEjemplar;
import Controlador.ControladorLibro;
import Controlador.ControladorPrestamo;
import Controlador.ControladorUsuario;
import Modelo.Ejemplar;
import Modelo.Libro;
import Modelo.Prestamo;
import Modelo.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuBiblioteca {

    private static Usuario usuarioActual;
    
    static ControladorUsuario controladorUsuario;
    static ControladorPrestamo controladorPrestamo;
    static ControladorLibro controladorLibro;
    static ControladorEjemplar controladorEjemplar;
    

    public MenuBiblioteca(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== Menú de Biblioteca ===");
            if (usuarioActual.getTipo().equalsIgnoreCase("administrador")) {
                System.out.println("1. Salir");
                System.out.println("2. Ver todos los préstamos");
                System.out.println("3. Ver un prestamo en especifico");
                System.out.println("4. Registrar prestamo");
                System.out.println("5. Modificar prestamo");
                System.out.println("6. Borrar prestamo");
                System.out.println("7. Registrar devolucion");
                System.out.println("8. Ver todos los usuarios");
                System.out.println("9. Ver un usuarios en especifico");
                System.out.println("10. Registrar usuario");
                System.out.println("11. Modificar usuario");
                System.out.println("12. Borrar usuario");
                System.out.println("13. Ver todos los libros");
                System.out.println("14. Ver un libros en especifico");
                System.out.println("15. Registrar libro");
                System.out.println("16. Modificar libro");
                System.out.println("17. Borrar libro");
                System.out.println("18. Ver todos los ejemplares");
                System.out.println("19. Ver un ejemplares en especifico");
                System.out.println("20. Registrar ejemplar");
                System.out.println("21. Modificar ejemplar");
                System.out.println("22. Borrar ejemplar");
                System.out.println("23. Obtener stock disponible");
            } else {
                System.out.println("1. Salir");
                System.out.println("2. Ver mis préstamos");
            }

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            int id;
            String dni;
            Usuario usuario;
            Libro libro;
            Ejemplar ejemplar;
            Prestamo prestamo;
            LocalDate fechaInicio = LocalDate.now();
            LocalDate fechaFin = LocalDate.now();

            switch (opcion) {
                case 1:
                    System.out.println("Saliendo del sistema...");
                    break;
                case 2:
                    if (usuarioActual.getTipo().equalsIgnoreCase("administrador")) {
                        mostrarTodosLosPrestamos();
                    } else {
                        mostrarPrestamosUsuario();
                    }
                    break;
                case 3:
                    System.out.println("indique el id del prestamo:");
                    id = scanner.nextInt();
                    System.out.println(controladorPrestamo.getById(id));
                    break;
                case 4:
                    System.out.println("indique primero el dni del usuario:");
                    dni = scanner.nextLine();
                    usuario = controladorUsuario.buscarUsuario(dni);
                    System.out.println("indique el id del ejemplar:");
                    id = scanner.nextInt();
                    ejemplar = controladorEjemplar.getById(id);
                    controladorPrestamo.registrarPrestamo(usuario, ejemplar);
                    break;
                case 5:
                    System.out.println("indique primero el dni del usuario:");
                    dni = scanner.nextLine();
                    usuario = controladorUsuario.buscarUsuario(dni);
                    System.out.println("indique el id del ejemplar:");
                    id = scanner.nextInt();
                    ejemplar = controladorEjemplar.getById(id);
                    prestamo = new Prestamo(usuario, ejemplar, fechaInicio, fechaFin);
                    controladorPrestamo.update(prestamo);
                    break;
                case 6:
                    System.out.println("indique primero el dni del usuario:");
                    dni = scanner.nextLine();
                    usuario = controladorUsuario.buscarUsuario(dni);
                    System.out.println("indique el id del ejemplar:");
                    id = scanner.nextInt();
                    ejemplar = controladorEjemplar.getById(id);
                    prestamo = new Prestamo(usuario, ejemplar, fechaInicio, fechaFin);
                    controladorPrestamo.delete(prestamo);
                    break;
                case 7:
                    System.out.println("Indique el id del prestamo:");
                    id = scanner.nextInt();
                    prestamo = controladorPrestamo.getById(id);
                    controladorPrestamo.registrarDevolucion(prestamo);
                    break;
                case 8:
                    List<Usuario> usuarios = controladorUsuario.listarUsuarios();
                    System.out.println("\n=== Lista de Todos los Usuarios ===");
                    for (Usuario usuario1 : usuarios) {
                        System.out.println(usuario1);
                    }
                    break;
                case 9:
                    System.out.println("Indique el dni del usuario:");
                    dni = scanner.nextLine();
                    usuario = controladorUsuario.buscarUsuario(dni);
                    System.out.println(usuario);
                    break;
                case 10:
                    System.out.println("Indique el dni del usuario:");
                    dni = scanner.nextLine();
                    System.out.println("Indique el nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Indique el correo:");
                    String correo = scanner.nextLine();
                    System.out.println("indique la contraseña:");
                    String contrasena = scanner.nextLine();
                    System.out.println("indique el rol del usuario:");
                    String tipo = scanner.nextLine();
                    usuario = new Usuario(dni, nombre, correo, contrasena, tipo, LocalDate.now());
                    controladorUsuario.registrarUsuario(usuario);
                    break;
                case 11:
                    System.out.println("Indique el dni del usuario:");
                    dni = scanner.nextLine();
                    System.out.println("Indique el nombre:");
                    String nombre1 = scanner.nextLine();
                    System.out.println("Indique el correo:");
                    String correo1 = scanner.nextLine();
                    System.out.println("indique la contraseña:");
                    String contrasena1 = scanner.nextLine();
                    System.out.println("indique el rol del usuario:");
                    String tipo1 = scanner.nextLine();
                    usuario = new Usuario(dni, nombre1, correo1, contrasena1, tipo1, LocalDate.now());
                    controladorUsuario.updateUsuario(usuario);
                    break;
                case 12:
                    System.out.println("Indique el dni del usuario:");
                    dni = scanner.nextLine();
                    usuario = controladorUsuario.buscarUsuario(dni);
                    controladorUsuario.eliminarUsuario(usuario);
                    break;
                case 13:
                    List<Libro> libros = controladorLibro.getAll();
                    System.out.println("\n=== Lista de Todos los Libros ===");
                    for (Libro libro1 : libros) {
                        System.out.println(libro1);
                    }
                    break;
                case 14:
                    System.out.println("Indique el isbn del libro:");
                    dni = scanner.nextLine();
                    libro = controladorLibro.getById(dni);
                    System.out.println(libro);
                    break;
                case 15:
                    System.out.println("indique el isbn del libro:");
                    dni = scanner.nextLine();
                    System.out.println("indique el nombre:");
                    String titulo = scanner.nextLine();
                    System.out.println("indique el autor:");
                    String autor = scanner.nextLine();
                    libro = new Libro(dni, titulo, autor);
                    controladorLibro.registrarLibro(libro);
                    break;
                case 16:
                    System.out.println("indique el isbn del libro:");
                    dni = scanner.nextLine();
                    System.out.println("indique el nombre:");
                    String titulo1 = scanner.nextLine();
                    System.out.println("indique el autor:");
                    String autor1 = scanner.nextLine();
                    libro = new Libro(dni, titulo1, autor1);
                    controladorLibro.update(libro);
                    break;
                case 17:
                    System.out.println("indique el isbn del libro:");
                    dni = scanner.nextLine();
                    libro = controladorLibro.getById(dni);
                    controladorLibro.delete(libro);
                    break;
                case 18:
                    List<Ejemplar> ejemplares = controladorEjemplar.getAll();
                    System.out.println("\n=== Lista de Todos los ejemplares ===");
                    for (Ejemplar ejemplar1 : ejemplares) {
                        System.out.println(ejemplar1);
                    }
                    break;
                case 19:
                    System.out.println("Indique el id:");
                    id = scanner.nextInt();
                    ejemplar = controladorEjemplar.getById(id);
                    System.out.println(ejemplar);
                    break;
                case 20:
                    System.out.println("indique el isbn del libro:");
                    dni = scanner.nextLine();
                    libro = controladorLibro.getById(dni);
                    System.out.println("indique el estado:");
                    String estado = scanner.nextLine();
                    ejemplar = new Ejemplar(libro, estado);
                    controladorEjemplar.registrarEjemplar(ejemplar);
                    break;
                case 21:
                    System.out.println("indique el isbn del libro:");
                    dni = scanner.nextLine();
                    libro = controladorLibro.getById(dni);
                    System.out.println("indique el estado:");
                    String estado1 = scanner.nextLine();
                    ejemplar = new Ejemplar(libro, estado1);
                    controladorEjemplar.update(ejemplar);
                    break;
                case 22:
                    System.out.println("Indique el id:");
                    id = scanner.nextInt();
                    ejemplar = controladorEjemplar.getById(id);
                    controladorEjemplar.delete(ejemplar);
                    break;
                case 23:
                    System.out.println("indique el isbn del libro:");
                    dni = scanner.nextLine();
                    System.out.println(controladorEjemplar.obtenerStockDisponible(dni));
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 1);

    }

    private static void mostrarTodosLosPrestamos() {
        List<Prestamo> prestamos = controladorPrestamo.getAll();
        System.out.println("\n=== Lista de Todos los Préstamos ===");
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo);
        }
    }

    private static void mostrarPrestamosUsuario() {
        List<Prestamo> prestamos = controladorPrestamo.getAll();
        System.out.println("\n=== Mis Préstamos ===");
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().equals(usuarioActual)) {
                System.out.println(prestamo);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de la biblioteca");
        System.out.print("Ingrese su DNI: ");
        String dni = scanner.nextLine();

        Usuario usuario = controladorUsuario.buscarUsuario(dni);

        if (usuario == null) {
            System.out.println("Usuario no encontrado. Saliendo...");
            return;
        }

        mostrarMenu();
    }
}