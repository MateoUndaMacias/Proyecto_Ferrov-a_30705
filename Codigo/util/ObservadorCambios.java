package util;

// Patrón Observer: interfaz que define el contrato para los observadores de cambios de datos.
// SOLID (I): segregación de interfaces — interfaz pequeña con un solo método.
// SOLID (O): abierto/cerrado — cualquier panel puede implementarla sin modificar código existente.
public interface ObservadorCambios {

    String TIPO_RESERVA = "RESERVA";
    String TIPO_TREN = "TREN";
    String TIPO_USUARIO = "USUARIO";

    // Patrón Observer: método invocado cuando los datos cambian
    void onDatosCambiados(String tipoCambio);
}
