package controlador;

import java.util.List;
import modelo.Usuario;
import modelo.UsuarioDAO;

// SOLID (S): responsabilidad única — solo gestiona la lógica de negocio de usuarios.
public class UsuarioControlador {

    private final UsuarioDAO dao;

    public UsuarioControlador() {
        this.dao = new UsuarioDAO();
    }

    public Usuario iniciarSesion(String username, String password) {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return null;
        }
        return dao.login(username.trim(), password);
    }

    public boolean registrarUsuario(Usuario u) {
        if (u == null)
            return false;
        if (u.getUsername() == null || u.getUsername().trim().isEmpty())
            return false;
        if (u.getPassword() == null || u.getPassword().trim().isEmpty())
            return false;
        if (u.getRol() == null || u.getRol().trim().isEmpty())
            return false;
        return dao.registrar(u);
    }

    public List<Usuario> listarUsuarios() {
        return dao.listarUsuarios();
    }
}
