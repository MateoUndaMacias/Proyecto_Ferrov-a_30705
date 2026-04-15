package modelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

// Patrón DAO: encapsula el acceso a las colecciones "reservas", "horarios" y "pagos" de MongoDB.
// SOLID (S): responsabilidad única — solo gestiona la persistencia de reservas.
public class ReservaDAO {

    private MongoCollection<Document> reservasCol;
    private MongoCollection<Document> horariosCol;
    private MongoCollection<Document> pagosCol;

    public ReservaDAO() {
        MongoDatabase db = ConexionBD.getConexion();
        if (db != null) {
            reservasCol = db.getCollection("reservas");
            horariosCol = db.getCollection("horarios");
            pagosCol = db.getCollection("pagos");
        }
    }

    public boolean guardar(Document reserva) {
        if (reservasCol == null || reserva == null) {
            return false;
        }
        reservasCol.insertOne(reserva);
        return true;
    }

    public boolean pagarReserva(String idReserva, double monto, String username, String metodoPago) {
        if (reservasCol == null || idReserva == null)
            return false;

        try {
            org.bson.types.ObjectId oid = new org.bson.types.ObjectId(idReserva);

            Document res = reservasCol.find(eq("_id", oid)).first();
            if (res == null)
                return false;

            String estadoActual = res.getString("estado");
            if (!"PENDIENTE".equalsIgnoreCase(estadoActual)) {
                return false;
            }

            String metodo = (metodoPago != null && !metodoPago.trim().isEmpty()) ? metodoPago.trim() : "Efectivo";
            Document pago = new Document()
                    .append("username", username)
                    .append("reservaId", idReserva)
                    .append("monto", monto)
                    .append("metodoPago", metodo)
                    .append("estado", "COMPLETADO");

            if (pagosCol != null) {
                pagosCol.insertOne(pago);
            }

            reservasCol.updateOne(
                    eq("_id", oid),
                    new Document("$set", new Document("estado", "PAGADO")));

            return true;

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean cancelarReserva(String idReserva) {
        if (reservasCol == null || idReserva == null)
            return false;

        try {
            org.bson.types.ObjectId oid = new org.bson.types.ObjectId(idReserva);

            Document res = reservasCol.find(eq("_id", oid)).first();
            if (res == null)
                return false;

            String estadoActual = res.getString("estado");
            if (!"PENDIENTE".equalsIgnoreCase(estadoActual)) {
                return false;
            }

            reservasCol.updateOne(
                    eq("_id", oid),
                    new Document("$set", new Document("estado", "CANCELADO")));
            return true;

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<Reserva> listarPorUsuario(String username) {
        List<Reserva> lista = new ArrayList<>();
        if (reservasCol == null || username == null || username.trim().isEmpty()) {
            return lista;
        }

        for (Document rDoc : reservasCol.find(eq("username", username))) {
            Reserva r = construirReservaCompleta(rDoc);
            lista.add(r);
        }

        return lista;
    }

    public List<Reserva> listarReservas() {
        List<Reserva> lista = new ArrayList<>();
        if (reservasCol == null) {
            return lista;
        }

        for (Document rDoc : reservasCol.find()) {
            Reserva r = construirReservaCompleta(rDoc);
            lista.add(r);
        }

        return lista;
    }

    private Reserva construirReservaCompleta(Document rDoc) {
        Reserva r = new Reserva();

        if (rDoc.getObjectId("_id") != null) {
            r.setId(rDoc.getObjectId("_id").toHexString());
        }

        r.setUsername(rDoc.getString("username"));
        r.setTren(rDoc.getString("tren"));
        r.setFecha(rDoc.getString("fecha"));
        r.setIdHorario(rDoc.getString("idHorario"));
        r.setEstado(rDoc.getString("estado"));

        Document hDoc = null;
        String idHorario = r.getIdHorario();
        if (idHorario != null && idHorario.trim().length() == 24 && esHex(idHorario.trim())) {
            try {
                hDoc = horariosCol.find(eq("_id", new org.bson.types.ObjectId(idHorario.trim()))).first();
            } catch (IllegalArgumentException ignored) {
            }
        }
        if (hDoc == null) {
            hDoc = horariosCol.find(
                    and(
                            eq("codigoTren", r.getTren()),
                            eq("fecha", r.getFecha())))
                    .first();
        }

        if (hDoc != null) {
            r.setHoraSalida(hDoc.getString("horaSalida"));
            r.setHoraLlegada(hDoc.getString("horaLlegada"));
        } else {
            r.setHoraSalida("-");
            r.setHoraLlegada("-");
        }

        Object montoReserva = rDoc.get("monto");
        if (montoReserva instanceof Number && ((Number) montoReserva).doubleValue() > 0) {
            r.setMonto(((Number) montoReserva).doubleValue());
        } else {
            Document pDoc = pagosCol.find(
                    eq("reservaId", r.getId())).first();

            if (pDoc != null) {
                Object montoObj = pDoc.get("monto");
                if (montoObj instanceof Number) {
                    r.setMonto(((Number) montoObj).doubleValue());
                } else {
                    r.setMonto(0.0);
                }
            } else {
                r.setMonto(0.0);
            }
        }

        return r;
    }

    private static boolean esHex(String s) {
        if (s == null || s.length() != 24)
            return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')))
                return false;
        }
        return true;
    }
}
