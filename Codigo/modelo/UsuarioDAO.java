package modelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

// Patrón DAO: encapsula el acceso a la colección "usuarios" de MongoDB.
public class UsuarioDAO {

    private MongoCollection<Document> coleccion;

    public UsuarioDAO() {
        MongoDatabase db = ConexionBD.getConexion();
        if (db != null) {
            coleccion = db.getCollection("usuarios");
        }
    }

    public Usuario login(String username, String password) {
        if (coleccion == null
                || username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return null;
        }

        Document doc = coleccion.find(and(
                eq("username", username.trim()),
                eq("activo", true))).first();

        if (doc != null) {
            String passAlmacenado = doc.getString("password");

            // ✅ Comparación directa (sin hash)
            if (password.equals(passAlmacenado)) {
                return documentToUsuario(doc);
            }
        }

        return null;
    }

    public boolean registrar(Usuario u) {
        if (coleccion == null || u == null) {
            return false;
        }

        if (u.getUsername() == null || u.getUsername().trim().isEmpty()) {
            return false;
        }

        if (u.getPassword() == null || u.getPassword().trim().isEmpty()) {
            return false;
        }

        // Verificar usuario duplicado
        if (coleccion.find(eq("username", u.getUsername().trim())).first() != null) {
            return false;
        }

        // ✅ Guardar contraseña SIN encriptar
        Document doc = new Document("nombre", u.getNombre())
                .append("username", u.getUsername().trim())
                .append("password", u.getPassword())
                .append("rol", u.getRol())
                .append("activo", true);

        coleccion.insertOne(doc);
        return true;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();

        if (coleccion == null) {
            return lista;
        }

        for (Document doc : coleccion.find()) {
            lista.add(documentToUsuario(doc));
        }

        return lista;
    }

    private Usuario documentToUsuario(Document doc) {
        Usuario u = new Usuario();

        if (doc.getObjectId("_id") != null) {
            u.setId(doc.getObjectId("_id").toHexString());
        }

        u.setNombre(doc.getString("nombre"));
        u.setUsername(doc.getString("username"));
        u.setRol(doc.getString("rol"));
        u.setActivo(doc.getBoolean("activo", true));

        return u;
    }
}
