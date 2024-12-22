package Controlador;

import Modelo.DAOGenerico2;
import Modelo.Usuario;

import java.time.LocalDate;
import java.util.List;

public class ControladorUsuario {

    private DAOGenerico2<Usuario, String> daoUsuario;

    public ControladorUsuario() {
        this.daoUsuario = new DAOGenerico2<>(Usuario.class, String.class);
    }

    public void registrarUsuario(Usuario usuario) {
        daoUsuario.add(usuario);
    }

    public void registrarPenalizacion(Usuario usuario, int diasPenalizacion) {
        LocalDate fechaFinPenalizacion = LocalDate.now().plusDays(diasPenalizacion);
        usuario.setPenalizacionHasta(fechaFinPenalizacion);
        daoUsuario.update(usuario);
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = daoUsuario.getAll();
        return usuarios;
    }

    public Usuario buscarUsuario(String dni) {
        Usuario usuario = daoUsuario.getById(dni);
        return usuario;
    }

    public Usuario updateUsuario(Usuario usuario) {
        return daoUsuario.update(usuario);
    }

    public boolean eliminarUsuario(Usuario usuario) {
        return daoUsuario.deleteUsuario(usuario);
    }
}
