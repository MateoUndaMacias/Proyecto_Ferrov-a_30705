package util;

import java.util.Map;
import modelo.ValidadorDatos;

// Patrón Strategy: estrategia concreta que implementa la validación específica para usuarios.
// SOLID (L): sustitución de Liskov — puede usarse en cualquier lugar donde se espere EstrategiaValidacion.
public class ValidacionUsuario implements EstrategiaValidacion {

    // Patrón Strategy: implementación concreta del método validar() para datos de
    // usuario
    @Override
    public String validar(Map<String, Object> datos) {
        String nombre = (String) datos.get("nombre");
        String username = (String) datos.get("username");
        String password = (String) datos.get("password");

        if (ValidadorDatos.textoVacio(nombre)) {
            return "El nombre es obligatorio.";
        }
        if (ValidadorDatos.textoVacio(username)) {
            return "El usuario es obligatorio.";
        }
        if (ValidadorDatos.textoVacio(password)) {
            return "La contraseña es obligatoria.";
        }
        if (!ValidadorDatos.longitudMinima(password, 3)) {
            return "La contraseña debe tener al menos 3 caracteres.";
        }
        return null;
    }
}
