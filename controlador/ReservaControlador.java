package controlador;

import java.util.List;
import modelo.Reserva;
import modelo.ReservaDAO;
import org.bson.Document;
import util.NotificadorCambios;
import util.ObservadorCambios;

// SOLID (S): responsabilidad única — solo gestiona la lógica de negocio de reservas.
public class ReservaControlador {

    private final ReservaDAO dao;

    public ReservaControlador() {
        this.dao = new ReservaDAO();
    }

    public boolean crearReserva(Document reserva) {
        if (reserva == null) {
            return false;
        }
        boolean ok = dao.guardar(reserva);
        // Patrón Observer: notifica a los observadores que los datos de reservas han
        // cambiado
        if (ok)
            NotificadorCambios.getInstancia().notificar(ObservadorCambios.TIPO_RESERVA);
        return ok;
    }

    public List<Reserva> historial(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El username es obligatorio");
        }
        return dao.listarPorUsuario(username);
    }

    public boolean cancelar(String idReserva) {
        if (idReserva == null)
            return false;
        boolean ok = dao.cancelarReserva(idReserva);
        // Patrón Observer: notifica cambio tras cancelación
        if (ok)
            NotificadorCambios.getInstancia().notificar(ObservadorCambios.TIPO_RESERVA);
        return ok;
    }

    public boolean pagar(String idReserva, double monto, String username, String metodoPago) {
        if (idReserva == null)
            return false;
        boolean ok = dao.pagarReserva(idReserva, monto, username, metodoPago);
        // Patrón Observer: notifica cambio tras pago
        if (ok)
            NotificadorCambios.getInstancia().notificar(ObservadorCambios.TIPO_RESERVA);
        return ok;
    }

    public List<Reserva> listarReservas() {
        return dao.listarReservas();
    }
}
