package modelo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

// Patrón Singleton: garantiza una única conexión a MongoDB en toda la aplicación.
// SOLID (S): responsabilidad única — solo gestiona la conexión a la base de datos.
public final class ConexionBD {

    private static final String URI = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "ferroviaria";

    // Singleton: instancia única del cliente MongoDB
    private static MongoClient mongoClient;

    // Singleton: constructor privado impide instanciación externa
    private ConexionBD() {
    }

    // Singleton: punto de acceso global con inicialización perezosa (lazy init)
    public static MongoDatabase getConexion() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}
