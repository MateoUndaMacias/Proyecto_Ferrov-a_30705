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
        if (coleccion == null) return lista;

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
        if (doc == null) return null;

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

        return t;
    }

    public List<Tren> buscarTrenesPorCriterio(String filtro) {
        List<Tren> lista = new ArrayList<>();
        if (coleccion == null) return lista;

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

            Object precioObj = doc.get("precio");
            t.setPrecio(precioObj instanceof Number ? ((Number) precioObj).doubleValue() : 0.0);

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

    // ============================================
    // ELIMINAR TREN
    // ============================================
    public boolean eliminarTren(String codigo) {
        if (coleccion == null || codigo == null) return false;

        return coleccion.deleteOne(eq("codigo", codigo)).getDeletedCount() > 0;
    }

    // ============================================
    // ACTIVAR / DESACTIVAR
    // ============================================
    public boolean cambiarEstado(String codigo, boolean activo) {
        if (coleccion == null || codigo == null) return false;

        coleccion.updateOne(
                eq("codigo", codigo),
                new Document("$set", new Document("activo", activo))
        );

        return true;
    }

    // ============================================
    // EDITAR TREN
    // ============================================
    public boolean actualizarTren(String codigo, String ruta, int capacidad, double precio, boolean activo) {
        if (coleccion == null || codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        try {
            Document datos = new Document()
                    .append("ruta", ruta)
                    .append("capacidad", capacidad)
                    .append("precio", precio)
                    .append("activo", activo);

            coleccion.updateOne(
                    eq("codigo", codigo),
                    new Document("$set", datos)
            );

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
