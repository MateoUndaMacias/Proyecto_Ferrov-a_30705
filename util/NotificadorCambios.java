package util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Patrón Observer: sujeto (Subject) que mantiene la lista de observadores y los notifica.
// Patrón Singleton: una sola instancia para centralizar las notificaciones.
// SOLID (S): responsabilidad única — solo gestiona la comunicación entre emisores y observadores.
public final class NotificadorCambios {

    // Singleton: instancia única
    private static NotificadorCambios instancia;
    private final List<ObservadorCambios> observadores = new CopyOnWriteArrayList<>();

    // Singleton: constructor privado
    private NotificadorCambios() {
    }

    // Singleton: punto de acceso global sincronizado (thread-safe)
    public static synchronized NotificadorCambios getInstancia() {
        if (instancia == null) {
            instancia = new NotificadorCambios();
        }
        return instancia;
    }

    // Patrón Observer: registrar un nuevo observador
    public void registrar(ObservadorCambios o) {
        if (o != null && !observadores.contains(o)) {
            observadores.add(o);
        }
    }

    // Patrón Observer: quitar un observador
    public void quitar(ObservadorCambios o) {
        observadores.remove(o);
    }

    // Patrón Observer: notificar a todos los observadores registrados
    public void notificar(String tipoCambio) {
        for (ObservadorCambios o : observadores) {
            try {
                o.onDatosCambiados(tipoCambio);
            } catch (Exception e) {
                System.err.println("Error en observador: " + e.getMessage());
            }
        }
    }
}
