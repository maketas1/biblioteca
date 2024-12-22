package Controlador;

import Modelo.DAOGenerico;
import Modelo.Ejemplar;
import Modelo.Libro;
import Modelo.Prestamo;

import java.util.List;

public class ControladorEjemplar {

    private DAOGenerico<Ejemplar> daoEjemplar;

    public ControladorEjemplar() {
        this.daoEjemplar = new DAOGenerico<>(Ejemplar.class);
    }

    public void registrarEjemplar(Ejemplar e) {
        daoEjemplar.add(e);
    }

    public int obtenerStockDisponible(String isbn) {
        List<Ejemplar> ejemplares = daoEjemplar.getAll();
        return (int) ejemplares.stream()
                .filter(e -> e.getIsbn().equals(isbn) && e.getEstado().equalsIgnoreCase("Disponible"))
                .count();
    }

    public List<Ejemplar> getAll() {
        List<Ejemplar> ejemplar = daoEjemplar.getAll();
        return ejemplar;
    }

    public Ejemplar getById(int id) {
        Ejemplar ejemplar = daoEjemplar.getById(id);
        return ejemplar;
    }

    public Ejemplar update(Ejemplar ejemplar) {
        return daoEjemplar.update(ejemplar);
    }

    public boolean delete(Ejemplar ejemplar) {
        return daoEjemplar.deleteUsuario(ejemplar);
    }
}
