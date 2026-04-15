package modelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

// Patrón DAO: encapsula el acceso a la colección "pagos" de MongoDB.
// SOLID (S): responsabilidad única — solo gestiona la persistencia de pagos.
public class PagoDAO {

    private MongoCollection<Document> coleccion;

    public PagoDAO() {
        MongoDatabase db = ConexionBD.getConexion();
        if (db != null) {
            coleccion = db.getCollection("pagos");
        }
    }

    public boolean registrarPago(Document pago) {
        if (coleccion == null || pago == null) {
            return false;
        }
        coleccion.insertOne(pago);
        return true;
    }

    public List<Pago> listarPagos() {
        List<Pago> lista = new ArrayList<>();
        if (coleccion == null)
            return lista;

        try {
            for (Document doc : coleccion.find()) {
                Pago p = new Pago();
                if (doc.getObjectId("_id") != null) {
                    p.setId(doc.getObjectId("_id").toHexString());
                }
                p.setUsername(doc.getString("username"));
                p.setReservaId(doc.getString("reservaId"));

                Object montoObj = doc.get("monto");
                if (montoObj instanceof Number) {
                    p.setMonto(((Number) montoObj).doubleValue());
                } else {
                    p.setMonto(0.0);
                }

                p.setEstado(doc.getString("estado"));
                lista.add(p);
            }
        } catch (Exception e) {
            System.err.println("Error listando pagos: " + e.getMessage());
        }
        return lista;
    }

    public List<Pago> listarPagosPorUsuario(String username) {
        List<Pago> lista = new ArrayList<>();
        if (coleccion == null || username == null)
            return lista;

        try {
            for (Document doc : coleccion.find(eq("username", username))) {
                Pago p = new Pago();
                if (doc.getObjectId("_id") != null) {
                    p.setId(doc.getObjectId("_id").toHexString());
                }
                p.setUsername(doc.getString("username"));
                p.setReservaId(doc.getString("reservaId"));

                Object montoObj = doc.get("monto");
                if (montoObj instanceof Number) {
                    p.setMonto(((Number) montoObj).doubleValue());
                } else {
                    p.setMonto(0.0);
                }

                p.setEstado(doc.getString("estado"));
                lista.add(p);
            }
        } catch (Exception e) {
            System.err.println("Error listando pagos usuario: " + e.getMessage());
        }
        return lista;
    }
}
