package modelo;

// Patrón Singleton: mantiene una única sesión de usuario activa en toda la aplicación.
// SOLID (S): responsabilidad única — solo gestiona el estado de la sesión del usuario.
public final class ControlSesion {

    // Singleton: instancia estática única del usuario en sesión
    private static Usuario usuarioActual;

    // Singleton: constructor privado impide instanciación externa
    private ControlSesion() {
    }

    public static void iniciarSesion(Usuario u) {
        usuarioActual = u;
    }

    public static Usuario getUsuario() {
        return usuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    public static boolean sesionActiva() {
        return usuarioActual != null;
    }

    public static boolean esAdmin() {
        return usuarioActual != null
                && usuarioActual.getRol() != null
                && usuarioActual.getRol().equalsIgnoreCase("Admin");
    }
}
