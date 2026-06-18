package controlador;

import java.util.List;
import modelo.Reserva;
import modelo.Tren;
import modelo.Usuario;
import org.bson.Document;

// Patrón Facade: punto de acceso unificado que oculta la complejidad de los controladores.
// Patrón Singleton: una sola instancia accesible globalmente mediante getInstancia().
// SOLID (S): responsabilidad única — coordinar la delegación entre controladores.
// SOLID (D): inversión de dependencias — la vista depende solo de esta fachada, no de controladores concretos.
public class SistemaFacade {

    // Singleton: instancia única
    private static SistemaFacade instancia;

    private final UsuarioControlador usuarioControl;
    private final ReservaControlador reservaControl;
    private final TrenControlador trenControl;

    // Singleton: constructor privado impide instanciación externa
    private SistemaFacade() {
        this.usuarioControl = new UsuarioControlador();
        this.reservaControl = new ReservaControlador();
        this.trenControl = new TrenControlador();
    }

    // Singleton: punto de acceso global con inicialización perezosa
    public static SistemaFacade getInstancia() {
        if (instancia == null) {
            instancia = new SistemaFacade();
        }
        return instancia;
    }

    // Facade: delega al UsuarioControlador
    public Usuario login(String username, String password) {
        return usuarioControl.iniciarSesion(username, password);
    }

    // Facade: delega al UsuarioControlador
    public boolean registrarUsuario(Usuario u) {
        return usuarioControl.registrarUsuario(u);
    }

    // Facade: delega al ReservaControlador
    public List<Reserva> obtenerReservas(boolean esAdmin, String username) {
        if (esAdmin) {
            return reservaControl.listarReservas();
        } else {
            return reservaControl.historial(username);
        }
    }

    // Facade: delega al ReservaControlador
    public boolean cancelarReserva(String idReserva) {
        return reservaControl.cancelar(idReserva);
    }

    // Facade: delega al ReservaControlador
    public boolean crearReserva(Document docReserva) {
        return reservaControl.crearReserva(docReserva);
    }

    // Facade: delega al TrenControlador
    public List<Tren> buscarTrenes(String criterio) {
        return trenControl.filtrarTrenes(criterio);
    }

    // Facade: delega al TrenControlador
    public void registrarTren(Document tren) {
        trenControl.registrarTren(tren);
    }
}