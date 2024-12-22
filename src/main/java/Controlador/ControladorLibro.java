package Controlador;

import Modelo.DAOGenerico;
import Modelo.DAOGenerico2;
import Modelo.Libro;

import java.util.List;

public class ControladorLibro {

    private DAOGenerico2<Libro, String> daoLibro;

    public ControladorLibro() {
        this.daoLibro = new DAOGenerico2<>(Libro.class, String.class);
    }

    public void registrarLibro(Libro libro) {
        daoLibro.add(libro);
    }

    public List<Libro> getAll() {
        List<Libro> libro = daoLibro.getAll();
        return libro;
    }

    public Libro getById(String id) {
        Libro libro = daoLibro.getById(id);
        return libro;
    }

    public Libro update(Libro libro) {
        return daoLibro.update(libro);
    }

    public boolean delete(Libro libro) {
        return daoLibro.deleteUsuario(libro);
    }
}
