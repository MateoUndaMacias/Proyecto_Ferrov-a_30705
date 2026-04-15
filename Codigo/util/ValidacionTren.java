package util;

import java.util.Map;
import modelo.ValidadorDatos;

// Patrón Strategy: estrategia concreta que implementa la validación específica para trenes.
// SOLID (L): sustitución de Liskov — puede usarse en cualquier lugar donde se espere EstrategiaValidacion.
public class ValidacionTren implements EstrategiaValidacion {

    // Patrón Strategy: implementación concreta del método validar() para datos de
    // tren
    @Override
    public String validar(Map<String, Object> datos) {
        String codigo = (String) datos.get("codigo");
        String ruta = (String) datos.get("ruta");
        Object capObj = datos.get("capacidad");

        if (ValidadorDatos.textoVacio(codigo)) {
            return "El código del tren es obligatorio.";
        }
        if (ValidadorDatos.textoVacio(ruta)) {
            return "La ruta es obligatoria.";
        }
        if (capObj == null) {
            return "La capacidad es obligatoria.";
        }
        int capacidad;
        try {
            if (capObj instanceof Number) {
                capacidad = ((Number) capObj).intValue();
            } else {
                capacidad = Integer.parseInt(capObj.toString().trim());
            }
            if (capacidad <= 0) {
                return "La capacidad debe ser un número positivo.";
            }
        } catch (NumberFormatException e) {
            return "La capacidad debe ser un número entero válido.";
        }
        return null;
    }
}
