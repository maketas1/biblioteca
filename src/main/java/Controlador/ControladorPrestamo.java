package Controlador;

import Modelo.DAOGenerico;
import Modelo.Ejemplar;
import Modelo.Prestamo;
import Modelo.Usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControladorPrestamo {

    private DAOGenerico<Prestamo> daoPrestamo;
    private DAOGenerico<Ejemplar> daoEjemplar;
    private ControladorUsuario controladorUsuario;

    public ControladorPrestamo() {
        this.daoPrestamo = new DAOGenerico<>(Prestamo.class);
        this.daoEjemplar = new DAOGenerico<>(Ejemplar.class);
    }

    public List<Prestamo> getAll() {
        List<Prestamo> prestamos = daoPrestamo.getAll();
        return prestamos;
    }

    public Prestamo getById(int id) {
        Prestamo prestamo = daoPrestamo.getById(id);
        return prestamo;
    }

    public Prestamo update(Prestamo prestamo) {
        return daoPrestamo.update(prestamo);
    }

    public boolean delete(Prestamo prestamo) {
        return daoPrestamo.deleteUsuario(prestamo);
    }

    public void registrarPrestamo(Usuario usuario, Ejemplar ejemplar) {
        if (usuario.getPenalizacionHasta() != null && !usuario.getPenalizacionHasta().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("El usuario tiene una penalización activa y no puede realizar préstamos.");
        }

        List<Prestamo> prestamosActivos = daoPrestamo.getAll();
        long prestamosUsuario = prestamosActivos.stream()
                .filter(p -> p.getUsuario().equals(usuario) && p.getFechaDevolucion() == null)
                .count();

        if (prestamosUsuario >= 3) {
            throw new IllegalArgumentException("El usuario ya tiene 3 préstamos activos.");
        }

        if (!ejemplar.getEstado().equalsIgnoreCase("Disponible")) {
            throw new IllegalArgumentException("El ejemplar no está disponible para préstamo.");
        }

        Prestamo prestamo = new Prestamo(usuario, ejemplar, LocalDate.now(), LocalDate.now());
        ejemplar.setEstado("Prestado");
        daoEjemplar.update(ejemplar);
        daoPrestamo.add(prestamo);
    }

    public void registrarDevolucion(Prestamo prestamo) {
        Ejemplar ejemplar = prestamo.getEjemplar();
        Usuario usuario = prestamo.getUsuario();

        prestamo.setFechaDevolucion(LocalDate.now());
        daoPrestamo.update(prestamo);

        ejemplar.setEstado("Disponible");
        daoEjemplar.update(ejemplar);

        long diasAtraso = ChronoUnit.DAYS.between(prestamo.getFechaDevolucion(), LocalDate.now());
        if (diasAtraso > 0) {
            int diasPenalizacion = (int) diasAtraso * 15;
            controladorUsuario.registrarPenalizacion(usuario, diasPenalizacion);
        }

    }
}
