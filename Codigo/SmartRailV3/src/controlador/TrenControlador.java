package controlador;

import java.util.List;
import modelo.Tren;
import modelo.TrenDAO;
import org.bson.Document;
import util.NotificadorCambios;
import util.ObservadorCambios;

// SOLID (S): responsabilidad única — solo gestiona la lógica de negocio de trenes.
public class TrenControlador {

    private final TrenDAO trenDAO;

    public TrenControlador() {
        this.trenDAO = new TrenDAO();
    }

    public List<Tren> listarTrenes() {
        return trenDAO.listarTrenes();
    }

    public List<Tren> filtrarTrenes(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return trenDAO.listarTrenes();
        }
        return trenDAO.buscarTrenesPorCriterio(criterio.trim());
    }

    public boolean registrarTren(Document tren) {
        boolean ok = trenDAO.insertar(tren);

        // Notifica cambios
        if (ok) {
            NotificadorCambios.getInstancia().notificar(ObservadorCambios.TIPO_TREN);
        }

        return ok;
    }

    // ============================================
    // ELIMINAR TREN
    // ============================================
    public boolean eliminarTren(String codigo) {
        boolean ok = trenDAO.eliminarTren(codigo);

        if (ok) {
            NotificadorCambios.getInstancia().notificar(ObservadorCambios.TIPO_TREN);
        }

        return ok;
    }

    // ============================================
    // ACTIVAR / DESACTIVAR TREN
    // ============================================
    public boolean cambiarEstado(String codigo, boolean activo) {
        boolean ok = trenDAO.cambiarEstado(codigo, activo);

        if (ok) {
            NotificadorCambios.getInstancia().notificar(ObservadorCambios.TIPO_TREN);
        }

        return ok;
    }

    // ============================================
    // EDITAR TREN
    // ============================================
    public boolean actualizarTren(String codigo, String ruta, int capacidad, double precio, boolean activo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        if (ruta == null || ruta.trim().isEmpty()) {
            return false;
        }

        if (capacidad <= 0 || precio <= 0) {
            return false;
        }

        boolean ok = trenDAO.actualizarTren(codigo, ruta, capacidad, precio, activo);

        if (ok) {
            NotificadorCambios.getInstancia().notificar(ObservadorCambios.TIPO_TREN);
        }

        return ok;
    }
}