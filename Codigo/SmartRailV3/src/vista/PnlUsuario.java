
package vista;

import controlador.UsuarioControlador;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.Usuario;
import util.EstrategiaValidacion;
import util.ValidacionUsuario;

// Patrón MVC: vista para la gestión y registro de usuarios.
// Patrón Strategy: utiliza EstrategiaValidacion (ValidacionUsuario) para validar los datos del formulario.
// SOLID (S): responsabilidad única — solo gestiona la interfaz de usuarios.
public class PnlUsuario extends javax.swing.JPanel {

    public PnlUsuario() {
        initComponents();
        setBackground(vista.TemaSistema.getFondo());
        jPanel1.setBackground(vista.TemaSistema.getFondo());
        setBorder(javax.swing.BorderFactory.createEmptyBorder(vista.TemaSistema.getPadding(),
                vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding()));
    }

    private boolean modoRegistro;

    public PnlUsuario(boolean modoRegistro) {
        initComponents();
        setBackground(vista.TemaSistema.getFondo());
        jPanel1.setBackground(vista.TemaSistema.getFondo());
        setBorder(javax.swing.BorderFactory.createEmptyBorder(vista.TemaSistema.getPadding(),
                vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding()));
        this.modoRegistro = modoRegistro;

        if (modoRegistro) {
            cboRol.setSelectedItem("Cliente");
            cboRol.setEnabled(false);
        } else {
            if (!modelo.ControlSesion.getUsuario().getRol().equalsIgnoreCase("ADMIN")) {
                JOptionPane.showMessageDialog(this, "Acceso restringido a Administradores");
                txtNombre.setEnabled(false);
                txtUsuario.setEnabled(false);
                txtPassword.setEnabled(false);
                cboRol.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnLimpiar.setEnabled(false);
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        cboRol = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        lblTitulo.setText(" REGISTRO DE USUARIOS  ");

        lblNombre.setText("Nombre:");

        lblCorreo.setText("Usuario:");

        lblContraseña.setText("Contraseña:");

        lblRol.setText("Rol:");

        cboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Admin" }));
        cboRol.addActionListener(this::cboRolActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCorreo)
                            .addComponent(lblNombre)
                            .addComponent(lblContraseña)
                            .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtUsuario)
                            .addComponent(txtPassword)
                            .addComponent(cboRol, 0, 151, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btnGuardar)
                        .addGap(72, 72, 72)
                        .addComponent(btnLimpiar)))
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTitulo)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContraseña)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRol)
                    .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnLimpiar))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboRolActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

        String nombre = txtNombre.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());
        String rolSeleccionado = cboRol.getSelectedItem().toString();

        // Patrón Strategy: delega la validación a la estrategia concreta
        // ValidacionUsuario
        EstrategiaValidacion estrategia = new ValidacionUsuario();
        Map<String, Object> datos = new HashMap<>();
        datos.put("nombre", nombre);
        datos.put("username", usuario);
        datos.put("password", password);
        String error = estrategia.validar(datos);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String rol;
        if (rolSeleccionado.equalsIgnoreCase("Admin")) {
            rol = "ADMIN";
        } else {
            rol = "CLIENTE";
        }

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setUsername(usuario);
        u.setPassword(password);
        u.setRol(rol);

        UsuarioControlador control = new UsuarioControlador();

        if (control.registrarUsuario(u)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Usuario registrado correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            if (modoRegistro) {
                SwingUtilities.getWindowAncestor(this).dispose();
            } else {
                limpiarFormulario();
            }

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "El nombre de usuario ya existe",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
        cboRol.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cboRol;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
