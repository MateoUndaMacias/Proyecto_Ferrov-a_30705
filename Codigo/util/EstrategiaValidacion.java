package util;

import java.util.Map;

// Patrón Strategy: interfaz que define el contrato para estrategias de validación.
// SOLID (O): abierto/cerrado — se pueden agregar nuevas estrategias sin modificar el código que las usa.
// SOLID (D): inversión de dependencias — el código cliente depende de esta abstracción, no de implementaciones concretas.
// SOLID (I): segregación de interfaces — interfaz con un solo método.
public interface EstrategiaValidacion {

    // Patrón Strategy: método que cada estrategia concreta implementa con sus
    // propias reglas
    String validar(Map<String, Object> datos);
}
