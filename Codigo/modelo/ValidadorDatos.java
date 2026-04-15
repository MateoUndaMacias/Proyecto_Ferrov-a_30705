package modelo;

// SOLID (S): responsabilidad única — centraliza las validaciones de datos reutilizables.
public final class ValidadorDatos {

    private ValidadorDatos() {
    }

    public static boolean textoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean longitudMinima(String texto, int min) {
        return texto != null && texto.trim().length() >= min;
    }

    public static boolean numeroPositivo(double valor) {
        return valor > 0;
    }
}
