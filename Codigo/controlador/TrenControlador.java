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
        // Patrón Observer: notifica a los observadores que los datos de trenes han
        // cambiado
        if (ok)
            NotificadorCambios.getInstancia().notificar(ObservadorCambios.TIPO_TREN);
        return ok;
    }
}
