package modelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.regex;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

// Patrón DAO: encapsula el acceso a la colección "trenes" de MongoDB.
// SOLID (S): responsabilidad única — solo gestiona la persistencia de trenes.
public class TrenDAO {

    private MongoCollection<Document> coleccion;

    public TrenDAO() {
        MongoDatabase db = ConexionBD.getConexion();
        if (db != null) {
            coleccion = db.getCollection("trenes");
        }
    }

    public List<Tren> listarTrenes() {
        List<Tren> lista = new ArrayList<>();
        if (coleccion == null) {
            return lista;
        }

        for (Document doc : coleccion.find()) {
            Tren t = new Tren();
            if (doc.getObjectId("_id") != null) {
                t.setId(doc.getObjectId("_id").toHexString());
            }
            t.setCodigo(doc.getString("codigo"));
            t.setRuta(doc.getString("ruta"));
            t.setCapacidad(doc.getInteger("capacidad", 0));
            t.setActivo(doc.getBoolean("activo", true));
            Object precioObj = doc.get("precio");
            t.setPrecio(precioObj instanceof Number ? ((Number) precioObj).doubleValue() : 0.0);
            lista.add(t);
        }

        return lista;
    }

    public Tren obtenerTrenPorCodigo(String codigo) {
        if (coleccion == null || codigo == null || codigo.trim().isEmpty()) {
            return null;
        }

        Document doc = coleccion.find(eq("codigo", codigo.trim())).first();
        if (doc == null) {
            return null;
        }

        Tren t = new Tren();
        if (doc.getObjectId("_id") != null) {
            t.setId(doc.getObjectId("_id").toHexString());
        }
        t.setCodigo(doc.getString("codigo"));
        t.setRuta(doc.getString("ruta"));
        t.setCapacidad(doc.getInteger("capacidad", 0));
        t.setActivo(doc.getBoolean("activo", true));
        Object precioObj2 = doc.get("precio");
        t.setPrecio(precioObj2 instanceof Number ? ((Number) precioObj2).doubleValue() : 0.0);

        return t;
    }

    public List<Tren> buscarTrenesPorCriterio(String filtro) {
        List<Tren> lista = new ArrayList<>();
        if (coleccion == null) {
            return lista;
        }

        Bson query;
        if (filtro == null || filtro.trim().isEmpty()) {
            query = new Document();
        } else {
            query = or(
                    regex("codigo", filtro.trim(), "i"),
                    regex("ruta", filtro.trim(), "i"));
        }

        for (Document doc : coleccion.find(query)) {
            Tren t = new Tren();
            if (doc.getObjectId("_id") != null) {
                t.setId(doc.getObjectId("_id").toHexString());
            }
            t.setCodigo(doc.getString("codigo"));
            t.setRuta(doc.getString("ruta"));
            t.setCapacidad(doc.getInteger("capacidad", 0));
            t.setActivo(doc.getBoolean("activo", true));
            Object precioObj3 = doc.get("precio");
            t.setPrecio(precioObj3 instanceof Number ? ((Number) precioObj3).doubleValue() : 0.0);
            lista.add(t);
        }

        return lista;
    }

    public boolean insertar(Document tren) {
        if (coleccion == null || tren == null)
            return false;
        try {
            if (obtenerTrenPorCodigo(tren.getString("codigo")) != null) {
                return false;
            }
            coleccion.insertOne(tren);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
