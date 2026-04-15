package modelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

// Patrón DAO: encapsula el acceso a la colección "horarios" de MongoDB.
// SOLID (S): responsabilidad única — solo gestiona la persistencia de horarios.
public class HorarioDAO {

    private MongoCollection<Document> coleccion;

    public HorarioDAO() {
        MongoDatabase db = ConexionBD.getConexion();
        if (db != null) {
            coleccion = db.getCollection("horarios");
        }
    }

    public List<Horario> buscarPorFecha(String fechaBusqueda) {
        List<Horario> lista = new ArrayList<>();
        if (coleccion == null || fechaBusqueda == null || fechaBusqueda.trim().isEmpty()) {
            return lista;
        }

        TrenDAO trenDAO = new TrenDAO();

        for (Document doc : coleccion.find(eq("fecha", fechaBusqueda.trim()))) {
            Horario h = new Horario();
            if (doc.getObjectId("_id") != null) {
                h.setId(doc.getObjectId("_id").toHexString());
            }

            try {
                h.setFecha(LocalDate.parse(doc.getString("fecha")));
                h.setHoraSalida(LocalTime.parse(doc.getString("horaSalida")));
                h.setHoraLlegada(LocalTime.parse(doc.getString("horaLlegada")));
            } catch (Exception e) {
                continue;
            }

            String codigoTren = doc.getString("codigoTren");
            if (codigoTren != null && !codigoTren.trim().isEmpty()) {
                Tren tren = trenDAO.obtenerTrenPorCodigo(codigoTren);
                if (tren != null) {
                    h.setTren(tren);
                }
            }

            if (h.getTren() != null) {
                lista.add(h);
            }
        }

        return lista;
    }

    public List<Horario> listarTodos() {
        List<Horario> lista = new ArrayList<>();
        if (coleccion == null)
            return lista;

        TrenDAO trenDAO = new TrenDAO();

        for (Document doc : coleccion.find()) {
            Horario h = new Horario();
            if (doc.getObjectId("_id") != null) {
                h.setId(doc.getObjectId("_id").toHexString());
            }

            try {
                h.setFecha(LocalDate.parse(doc.getString("fecha")));
                h.setHoraSalida(LocalTime.parse(doc.getString("horaSalida")));
                h.setHoraLlegada(LocalTime.parse(doc.getString("horaLlegada")));
            } catch (Exception e) {
                continue;
            }

            String codigoTren = doc.getString("codigoTren");
            if (codigoTren != null) {
                h.setTren(trenDAO.obtenerTrenPorCodigo(codigoTren));
            }

            if (h.getTren() != null) {
                lista.add(h);
            }
        }

        return lista;
    }

    public void insertar(Document horario) {
        if (coleccion == null || horario == null)
            return;
        coleccion.insertOne(horario);
    }
}
